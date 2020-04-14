package ec.com.paul.roomudemy.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import ec.com.paul.roomudemy.constants.Constants

/**
 * Created by Paul Yaguachi on 6/4/2019.
 * Paul Local
 */

@Entity(
    tableName = Constants.NAME_TABLE_CURSE,
    foreignKeys = [ForeignKey(
        entity = Professor::class,
        parentColumns = ["id"],
        childColumns = ["profesorId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class Curse {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0
    @ColumnInfo(name = "name")
    var name = ""
    @ColumnInfo(name = "duration")
    var duration = ""
    @ColumnInfo(name = "profesorId")
    var profesoId  = 0

}