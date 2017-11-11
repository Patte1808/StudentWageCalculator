package de.pvoss.wagecalculatorandroidcomponents.utils;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by Pattelicious on 11.11.17.
 * Source: https://developer.android.com/reference/android/arch/persistence/room/TypeConverter.html
 */

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
