package com.opsDashboard.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlVehicleRepository extends VehicleRepository, JpaRepository<Vehicle, Integer>
{
}
