package com.sebqv97.myapplication.feature_users.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.sebqv97.myapplication.feature_users.data.util.JsonParser


@ProvidedTypeConverter
class Converters (private val jsonParser: JsonParser){

    @TypeConverter
    fun fromJson(json:String) : List<String>{
        return jsonParser.fromJson<List<String>>(json, object : TypeToken<List<String>>(){}.type)   ?: listOf<String>()
    }

    @TypeConverter
    fun toJson(complexData:List<String>):String {
        return jsonParser.toJson(complexData, object : TypeToken<List<String>>() {}.type)
    }

}