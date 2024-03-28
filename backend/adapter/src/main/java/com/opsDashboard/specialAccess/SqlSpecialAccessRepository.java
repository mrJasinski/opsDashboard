package com.opsDashboard.specialAccess;

import com.opsDashboard.utils.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
interface SqlSpecialAccessRepository extends SpecialAccessRepository, JpaRepository<SpecialAccess, Integer>
{
    @Override
    @Query(value = "SELECT COUNT(*)" +
            " FROM SpecialAccess sa" +
            " WHERE sa.country IN :countries  AND sa.status IN :statuses")
    Optional<Integer> findCountByCountriesAndStatuses(Set<Country> countries, Set<SAStatus> statuses);

    @Override
    @Query(value = " FROM SpecialAccess sa" +
            " WHERE sa.country IN :countries  AND sa.status IN :statuses")
    List<SpecialAccess> findByCountriesAndStatuses(Set<Country> countries, Set<SAStatus> statuses);

    @Override
    @Query(value = "FROM SpecialAccess sa " +
            "WHERE sa.vehicle.stockId = :stockId")
    Optional<SpecialAccess> findByStockId(String stockId);
}
