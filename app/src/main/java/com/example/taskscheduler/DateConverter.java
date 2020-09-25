package com.example.taskscheduler;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    //Room use this when written from database
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }
    //Room use this when writing into the database
    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
