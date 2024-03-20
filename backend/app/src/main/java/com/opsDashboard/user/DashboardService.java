package com.opsDashboard.user;

import com.opsDashboard.claim.ClaimService;
import com.opsDashboard.full.FullRefundService;
import com.opsDashboard.specialAccess.SpecialAccessService;
import com.opsDashboard.user.dto.Dashboard;
import com.opsDashboard.utils.Utils;
import org.springframework.stereotype.Service;

@Service
class DashboardService
{
    private final UserService userService;
    private final ClaimService claimService;
    private final SpecialAccessService sAService;
    private final FullRefundService fRService;

    DashboardService(UserService userService
            , ClaimService claimService
            , SpecialAccessService sAService
            , FullRefundService fRService)
    {
        this.userService = userService;
        this.claimService = claimService;
        this.sAService = sAService;
        this.fRService = fRService;
    }

    Dashboard getDashboardByUserId(final int userId)
    {
        var role = this.userService.getRoleByUserId(userId);

        var claimsCount = this.claimService.getClaimCountByCountriesAndStatuses(role.getCountriesSet(), role.getClaimStatusesSet());
        var pendingSACount = this.sAService.getSACountByCountriesAndStatuses(role.getCountriesSet(), role.getSAStatusesSet());
        var ongoingSACount = this.sAService.getSACountByCountriesAndStatuses(role.getCountriesSet(), Utils.ongoingSAStatuses);
        var fRCount = this.fRService.getFRCountByCountriesAndStatuses(role.getCountriesSet(), role.getFRStatusesSet());

        return new Dashboard(claimsCount, pendingSACount, ongoingSACount, fRCount);
    }
}
