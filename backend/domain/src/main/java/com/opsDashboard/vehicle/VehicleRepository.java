package com.opsDashboard.vehicle;

import java.util.Optional;

interface VehicleRepository
{
    Vehicle save(Vehicle toSave);

    Optional<Vehicle> findByStockId(String stockId);
}
