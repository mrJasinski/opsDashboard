package com.opsDashboard.user;

import com.opsDashboard.user.dto.RoleDTO;
import com.opsDashboard.user.dto.UserDTO;
import com.opsDashboard.utils.Country;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;

@Service
public class UserService
{
    private final UserRepository userRepo;

    UserService(final UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    public int getUserIdByEmail(final String email)
    {
        return this.userRepo.findIdByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User id for given email not found!"));
    }

    User.Role getRoleByUserId(final int userId)
    {
        return this.userRepo.findRoleByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("Role for given user id not found!"));
    }

    public RoleDTO getRoleByUserIdAsDto(final int userId)
    {
        return toDto(getRoleByUserId(userId));
    }

    private RoleDTO toDto(final User.Role role)
    {
        return new RoleDTO(role.getType().name(), role.unwrapCountries(role.getCountries()));
//        return new RoleDTO(role.getType().name(), role.getCountries(), role.getSaStatuses());
    }

    private UserDTO toDto(final User user)
    {
        return new UserDTO(user.getEmail(), user.getPassword(), toDto(user.getRole()));
    }

    User getUserByEmail(final String email)
    {
        return this.userRepo.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User with given email not found!"));
    }

    public UserDTO getUserByEmailAsDto(final String email)
    {
        return toDto(getUserByEmail(email));
    }

    void startWorkday(final String email, final WorkLocation location)
    {
        var user = getUserByEmail(email);

        if (user.getWorkdays().stream().filter(w -> (w.getWorkday()).equals(LocalDate.now())).toList().isEmpty())
            user.addWorkday(location, LocalDate.now(), LocalTime.now());

        if (!user.getWorkdays().stream().filter(w -> (w.getWorkday()).equals(LocalDate.now())).toList().isEmpty())
            user.restartWorkday(LocalDate.now(), LocalTime.now());

        this.userRepo.save(user);
    }

    void stopWorkday(final String email)
    {
        var user = getUserByEmail(email);

        var workday = user.getWorkdays().stream().filter(w -> (w.getWorkday()).equals(LocalDate.now())).toList().get(0);

        var startTime = workday.getStartTime();

        if (!(workday.getRestartTime()).equals(workday.getStartTime()))
            startTime = workday.getRestartTime();

        var minutes = (int)Duration.between(startTime, LocalTime.now()).toMinutes();

        workday.addWorkTimeMins(minutes);

        this.userRepo.save(user);
    }

//    TODO temporal name
    User getUserWithPreviouslyAssignedStockId(final Country country, final String stockId)
    {
        return this.userRepo.findAvailableByCountryAndStockId(country, stockId)
            .orElseThrow(() -> new NoSuchElementException("Not found!"));
    }

    User getUserWithLowestDailyAssignedClaimsByCountryAndDate(final Country country, final LocalDate date)
    {
        return this.userRepo.findAvailableWithLowestDailyAssignedClaimByCountryAndDate(country, date)
                .orElseThrow(() -> new NoSuchElementException("Not found!"));
    }

//    private User getUserWithLowestDailyAssignedTicketsByCountryAndDate(final Country country, final LocalDate date)
//    {
//        return this.userRepo.findAvailableWithLowestDailyAssignedTicketByCountryAndDate(country, date);
//    }
//
//    UserDTO getUserToAssignClaimToAsDto(final Country country, final String stockId, final LocalDate date)
//    {
//        User user;
//
//        try
//        {
//            user = getUserWithPreviouslyAssignedStockId(country, stockId);
//        }
//        catch (NoSuchElementException ex)
//        {
//            try
//            {
//                user = getUserWithLowestDailyAssignedClaimsByCountryAndDate(country, date);
//            }
//            catch (NoSuchElementException e)
//            {
//                user = getUserWithLowestDailyAssignedTicketsByCountryAndDate(country, date);
//            }
//        }
//
//        return toDto(user);
//    }





//    User getUserToAssignClaimToByCountryAndStockId(final Country country, final String stockId)
//    {
//        return this.userRepo.findAvailableByCountryStockId(country, stockId)
//                .orElseGet(() -> getAvailableUserByCountryWithLowestClaimsCountByDate(country, LocalDate.now()));
//    }
}
