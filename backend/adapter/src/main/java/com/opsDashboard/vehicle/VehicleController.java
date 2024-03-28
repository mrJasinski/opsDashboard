package com.opsDashboard.vehicle;

import com.opsDashboard.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(value = "/vehicle", params = {"stockId"})
    ResponseEntity<?> getVehicleByStockIdAsDto(@RequestParam(name = "stockId") String stockId)
    {
        return ResponseEntity.ok(this.vehicleService.getVehicleByStockIdAsDto(stockId));
    }
}
