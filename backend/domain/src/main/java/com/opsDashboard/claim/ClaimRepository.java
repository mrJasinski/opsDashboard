package com.opsDashboard.claim;

import com.opsDashboard.utils.Country;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface ClaimRepository
{
    Claim save(Claim toSave);

    List<Claim> findAll();

    Optional<Integer> findClaimCountByCountriesAndStatuses(Set<Country> countries, Set<ClaimStatus> statuses);
}
