package com.opsDashboard.user;

import com.opsDashboard.utils.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    @Query(value = "SELECT * FROM users WHERE users.id = " +
            "    (SELECT claims.user_id" +
            "             FROM claims " +
            "             WHERE claims.assigned_date = :assignDate AND claims.user_id IN " +
            "                (SELECT users.id AS userIds " +
            "                 FROM users " +
            "                 JOIN roles ON roles.id = users.role_id " +
            "                 WHERE users.is_available = TRUE AND roles.countries LIKE %:country%) " +
            "            GROUP BY claims.user_id" +
            "            ORDER BY COUNT(*) LIMIT 1)", nativeQuery = true)
    Optional<User> findAvailableWithLowestDailyAssignedClaimByCountryAndDate(Country country, LocalDate assignDate);


    @Override
    @Query(value = "SELECT MIN(claimsCount) FROM (SELECT COUNT(*) AS claimsCount FROM Claim c WHERE c.assignedDate = :assignDate GROUP BY c.assignedAgent)")
    Integer findLowest(LocalDate assignDate);

    @Override
    @Query(value = "FROM User u WHERE u.isAvailable = TRUE AND u.role.countries LIKE %:country% AND u.role.type = :type")
    List<User> findAvailableByCountryAndRoleType(Country country, User.Role.Type type);
}
