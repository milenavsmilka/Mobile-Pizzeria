package com.example.systemymobilneprojekt.db;

import androidx.room.TypeConverter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(String value) throws ParseException {
        return value == null ? null : new SimpleDateFormat("dd/MM/yyyy").parse(value);
    }

    @TypeConverter
    public static String dateToTimestamp(Date date) {
        return date == null ? null : date.toString();
    }

    @TypeConverter
    public static BigDecimal floatToBigDecimal(Float number){
        return number == null ? null : new BigDecimal(Float.toString(number));
    }

    @TypeConverter
    public static Float bigDecimalToFloat(BigDecimal number) {
        return number == null ? null : number.floatValue();
    }
}
