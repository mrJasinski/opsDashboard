package com.opsDashboard.vehicle;

import com.opsDashboard.vo.MerchantSource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class VehicleInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final VehicleRepository vehicleRepo;

    VehicleInitializer(final VehicleRepository vehicleRepo)
    {
        this.vehicleRepo = vehicleRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        this.vehicleRepo.save(new Vehicle(
                "NE12345"
                , "VW"
                , "Golf"
                , new MerchantSource(1)));

        this.vehicleRepo.save(new Vehicle(
                "UK90876"
                , "VW"
                , "Golf"
                , new MerchantSource(1)));

        this.vehicleRepo.save(new Vehicle(
                "TR76543"
                , "VW"
                , "Golf"
                , new MerchantSource(1)));
    }

}
