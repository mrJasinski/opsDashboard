package com.opsDashboard.specialAccess;

import com.opsDashboard.utils.Country;
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
                , "UT28564"
                , "link"
                , new SpecialAccess.Reason("Admin problems")
                , "late PU date update"
                , SpecialAccess.TransportMethod.TRANSPORT
                , LocalDate.now()
                , Status.WAITING_FOR_LOCAL_APPROVAL));

        this.specialAccessRepo.save(new SpecialAccess(
                Country.RS
                , "OM15379"
                , "link"
                , new SpecialAccess.Reason("Missing docs")
                , "docs not delivered to merchant"
                , SpecialAccess.TransportMethod.NOT_PICKED_UP
                , null
                , Status.WAITING_FOR_HQ_APPROVAL));

    }
}
