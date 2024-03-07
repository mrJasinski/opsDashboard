package com.opsDashboard.user;

import com.opsDashboard.specialAccess.SAStatus;
import com.opsDashboard.utils.Country;
import com.opsDashboard.utils.Utils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class UserInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    UserInitializer(final UserRepository userRepo, PasswordEncoder encoder)
    {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        var countries = Set.of(Country.EE, Country.HR);

       this.userRepo.save(new User(
               "dm_cee@example.com"
               , this.encoder.encode("12345")
               , new User.Role(User.Role.Type.DM_CEE , countries)
               , true));
//               , new User.Role(User.Role.Type.DM_CEE , Utils.ceeWithoutPL, Set.of(SAStatus.WAITING_FOR_LOCAL_APPROVAL, SAStatus.HQ_REJECTED))));
    }
}

