package com.astecnology.newspluse.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.astecnology.newspluse.domain.model.Source

/**
 * Type converter class for converting Source objects to and from String format.
 * This converter is used by Room to store and retrieve Source objects in the database.
 */
@ProvidedTypeConverter
class NewsTypeConverter {

    /**
     * Converts a Source object to its String representation.
     * @param source The Source object to be converted.
     * @return A String representation of the Source object.
     */
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id}, ${source.name}"
    }

    /**
     * Converts a String representation of a Source object back to a Source object.
     * @param source The String representation of the Source object.
     * @return The Source object parsed from the String representation.
     */
    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(sourceArray[0], sourceArray[1])
        }
    }
}