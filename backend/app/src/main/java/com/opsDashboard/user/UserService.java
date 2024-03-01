package com.opsDashboard.user;

import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        return new RoleDTO(role.getCountries(), role.getSaStatuses());
    }
}
