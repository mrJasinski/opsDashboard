package com.opsDashboard.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
