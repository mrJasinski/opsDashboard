package com.opsDashboard.specialAccess;

import com.opsDashboard.user.UserService;
import com.opsDashboard.utils.Country;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class SpecialAccessService
{
    private final SpecialAccessRepository specialAccessRepo;
    private final UserService userService;

    SpecialAccessService(SpecialAccessRepository specialAccessRepo, UserService userService)
    {
        this.specialAccessRepo = specialAccessRepo;
        this.userService = userService;
    }

    List<SpecialAccess> getPendingSAByUserId(final int userId)
    {
        var role = this.userService.getRoleByUserIdAsDto(userId);

        var specials = this.specialAccessRepo.findAll();

        return specials
                .stream()
                .filter(s -> (role.getSaStatuses()).contains(s.getStatus()) && (role.getCountries()).contains(s.getCountry()))
                .toList();
    }

    SpecialAccess getSpecialAccessByStockId(final String stockId)
    {
        return this.specialAccessRepo.findByStockId(stockId)
                .orElseThrow(() -> new NoSuchElementException("Special access with given stock id not found!"));
    }

    void changeSAStatusByStockId(final String stockId, final boolean isApproved)
    {
        var sa = getSpecialAccessByStockId(stockId);

        sa.changeStatus(sa.getStatus(), isApproved);
        this.specialAccessRepo.save(sa);
    }

    public int getSACountByCountriesAndStatuses(final Set<Country> countries, final Set<SAStatus> sAStatuses)
    {
        return this.specialAccessRepo.findCountByCountriesAndStatuses(countries, sAStatuses)
                .orElse(0);
    }
}
