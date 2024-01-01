package com.okutan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String dateFormat = "YYYY-MM-DD";

    public static String getFormattedDate(Date unformattedDate){
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(unformattedDate);
    }
}
