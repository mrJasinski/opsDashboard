package com.opsDashboard.specialAccess;

import com.opsDashboard.utils.Country;
import com.opsDashboard.utils.TransportMethod;
import com.opsDashboard.vo.VehicleSource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class SAInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final SpecialAccessRepository specialAccessRepo;

    SAInitializer(final SpecialAccessRepository specialAccessRepo)
    {
        this.specialAccessRepo = specialAccessRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        this.specialAccessRepo.save(new SpecialAccess(
                Country.EE
                , new VehicleSource("UT28564")
                , "link"
                , SAReason.ADMIN_PROBLEM
                , "late PU date update"
                , TransportMethod.TRANSPORT
                , LocalDate.now()
                , SAStatus.WAITING_FOR_LOCAL_APPROVAL));

        this.specialAccessRepo.save(new SpecialAccess(
                Country.HR
                , new VehicleSource("OM15379")
                , "link"
                , SAReason.MISSING_DOCS
                , "docs not delivered to merchant"
                , TransportMethod.NOT_PICKED_UP
                , null
                , SAStatus.WAITING_FOR_HQ_APPROVAL));

    }
}
