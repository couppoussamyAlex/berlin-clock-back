package com.test.berlin_clock.controller;

import com.test.berlin_clock.exception.IncorrectBerlinClockTimeException;
import com.test.berlin_clock.service.BerlinClockTimeToDigitalTimeService;
import com.test.berlin_clock.service.DigitalTimeToBerlinClockTimeService;
import com.test.berlin_clock.utils.TimeFormatUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Optional;

@RestController
@RequestMapping("berlin-clock")
@AllArgsConstructor
public class BerlinClockTimeController {
    private final DigitalTimeToBerlinClockTimeService digitalTimeToBerlinClockTimeService;
    private final BerlinClockTimeToDigitalTimeService berlinClockTimeToDigitalTimeService;

    @GetMapping(path = "/encode", produces = "application/json")
    public String getBerlinClock(@RequestParam("time") Optional<String> time){
        LocalTime localTime = LocalTime.now();
        if (time.isPresent()) {
            localTime = LocalTime.parse(time.get());
        }

        return digitalTimeToBerlinClockTimeService.getBerlinClock(localTime);
    }

    @GetMapping(path = "/decode/{berlin-clock}")
    public String getDigitalTime(@PathVariable("berlin-clock") String berlinClock) {
        if (TimeFormatUtils.IsIncorrectBerlinClockTimeFormat(berlinClock)) {
            throw new IncorrectBerlinClockTimeException();
        }
        return berlinClockTimeToDigitalTimeService.getDigitalTime(berlinClock);
    }
}
