package com.opsDashboard.specialAccess;

import com.opsDashboard.utils.Country;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface SpecialAccessRepository
{
    SpecialAccess save(SpecialAccess toSave);

    List<SpecialAccess> findAll();
    List<SpecialAccess> findByCountriesAndStatuses(Set<Country> countries, Set<SAStatus> statuses);

    Optional<SpecialAccess> findByStockId(String stockId);

    Optional<Integer> findCountByCountriesAndStatuses(Set<Country> countries, Set<SAStatus> statuses);


}
