package com.bmwgroup.timeservice.adapters.worldtime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorldTime {
    @JsonProperty("week_number")
    private  String weekNumber;

    @JsonProperty("utc_offset")
    private String utcOffset;

    private String unixtime;

    private String timezone;

    @JsonProperty("dst_until")
    private String dstUntil;

    @JsonProperty("dst_from")
    private String dstFrom;

    private boolean dst;

    @JsonProperty("day_of_year")
    private int dayOfYear;

    private String datetime;

    private String abbreviation;


    public String getWeekNumber() {
        return weekNumber;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getDstUntil() {
        return dstUntil;
    }

    public String getDstFrom() {
        return dstFrom;
    }

    public boolean isDst() {
        return dst;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
