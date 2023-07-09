package com.test.berlin_clock.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeFormatUtils {
    public static boolean IsIncorrectBerlinClockTimeFormat(String time) {
        Pattern pattern = Pattern.compile("^[OY]([RO]{4})([RO]{4})(([YO]{2}[RO]){3})([YO]{2})([YO]{4})$");
        Matcher matcher = pattern.matcher(time);
        return !matcher.find();
    }
}
