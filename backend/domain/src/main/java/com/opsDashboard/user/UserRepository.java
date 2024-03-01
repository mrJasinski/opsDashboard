package com.opsDashboard.user;

import java.util.List;
import java.util.Optional;

interface UserRepository
{
    User save(User toSave);

    List<User> findAll();

    Optional<Integer> findIdByEmail(String email);

    Optional<User.Role> findRoleByUserId(int userId);
}
