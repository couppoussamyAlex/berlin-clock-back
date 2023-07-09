package com.test.berlin_clock.unit.service;

import com.test.berlin_clock.service.BerlinClockTimeToDigitalTimeService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BerlinClockTimeToDigitalTimeServiceTest {
    private BerlinClockTimeToDigitalTimeService berlinClockTimeToDigitalTimeService;

    @Before
    public void setUp() {
        berlinClockTimeToDigitalTimeService = new BerlinClockTimeToDigitalTimeService();
    }

    @Test
    public void shouldDisplayAllHours(){
        Assertions.assertEquals("23", berlinClockTimeToDigitalTimeService.getHours("ORRRRRRROYYRYYRYYRYYYYYY"));
    }

    @Test
    public void shouldDisplayNoHours(){
        Assertions.assertEquals("00", berlinClockTimeToDigitalTimeService.getHours("YOOOOOOOOOOOOOOOOOOOOOOO"));
    }

    @Test
    public void shouldDisplayAllMinutes(){
        Assertions.assertEquals("59", berlinClockTimeToDigitalTimeService.getMinutes("ORRRRRRROYYRYYRYYRYYYYYY"));
    }

    @Test
    public void shouldDisplayNoMinutes(){
        Assertions.assertEquals("00", berlinClockTimeToDigitalTimeService.getHours("YOOOOOOOOOOOOOOOOOOOOOOO"));
    }

    @Test
    public void shouldOddSecond() {
        Assertions.assertEquals("59", berlinClockTimeToDigitalTimeService.getSeconds("ORROOROOOYYRYYRYOOOOYYOO"));
    }

    @Test
    public void shouldEvenSecond() {
        Assertions.assertEquals("00", berlinClockTimeToDigitalTimeService.getSeconds("YOOOOOOOOOOOOOOOOOOOOOOO\n"));
    }

    @Test
    public void shouldEndOfTheDay(){
        Assertions.assertEquals("23:59:59", berlinClockTimeToDigitalTimeService.getDigitalTime("ORRRRRRROYYRYYRYYRYYYYYY"));
    }

    @Test
    public void shouldStartOfTheDay(){
        Assertions.assertEquals("00:00:00", berlinClockTimeToDigitalTimeService.getDigitalTime("YOOOOOOOOOOOOOOOOOOOOOOO"));
    }
}
