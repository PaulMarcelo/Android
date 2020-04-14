package ec.com.paul.arquitecturamvvm.db

import androidx.room.TypeConverter
import androidx.room.util.StringUtil
import java.util.*



/**
 * Created by Paul Yaguachi on 24/9/2019.
 * Paul Local
 */
class GithubTypeConverters {
    @TypeConverter
    fun stringToIntList(data: String?): List<Int>? {
        return if (data == null) {
            Collections.emptyList()
        } else StringUtil.splitToIntList(data)
    }

    @TypeConverter
    fun intListToString(ints: List<Int>): String? {
        return StringUtil.joinIntoString(ints)
    }
}