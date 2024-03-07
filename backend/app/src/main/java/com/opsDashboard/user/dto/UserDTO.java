package com.opsDashboard.user.dto;

import com.opsDashboard.user.WorkLocation;

public class UserDTO
{
    private String email;
    private String password;
    private RoleDTO role;
    WorkLocation location;

    UserDTO()
    {
    }

    UserDTO(final String email)
    {
        this.email = email;
    }

    UserDTO(final String email, final String password)
    {
        this.email = email;
        this.password = password;
    }

    public UserDTO(final String email, final String password, final RoleDTO role)
    {
        this(email, password);
        this.role = role;
    }

    UserDTO(final String email, final String password, final RoleDTO role, final WorkLocation location)
    {
        this.email = email;
        this.password = password;
        this.role = role;
        this.location = location;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public RoleDTO getRole()
    {
        return this.role;
    }

    public WorkLocation getLocation()
    {
        return this.location;
    }
}
