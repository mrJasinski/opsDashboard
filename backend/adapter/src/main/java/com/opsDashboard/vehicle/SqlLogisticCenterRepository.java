package com.opsDashboard.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
interface SqlLogisticCenterRepository extends LogisticCenterRepository, JpaRepository<LogisticCenter, Integer>
{
    @Override
    @Query("SELECT COUNT(*) FROM PUCodeRequest pu WHERE pu.puDate = :puDate AND pu.lc.id = :lcId AND pu.status = :status")
    Optional<Integer> findPuRequestCountByDateAndLCIdAndStatus(LocalDate puDate, int lcId, PUCodeRequest.Status status);
}
