package com.example.hackernews.data.room

import androidx.room.TypeConverter
import com.example.hackernews.data.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.xml.transform.Source


class RoomTypeConvertor {

    @TypeConverter
    fun fromHighlighResult(sh: HighlightResult): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toHighlighResult(sh: String): HighlightResult {
        return Gson().fromJson(sh,HighlightResult::class.java)
    }

    @TypeConverter
    fun fromAuthorResult(sh: Author): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toAuthorResult(sh: String): Author {
        return Gson().fromJson(sh,Author::class.java)
    }

    @TypeConverter
    fun fromCommentTextResult(sh: CommentText): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toCommentTextResult(sh: String): CommentText {
        return Gson().fromJson(sh,CommentText::class.java)
    }

    @TypeConverter
    fun fromStoryTextResult(sh: StoryText): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toStoryTextResult(sh: String): StoryText {
        return Gson().fromJson(sh,StoryText::class.java)
    }

    @TypeConverter
    fun fromStoryTitleResult(sh: StoryTitle): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toStoryTitleResult(sh: String): StoryTitle {
        return Gson().fromJson(sh,StoryTitle::class.java)
    }

    @TypeConverter
    fun fromStoryUrlResult(sh: StoryUrl): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toStoryUrlResult(sh: String): StoryUrl{
        return Gson().fromJson(sh,StoryUrl::class.java)
    }

    @TypeConverter
    fun fromTitleResult(sh: Title): String {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toTitleResult(sh: String): Title {
        return Gson().fromJson(sh,Title::class.java)
    }

    @TypeConverter
    fun fromUrlResult(sh: Url): String? {
        return Gson().toJson(sh)
    }
    @TypeConverter
    fun toUrlResult(sh:String ): Url {
        return Gson().fromJson(sh,Url::class.java)
    }

    @TypeConverter
    fun sourceToString(source: Source): String {
        return Gson().toJson(source)
    }

    @TypeConverter
    fun stringToSource(str: String): Source {
        return Gson().fromJson(str, Source::class.java)
    }

    @TypeConverter
    fun fromOptionValuesList(optionValues: List<String?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<String>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    fun toOptionValuesList(optionValuesString: String?): List<String?>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<String?>?>() {}.type
        return gson.fromJson(optionValuesString, type)
    }



}