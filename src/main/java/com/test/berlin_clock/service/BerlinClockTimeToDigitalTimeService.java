package com.test.berlin_clock.service;

import com.test.berlin_clock.enums.LampState;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BerlinClockTimeToDigitalTimeService {
    public static int FIVE_HOURS_START_INDEX = 1;
    public static int FIVE_HOURS_END_INDEX = 4;
    public static int SINGLE_HOURS_START_INDEX = 5;
    public static int SINGLE_HOURS_END_INDEX = 8;

    public static int FIVE_MINUTES_START_INDEX = 9;
    public static int FIVE_MINUTES_END_INDEX = 19;
    public static int SINGLE_MINUTES_START_INDEX = 20;
    public static int SINGLE_MINUTES_END_INDEX = 23;


    public String getHours(String berlinClock) {
        int result = getFiveHoursRowData(berlinClock.substring(FIVE_HOURS_START_INDEX, FIVE_HOURS_END_INDEX + 1)) + getSingleHoursRowData(berlinClock.substring(SINGLE_HOURS_START_INDEX, SINGLE_HOURS_END_INDEX + 1));
        return result < 10 ? "0" + result : String.valueOf(result);
    }

    private int getSingleHoursRowData(String berlinClock) {
        return StringUtils.countOccurrencesOf(berlinClock, LampState.RED.getValue());
    }

    private int getFiveHoursRowData(String berlinClock) {
        return StringUtils.countOccurrencesOf(berlinClock, LampState.RED.getValue()) * 5;
    }

    public String getMinutes(String berlinClock) {
        int result = getFiveMinutesRowData(berlinClock.substring(FIVE_MINUTES_START_INDEX, FIVE_MINUTES_END_INDEX + 1)) + getSingleMinutesRowData(berlinClock.substring(SINGLE_MINUTES_START_INDEX, SINGLE_MINUTES_END_INDEX + 1));
        return result < 10 ? "0" + result : String.valueOf(result);
    }

    private int getSingleMinutesRowData(String berlinClock) {
        return StringUtils.countOccurrencesOf(berlinClock, LampState.YELLOW.getValue());
    }

    private int getFiveMinutesRowData(String berlinClock) {
        return (11 - StringUtils.countOccurrencesOf(berlinClock, LampState.OFF.getValue())) * 5;
    }

    public String getSeconds(String berlinClock) {
        return !LampState.OFF.getValue().equals(berlinClock.substring(0, 1)) ? "00" : "59";
    }

    public String getDigitalTime(String berlinClock) {
        return getHours(berlinClock) + ":" + getMinutes(berlinClock) + ":" + getSeconds(berlinClock);
    }
}
