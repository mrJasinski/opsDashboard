package com.opsDashboard.user;

import com.opsDashboard.utils.Country;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface UserRepository
{
    User save(User toSave);

    List<User> findAll();

    Optional<Integer> findIdByEmail(String email);

    Optional<User.Role> findRoleByUserId(int userId);

    Optional<User> findByEmail(String email);
    Optional<User> findAvailableByCountryAndStockId(Country country, String stockId);
    Optional<User> findAvailableWithLowestDailyAssignedClaimByCountryAndDate(Country country, LocalDate assignDate);
    Optional<User> findWithLowestDailyAssignedTicketsByIdsAndDate(List<Integer> userIds, LocalDate assignDate);

//    TODO test
    Integer findLowest(LocalDate assignDate);

    List<User> findAvailableByCountryAndRoleType(Country country, User.Role.Type type);
    List<User> findAvailableByIds(List<Integer> userIds);


}
