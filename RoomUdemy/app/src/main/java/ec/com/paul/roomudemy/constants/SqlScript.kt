package ec.com.paul.roomudemy.constants

/**
 * Created by Paul Yaguachi on 6/4/2019.
 * Paul Local
 */
object SqlScript {
    const val SQL_TABLE_CURSE: String =
        "CREATE TABLE curso (id INTEGER PRIMARY KEY NOT NULL, " +
                "name TEXT, " +
                "duration TEXT, " +
                "profesorId INTEGER NOT NULL, " +
                "foreign key (profesorId) references professor(id) ON DELETE CASCADE)"
    const val SQL_TABLE_LANGUAGE: String =
        "CREATE TABLE language (id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL)"

    const val SQL_TABLE_PROFESSOR_LANGUAGE: String =
        "CREATE TABLE professorlanguage (profesorId INTEGER NOT NULL, languageId INTEGER NOT NULL, PRIMARY KEY (profesorId, languageId) foreign key (profesorId) references professor(id),foreign key (languageId) references language(id))"

}