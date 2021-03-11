package m.tech.gapotest.framework.datasource.cache.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.tech.gapotest.framework.datasource.cache.model.ImageEntity
import m.tech.gapotest.framework.datasource.cache.model.SectionEntity

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

    @TypeConverter
    fun fromSections(list: List<SectionEntity>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toSections(s: String?): List<SectionEntity>? {
        val type = object : TypeToken<List<SectionEntity>>() {}.type
        return Gson().fromJson(s, type)
    }

}