package com.opsDashboard.vehicle;

import com.opsDashboard.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class VehicleController
{
    private final VehicleService vehicleService;
    private final JwtService jwtService;

    VehicleController(VehicleService vehicleService, JwtService jwtService)
    {
        this.vehicleService = vehicleService;
        this.jwtService = jwtService;
    }

    @GetMapping("/vehicle/{stockId}")
    ResponseEntity<?> getVehicleByStockIdAsDto(HttpServletRequest request, @PathVariable String stockId)
    {
        return ResponseEntity.ok(this.vehicleService.getVehicleByStockIdAsDto(stockId));
    }

//    @GetMapping(value = "/specialAccess", params = {"filter"})
//    ResponseEntity<?> getSpecialAccess(HttpServletRequest request, @RequestParam(name = "filter") SAFilter filter)
//    {
////        type has options - pending/ongoing/all/resolved
//        var specials = this.sAService.getSAByUserIdAndFilterAsDto(this.jwtService.getUserIdFromToken(request), filter);
//
//        return ResponseEntity.ok(specials);
//    }
}
