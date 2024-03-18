package com.opsDashboard.full;

import com.opsDashboard.utils.Country;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FullRefundService
{
    private final FullRefundRepository fullRefundRepo;

    FullRefundService(FullRefundRepository fullRefundRepo)
    {
        this.fullRefundRepo = fullRefundRepo;
    }


    public int getFRCountByCountriesAndStatuses(final Set<Country> countries, final Set<FRStatus> frStatuses)
    {
        return this.fullRefundRepo.findCountByCountriesAndStatuses(countries, frStatuses)
                .orElse(0);
    }
}
