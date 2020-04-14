package ec.com.paul.roomudemy.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity

import android.arch.persistence.room.PrimaryKey
import ec.com.paul.roomudemy.constants.Constants

/**
 * Created by Paul Yaguachi on 27/3/2019.
 * Paul Local
 */
@Entity(tableName = Constants.NAME_TABLE_PROFESSOR)

class Professor {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0
    @ColumnInfo(name = "name")
    var name = ""
    @ColumnInfo(name = "email")
    var email = ""
}