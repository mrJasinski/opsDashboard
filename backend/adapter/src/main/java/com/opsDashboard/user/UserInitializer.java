package com.opsDashboard.user;

import com.opsDashboard.specialAccess.Status;
import com.opsDashboard.utils.Utils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
class UserInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final UserRepository userRepo;

    UserInitializer(final UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        this.userRepo.save(new User("tytus@example.com", "12345"));
        this.userRepo.save(new User("roman@example.com", "54321"));

        this.userRepo.save(new User("dm_cee@example.com", "54321"
                , new User.Role(User.Role.Type.DM_CEE, Utils.ceeWithoutPL, Set.of(Status.WAITING_FOR_LOCAL_APPROVAL, Status.HQ_REJECTED))));
    }
}

