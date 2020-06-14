package ec.com.paul.blurimage

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

/**
 * Created by Paul Yaguachi on 12/6/2020.
 * Paul Local
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_CODE = 100
        const val IMAGE_CAPTURE_CODE = 1001
        const val PICK_IMAGE = 1002
    }

    private var urlImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        capture_image_btn.setOnClickListener {
            checkPermision { openCamera() }
        }

        pick_image_btn.setOnClickListener {
            checkPermision {
                idText.visibility = View.GONE
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            }
        }

        check_image_btn.setOnClickListener {
            if (urlImage == null) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.image_not_found),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                uploadFile()
            }
        }
    }

    private inline fun checkPermision(action: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (handlePermissions()) {
                action.invoke()
            }
        } else {
            action.invoke()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun handlePermissions(): Boolean {
        return if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
        ) {
            val permission = arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            requestPermissions(permission, PERMISSION_CODE)
            false
        } else {
            true
        }
    }

    private fun openCamera() {
        idText.visibility = View.GONE
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        urlImage = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, urlImage)
        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                val selectedImage: Uri = data!!.data!!
                urlImage = selectedImage
            }
            ivImage.setImageURI(urlImage)
        }
    }

    private fun uploadFile() {
        val path = getImagePathFromUri(urlImage)
        if (path == null) {
            Toast.makeText(
                applicationContext,
                getString(R.string.image_not_found),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        val file = File(path)
        val requestFile =
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        val fileToUpload =
            MultipartBody.Part.createFormData("file", file.name, requestFile)
        val getResponse = WSConfig.getRetrofit()!!.create(ServiceCamera::class.java)
        val call: Call<ResponseBody> = getResponse.uploadFile(fileToUpload)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    val serverResponse = response.body().string()
                    idText.visibility = View.VISIBLE
                    if (serverResponse.toString() == "TRUE") {
                        idText.setTextColor(ContextCompat.getColor(applicationContext, R.color.red))
                        idText.text = getString(R.string.blurred_image)
                    } else {
                        idText.setTextColor(
                            ContextCompat.getColor(
                                applicationContext,
                                R.color.green
                            )
                        )
                        idText.text = getString(R.string.no_blurred_image)
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.server_error, response.code()),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable?) {
                Log.e("Error", t.toString())
                Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getImagePathFromUri(aUri: Uri?): String? {
        var imagePath: String? = null
        if (aUri == null) {
            return imagePath
        }
        if (DocumentsContract.isDocumentUri(applicationContext, aUri)) {
            val documentId: String = DocumentsContract.getDocumentId(aUri)
            if ("com.android.providers.media.documents" == aUri.authority) {
                val id = documentId.split(":".toRegex()).toTypedArray()[1]
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == aUri.authority) {
                val contentUri: Uri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(documentId)
                )
                imagePath = getImagePath(contentUri, null)
            }
        } else if ("content".equals(aUri.scheme, ignoreCase = true)) {
            imagePath = getImagePath(aUri, null)
        } else if ("file".equals(aUri.scheme, ignoreCase = true)) {
            imagePath = aUri.path
        }
        return imagePath
    }

    private fun getImagePath(aUri: Uri, aSelection: String?): String? {
        var path: String? = null
        val cursor: Cursor? = applicationContext.contentResolver
            .query(aUri, null, aSelection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return path
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}