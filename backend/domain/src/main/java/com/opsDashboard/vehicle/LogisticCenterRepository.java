package com.opsDashboard.vehicle;

import java.time.LocalDate;
import java.util.Optional;

interface LogisticCenterRepository
{
    Optional<Integer> findPuRequestCountByDateAndLCIdAndStatus(LocalDate puDate, int lcId, PUCodeRequest.Status status);

    Optional<LogisticCenter> findById(int lcId);
}
