package com.opsDashboard.full;

import com.opsDashboard.utils.Country;

import java.util.Optional;
import java.util.Set;

interface FullRefundRepository
{
    Optional<Integer> findCountByCountriesAndStatuses(Set<Country> countries, Set<FRStatus> frStatuses);
}
