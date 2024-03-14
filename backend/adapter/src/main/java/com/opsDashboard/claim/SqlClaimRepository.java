package com.opsDashboard.claim;

import com.opsDashboard.utils.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
interface SqlClaimRepository extends ClaimRepository, JpaRepository<Claim, Integer>
{
    @Override
    @Query(value = "SELECT COUNT(*)" +
            " FROM Claim c" +
            " JOIN Vehicle v ON v.stockId = c.vehicle.stockId" +
            " JOIN Merchant m ON m.id = v.merchant.id" +
            " WHERE m.country IN :countries  AND c.status IN :statuses")
    Optional<Integer> findClaimCountByCountriesAndStatuses(Set<Country> countries, Set<ClaimStatus> statuses);
}
