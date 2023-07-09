package com.test.berlin_clock.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BerlinClockTimeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFailBecauseOfWrongBerlinClockTime() throws Exception {
        this.mockMvc.perform(get("/berlin-clock/decode/YRRROROOOYYRYYRYYRYOOOOD")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("The received time doesn't respect the format"));
    }

    @Test
    public void shouldGetDigitalTime() throws Exception {
        this.mockMvc.perform(get("/berlin-clock/decode/YRRROROOOYYRYYRYYRYOOOOO")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("16:50:00"));
    }

    @Test
    public void shouldFailBecauseOfWrongDigitalTime() throws Exception {
        this.mockMvc.perform(get("/berlin-clock/encode").queryParam("time", "24:00:00")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void shouldGeBerlinClockTime() throws Exception {
        this.mockMvc.perform(get("/berlin-clock/encode").queryParam("time", "23:59:59")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ORRRRRRROYYRYYRYYRYYYYYY"));
    }
}
