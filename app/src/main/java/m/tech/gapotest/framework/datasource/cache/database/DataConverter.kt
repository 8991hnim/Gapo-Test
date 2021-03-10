package m.tech.gapotest.framework.datasource.cache.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.tech.gapotest.framework.datasource.cache.model.ImageEntity

class DataConverter {
//    @TypeConverter
//    fun fromImages(list: ArrayList<String>): String {
//        return Gson().toJson(list)
//    }
//
//    @TypeConverter
//    fun toImages(s: String): ArrayList<String> {
//        val type = object : TypeToken<ArrayList<String>>() {}.type
//        return Gson().fromJson(s, type)
//    }

    @TypeConverter
    fun fromImages(list: List<ImageEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toImages(s: String?): List<ImageEntity>? {
        val type = object : TypeToken<List<ImageEntity>>() {}.type
        return Gson().fromJson(s, type)
    }

}