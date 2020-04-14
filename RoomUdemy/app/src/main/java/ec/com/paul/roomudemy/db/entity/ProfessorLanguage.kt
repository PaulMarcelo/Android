package ec.com.paul.roomudemy.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import ec.com.paul.roomudemy.constants.Constants

/**
 * Created by Paul Yaguachi on 7/4/2019.
 * Paul Local
 */
@Entity(
    tableName = Constants.NAME_TABLE_PROFESSOR_LANGUAGE,
    primaryKeys = ["profesorId", "languageId"],
    foreignKeys = [ForeignKey(
        entity = Professor::class,
        parentColumns = ["id"],
        childColumns = ["profesorId"]
    ),
        ForeignKey(
            entity = Language::class,
            parentColumns = ["id"],
            childColumns = ["languageId"]
        )]
)


class ProfessorLanguage {
    @ColumnInfo(name = "profesorId")
    var profesorId = 0

    @ColumnInfo(name = "languageId")
    var languageId = 0

}