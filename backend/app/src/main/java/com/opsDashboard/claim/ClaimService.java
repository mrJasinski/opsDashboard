package com.opsDashboard.claim;

import com.opsDashboard.claim.dto.ClaimDTO;
import com.opsDashboard.claim.dto.ClaimWriteModel;
import com.opsDashboard.merchant.MerchantService;
import com.opsDashboard.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService
{
    private final ClaimRepository claimRepo;
    private final UserService userService;
    private final MerchantService merchantService;

    ClaimService(ClaimRepository claimRepo, final UserService userService, final MerchantService merchantService)
    {
        this.claimRepo = claimRepo;
        this.userService = userService;
        this.merchantService = merchantService;
    }

    void assignClaim(Claim claim)
    {
//        var country = this.merchantService.getCountryByMerchantId(claim.get);
//
//        var availableOps = this.userService.getAvailableOpsByCountryAsDto(country);
//
//        var lowestClaims = availableOps.get(0);

//        for (UserDTO o : availableOps)
//        {
//            if (o.getClaimsStockIds().contains(claim.getStockId()))
//            {
//                lowestClaims = o;
//
//                break;
//            }
//        }
////                wykonuje się tylko jeśli nie został przypisany w powyższej pętli
//        availableOps.forEach(o ->
//        {
//            if (o.getAssignedClaimsCountByDate(LocalDate.now()) < lowestClaims.getAssignedClaimsCountByDate(LocalDate.now()))
//                lowestClaims = 0;
//        });
//
//
//
//        lowestClaims.addClaim(toDto(claim));
    }

    List<ClaimDTO> getClaimByUserIdAsDto(final int userId)
    {
        var userRole = this.userService.getRoleByUserIdAsDto(userId);

        var claims = this.claimRepo.findAll();

        var dtos = claims
                .stream()
                .map(this::toDto)
                .toList();

        //        TODO

//        return dtos
//                .stream()
//                .filter(c -> userRole.getCountries().contains(c.getCountry()) && userRole.getClaimStatuses().contains(c.getStatus()))
//                .toList();

        return null;
    }

    ClaimDTO toDto(Claim claim)
    {
        return new ClaimDTO(
//                TODO
        );
    }

    public void save(final ClaimWriteModel toSave)
    {
//        TODO placeholder - numer kolejny claimu dla danego stockId z bazy
        var number = 0;

        this.claimRepo.save(new Claim(
                number
                , toSave.getLink()
                , toSave.getStatus()
                , toSave.getVehicle()
        ));
    }

    List<Claim> getAllClaims()
    {
        return this.claimRepo.findAll();
    }
}
