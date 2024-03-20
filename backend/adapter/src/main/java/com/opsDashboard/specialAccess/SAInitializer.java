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
                , SpecialAccess.Reason.ADMIN_PROBLEM
                , "late PU date update"
                , SpecialAccess.TransportMethod.TRANSPORT
                , LocalDate.now()
                , SAStatus.WAITING_FOR_LOCAL_APPROVAL));

        this.specialAccessRepo.save(new SpecialAccess(
                Country.HR
                , "OM15379"
                , "link"
                , SpecialAccess.Reason.MISSING_DOCS
                , "docs not delivered to merchant"
                , SpecialAccess.TransportMethod.NOT_PICKED_UP
                , null
                , SAStatus.WAITING_FOR_HQ_APPROVAL));

    }
}
