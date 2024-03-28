package com.opsDashboard.specialAccess;

import com.opsDashboard.specialAccess.dto.SpecialAccessDTO;
import com.opsDashboard.user.UserService;
import com.opsDashboard.utils.Country;
import com.opsDashboard.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.*;

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

        return this.specialAccessRepo.findByCountriesAndStatuses(role.getCountries(), role.getSAStatuses());
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

    Map<SpecialAccess, Boolean> getSAByUserIdAndFilter(final int userId, final SAFilter filter)
    {
        var role = this.userService.getRoleByUserIdAsDto(userId);

        var roleStatuses = role.getSAStatuses();

//        treated as default filter - PENDING
        var statuses = roleStatuses;

        switch (filter)
        {
            case ONGOING -> statuses = Utils.ongoingSAStatuses;
            case ALL -> statuses = Set.of(SAStatus.values());
            case RESOLVED -> statuses = Set.of(SAStatus.GRANTED, SAStatus.LOCAL_REJECTED);
        }

        var specials = this.specialAccessRepo.findByCountriesAndStatuses(role.getCountries(), statuses);

        var map = new HashMap<SpecialAccess, Boolean>();

        specials.forEach(sa ->
        {
            var isPending = roleStatuses.contains(sa.getStatus());

            map.put(sa, isPending);
        });

        return map;
    }

    List<SpecialAccessDTO> getSAByUserIdAndFilterAsDto(final int userId, final SAFilter filter)
    {
        var result = new ArrayList<SpecialAccessDTO>();

        this.getSAByUserIdAndFilter(userId, filter).forEach((k, v) -> result.add(this.toDto(k, v)));
                 
        return result;
    }

    SpecialAccessDTO toDto(final SpecialAccess sa, final boolean isPending)
    {
        return new SpecialAccessDTO(
                sa.getCountry()
                , sa.getVehicle().getStockId()
                , sa.getLink()
                , sa.getReason()
                , sa.getExplanation()
                , sa.getTransportMethod()
                , sa.getPickupDate()
                , sa.getStatus()
                , isPending
        );
    }
}
