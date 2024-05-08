package com.project.menuflash.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DatesUtil {

    public static Date getTodayUtcArg(){
        ZoneId timeZone = ZoneId.of("UTC-3");
        ZonedDateTime actualDate = ZonedDateTime.now(timeZone);
        return Date.from(actualDate.toInstant());
    }

}
