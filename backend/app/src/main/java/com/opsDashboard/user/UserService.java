package com.opsDashboard.user;

import com.opsDashboard.user.dto.RoleDTO;
import com.opsDashboard.user.dto.UserDTO;
import com.opsDashboard.utils.Country;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
        return new RoleDTO(
                role.getType().name()
                , role.unwrapCountries(role.getCountries())
                , role.unwrapClaimStatuses(role.getClaimStatuses())
                , role.unwrapSAStatuses(role.getSAStatuses())
                , role.unwrapFRStatuses(role.getFRStatuses()));
    }

    private UserDTO toDto(final User user)
    {
        return new UserDTO(user.getEmail(), user.getPassword(), toDto(user.getRole()), user.isAvailable());
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

    int startWorkday(final String email, final WorkLocation location)
    {
        var user = getUserByEmail(email);

        try
        {
            var workday = user.getWorkDayByDate(LocalDate.now());
            user.restartWorkday(workday, LocalTime.now());
        }
        catch (NoSuchElementException ex)
        {
            user.addWorkday(location, LocalDate.now(), LocalTime.now());
        }

        this.userRepo.save(user);

        return user.getWorkDayByDate(LocalDate.now()).getWorkTimeMins();
    }

    void stopWorkday(final String email)
    {
        var user = getUserByEmail(email);

        var workday = user.getWorkDayByDate(LocalDate.now());

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

    public int getAgentIdToAssignTicketToByUserIds(final List<Integer> userIds)
    {
        var users = this.userRepo.findAvailableByIds(userIds);

//        if no available users id set to zero which means no user
        var result = 0;

        if (users.size() == 1)
        {
            result = users.get(0).getId();
        }

        if ((users.size() > 1))
        {
//            get agent with lowest daily assigned tickets
            result = getUserWithLowestDailyAssignedTicketsByIds(users.stream().map(User::getId).toList()).getId();

        }


        return result;
    }

    private User getUserWithLowestDailyAssignedTicketsByIds(final List<Integer> userIds)
    {
        return this.userRepo.findWithLowestDailyAssignedTicketsByIdsAndDate(userIds, LocalDate.now())
                .orElse(
// TODO
                );
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
