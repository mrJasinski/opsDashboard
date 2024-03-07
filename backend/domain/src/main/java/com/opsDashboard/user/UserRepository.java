package com.opsDashboard.user;

import com.opsDashboard.utils.Country;

import java.util.List;
import java.util.Optional;

interface UserRepository
{
    User save(User toSave);

    List<User> findAll();
//    List<User> findAvailableByCountry(Country country);

    Optional<Integer> findIdByEmail(String email);

    Optional<User.Role> findRoleByUserId(int userId);

    Optional<User> findByEmail(String email);
    Optional<User> findAvailableByCountryAndStockId(Country country, String stockId);
//    Optional<User> findAvailableByCountryWithLowestClaimsCountByDate(Country country, LocalDate date);
}
