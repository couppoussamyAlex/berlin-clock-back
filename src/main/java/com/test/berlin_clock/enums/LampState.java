package com.test.berlin_clock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LampState {
    YELLOW("Y"),
    RED("R"),
    OFF("O");

    String value;
}
