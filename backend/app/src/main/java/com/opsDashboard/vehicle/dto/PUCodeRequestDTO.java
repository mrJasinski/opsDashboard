package com.opsDashboard.vehicle.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PUCodeRequestDTO
{
    private LocalDateTime timestamp;
    private String stockId;
    private String vin;
    private LocalDate puDate;

    public LocalDateTime getTimestamp()
    {
        return this.timestamp;
    }

    public String getStockId()
    {
        return this.stockId;
    }

    public String getVin()
    {
        return this.vin;
    }

    public LocalDate getPuDate()
    {
        return this.puDate;
    }

    public LocalTime getTime()
    {
        return LocalTime.of(this.timestamp.getHour(), this.timestamp.getMinute());
    }
}
