package com.simononboard.blockchain.common.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@UtilityClass
public class DateUtils {

    private static final ZoneId zoneId = ZoneId.of("Europe/Moscow");

    public static LocalDate getDate() {
        return LocalDate.now(zoneId);
    }

    public static OffsetDateTime getDateTime() {
        return OffsetDateTime.now(zoneId);
    }
}
