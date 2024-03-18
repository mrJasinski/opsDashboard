package com.opsDashboard.full;

import com.opsDashboard.utils.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
interface SqlFullRefundRepository extends FullRefundRepository, JpaRepository<FullRefund, Integer>
{
    @Override
    @Query(value = "SELECT COUNT(*)" +
            " FROM FullRefund fr" +
            " JOIN Vehicle v ON v.stockId = fr.vehicle.stockId" +
            " JOIN Merchant m ON m.id = v.merchant.id" +
            " WHERE m.country IN :countries  AND fr.status IN :statuses")
    Optional<Integer> findCountByCountriesAndStatuses(Set<Country> countries, Set<FRStatus> statuses);
}
