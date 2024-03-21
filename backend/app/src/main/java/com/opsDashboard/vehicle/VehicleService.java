package com.opsDashboard.vehicle;

import com.opsDashboard.vehicle.dto.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
class VehicleService
{
    private final VehicleRepository vehicleRepo;

    VehicleService(final VehicleRepository vehicleRepo)
    {
        this.vehicleRepo = vehicleRepo;
    }

    VehicleDTO getVehicleByStockIdAsDto(final String stockId)
    {
        return toDto(getVehicleByStockId(stockId));
    }

    private VehicleDTO toDto(final Vehicle stockId)
    {
        return new VehicleDTO(
//                TODO
        );
    }

    private Vehicle getVehicleByStockId(final String stockId)
    {
//        TODO check czy stock id jest poprawny?

        return this.vehicleRepo.findByStockId(stockId)
                .orElseThrow(() -> new NoSuchElementException("Vehicle with given stock id not found!"));
    }
}
