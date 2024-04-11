package com.opsDashboard.vehicle;

import com.opsDashboard.security.JwtService;
import com.opsDashboard.vehicle.dto.PUCodeRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/requestPUCode")
    ResponseEntity<?> requestPUCode(@RequestBody PUCodeRequestDTO request)
    {
        this.vehicleService.requestPUCode(request);

//        TODO
        return ResponseEntity.ok("PU Code!");
    }
}
