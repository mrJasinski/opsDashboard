package com.opsDashboard.user;

import com.opsDashboard.utils.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer>
{
    @Override
    @Query(value = "SELECT u.role FROM User u WHERE u.id = :userId")
    Optional<User.Role> findRoleByUserId(int userId);

    @Override
    @Query(value = "SELECT u.id FROM User u WHERE u.email = :email")
    Optional<Integer> findIdByEmail(String email);

    @Override
    @Query(value = "FROM User u WHERE u.isAvailable = TRUE AND u.role.countries LIKE %:country% " +
            "AND :stockId IN (SELECT c.vehicle.stockId FROM Claim c WHERE c.assignedAgent.id = u.id)")
    Optional<User> findAvailableByCountryAndStockId(Country country, String stockId);

//    chodzi o to żeby ustalić który user ma najmniej claimów przypisanych w danym dniu
//    przy czym pewnie należy zawęzić jego rolę

//    @Override
////    @Query(value = "FROM User u WHERE u.isAvailable = TRUE AND :country IN u.role.countries " +
////            "AND ")
//    @Query
//    Optional<User> findAvailableByCountryWithLowestClaimsCountByDate(Country country, LocalDate date);
}
