package com.test.berlin_clock.service;

import com.test.berlin_clock.enums.LampState;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class DigitalTimeToBerlinClockTimeService {
    public static final int NB_ONE_MINUTE_BLOCKS = 4;
    public static final int NB_FIVE_MINUTES_BLOCKS = 11;
    public static final int NB_ONE_HOUR_BLOCKS = 4;
    public static final int NB_FIVE_HOURS_BLOCKS = 4;

    public String getSingleMinutesRow(LocalTime time) {
        int minutes = time.getMinute();
        int numberOfLight = minutes % 5;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < NB_ONE_MINUTE_BLOCKS; i++) {
            if (i < numberOfLight) {
                result.append(LampState.YELLOW.getValue());
            } else {
                result.append(LampState.OFF.getValue());
            }
        }
        return result.toString();
    }

    public String getFiveMinutesRow(LocalTime time) {
        int minutes = time.getMinute();
        int numberOfLight = minutes / 5;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < NB_FIVE_MINUTES_BLOCKS; i++) {
            if (i < numberOfLight) {
                if ((i + 1) % 3 == 0) {
                    result.append(LampState.RED.getValue());
                } else {
                    result.append(LampState.YELLOW.getValue());
                }
            } else {
                result.append(LampState.OFF.getValue());
            }
        }
        return result.toString();
    }

    public String getSingleHourRow(LocalTime time) {
        int hours = time.getHour();
        int numberOfLight = hours % 5;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < NB_ONE_HOUR_BLOCKS; i++) {
            if (i < numberOfLight) {
                result.append(LampState.RED.getValue());
            } else {
                result.append(LampState.OFF.getValue());
            }
        }
        return result.toString();
    }

    public String getFiveHoursRow(LocalTime time) {
        int hours = time.getHour();
        int numberOfLight = hours / 5;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < NB_FIVE_HOURS_BLOCKS; i++) {
            if (i < numberOfLight) {
                result.append(LampState.RED.getValue());
            } else {
                result.append(LampState.OFF.getValue());
            }
        }
        return result.toString();
    }

    public String getSecondsRow(LocalTime time) {
        int seconds = time.getSecond();
        return seconds % 2 == 0 ? LampState.YELLOW.getValue() : LampState.OFF.getValue();
    }

    public String getBerlinClock(LocalTime time) {
        return getSecondsRow(time)
                + getFiveHoursRow(time)
                + getSingleHourRow(time)
                + getFiveMinutesRow(time)
                + getSingleMinutesRow(time);
    }
}
