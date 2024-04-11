package com.opsDashboard.vehicle;

import com.opsDashboard.vo.LCSource;
import com.opsDashboard.vo.UserSource;
import com.opsDashboard.vo.VehicleSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

class PUCodeRequest
{
    private int id;
    private LocalDateTime timestamp;
    private LocalDate puDate;
    private Status status;
    private VehicleSource vehicle;
    private UserSource requester;
    private LCSource lc;

    enum Status
    {
        ACCEPTED
        , REJECTED
        , WAITING_FOR_LC
    }
}
