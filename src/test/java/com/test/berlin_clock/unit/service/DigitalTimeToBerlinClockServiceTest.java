package com.test.berlin_clock.unit.service;

import com.test.berlin_clock.enums.LampState;
import com.test.berlin_clock.service.DigitalTimeToBerlinClockTimeService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.time.LocalTime;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DigitalTimeToBerlinClockServiceTest {
    private DigitalTimeToBerlinClockTimeService digitalTimeToBerlinClockTimeService;

    @Before
    public void setUp() {
        digitalTimeToBerlinClockTimeService = new DigitalTimeToBerlinClockTimeService();
    }

    @Test
    public void shouldSingleMinutesRowOn(){
        Assertions.assertEquals(4, StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleMinutesRow(LocalTime.of(23, 59,59)), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldSingleMinutesRowOff(){
        Assertions.assertEquals(0, StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleMinutesRow(LocalTime.of(23, 55, 59)), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldSingleMinutesRowFirstLampOn(){
        Assertions.assertEquals(
                1,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleMinutesRow(LocalTime.of(23, 59, 59)).substring(0, 1), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldSingleMinutesRowTwoFirstLampOn(){
        Assertions.assertEquals(
                2,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleMinutesRow(LocalTime.of(23, 59, 59)).substring(0, 2), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldSingleMinutesRowThreeFirstLampOn(){
        Assertions.assertEquals(
                3,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleMinutesRow(LocalTime.of(23, 58, 59)).substring(0, 3), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldFiveMinutesRowOn(){
        Assertions.assertEquals(
                0,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveMinutesRow(LocalTime.of(23,55, 59)), LampState.OFF.getValue()));
    }

    @Test
    public void shouldFiveMinutesRowOff(){
        Assertions.assertEquals(
                11,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveMinutesRow(LocalTime.of(23, 00, 59)), LampState.OFF.getValue()));
    }

    @Test
    public void shouldFiveMinutesRowFirstLampOn(){
        Assertions.assertEquals(
                1,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveMinutesRow(LocalTime.of(23, 05, 59)).substring(0, 1), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldFiveMinutesRowTwoFirstLampOn(){
        Assertions.assertEquals(
                2,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveMinutesRow(LocalTime.of(23, 10, 59)).substring(0, 2), LampState.YELLOW.getValue()));
    }

    @Test
    public void shouldFiveMinutesRowThreeFirstLampOn(){
        Assertions.assertEquals(
                2,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveMinutesRow(LocalTime.of(23, 15, 59)).substring(0, 2), LampState.YELLOW.getValue()));
        Assertions.assertEquals(
                1,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveMinutesRow(LocalTime.of(23, 15, 59)).substring(2, 3), LampState.RED.getValue()));
    }

    @Test
    public void shouldSingleHourRowOn(){
        Assertions.assertEquals(4, StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleHourRow(LocalTime.of(14, 59, 59)), LampState.RED.getValue()));
    }

    @Test
    public void shouldSingleHourRowOff(){
        Assertions.assertEquals(4, StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getSingleHourRow(LocalTime.of(20, 55, 59)), LampState.OFF.getValue()));
    }

    @Test
    public void shouldFiveHoursRowOn(){
        Assertions.assertEquals(
                4,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveHoursRow(LocalTime.of(23, 55, 59)), LampState.RED.getValue()));
    }

    @Test
    public void shouldFiveHoursRowOff(){
        Assertions.assertEquals(
                4,
                StringUtils.countOccurrencesOf(digitalTimeToBerlinClockTimeService.getFiveHoursRow(LocalTime.of(00, 00, 59)), LampState.OFF.getValue()));
    }

    @Test
    public void shouldLampOfSecondBeOff() {
        Assertions.assertEquals(LampState.OFF.getValue(), digitalTimeToBerlinClockTimeService.getSecondsRow(LocalTime.of(23, 59, 59)));
    }

    @Test
    public void shouldLampOfSecondBeYellow() {
        Assertions.assertEquals(LampState.YELLOW.getValue(), digitalTimeToBerlinClockTimeService.getSecondsRow(LocalTime.of(23, 59, 00)));
    }

    @Test
    public void shouldAllBerlinClockOff() {
        Assertions.assertEquals("OOOOOOOOOOOOOOOOOOOOOOOO", digitalTimeToBerlinClockTimeService.getBerlinClock(LocalTime.of(00,00, 01)));
    }

    @Test
    public void shouldAllBerlinClockOn() {
        Assertions.assertEquals("YRRRRRRROYYRYYRYYRYYYYYY", digitalTimeToBerlinClockTimeService.getBerlinClock(LocalTime.of(23, 59, 58)));
    }
}
