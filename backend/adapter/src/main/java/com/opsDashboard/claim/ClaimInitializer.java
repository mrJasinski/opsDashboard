package com.opsDashboard.claim;

import com.opsDashboard.vo.UserSource;
import com.opsDashboard.vo.VehicleSource;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class ClaimInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final ClaimRepository claimRepo;

    ClaimInitializer(ClaimRepository claimRepo)
    {
        this.claimRepo = claimRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        this.claimRepo.save(new Claim(
                1
                , "link"
                , ClaimStatus.WAITING_FOR_CE_DECISION
                , new VehicleSource("NE12345")
                , LocalDate.now(), new UserSource(1)));

        this.claimRepo.save(new Claim(
                1
                , "link"
                , ClaimStatus.WAITING_FOR_CE_DECISION
                , new VehicleSource("UK90876")
                , LocalDate.now(), new UserSource(1)));

        this.claimRepo.save(new Claim(
                1
                , "link"
                , ClaimStatus.WAITING_FOR_CE_DECISION
                , new VehicleSource("TR76543")
                , LocalDate.now(), new UserSource(2)));

    }
//    @Component
//class UserInitializer implements ApplicationListener<ContextRefreshedEvent>
//{
//    private final UserRepository userRepo;
//    private final PasswordEncoder encoder;
//
//    UserInitializer(final UserRepository userRepo, PasswordEncoder encoder)
//    {
//        this.userRepo = userRepo;
//        this.encoder = encoder;
//    }
//
//    @Override
//    public void onApplicationEvent(final ContextRefreshedEvent event)
//    {
//        this.userRepo.save(new User("tytus@example.com", this.encoder.encode("12345")));
//        this.userRepo.save(new User("roman@example.com", this.encoder.encode("12345")));
//
//        this.userRepo.save(new User("dm_cee@example.com", this.encoder.encode("12345")
//                , new User.Role(User.Role.Type.DM_CEE, Utils.ceeWithoutPL, Set.of(SAStatus.WAITING_FOR_LOCAL_APPROVAL, SAStatus.HQ_REJECTED))));
//    }
}
