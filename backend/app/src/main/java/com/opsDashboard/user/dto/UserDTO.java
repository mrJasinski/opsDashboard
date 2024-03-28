package com.opsDashboard.user.dto;

import com.opsDashboard.user.WorkLocation;

public class UserDTO
{
    private String email;
    private String password;
    private RoleDTO role;
    private boolean isAvailable;
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

    public UserDTO(final String email, final String password, final RoleDTO role, final boolean isAvailable)
    {
        this(email, password, role);
        this.isAvailable = isAvailable;
    }

    UserDTO(final String email, final String password, final RoleDTO role, final WorkLocation location)
    {
        this.email = email;
        this.password = password;
        this.role = role;
        this.location = location;
    }

    UserDTO(final String email, final String password, final RoleDTO role, final boolean isAvailable, final WorkLocation location)
    {
        this(email, password, role, location);
        this.isAvailable = isAvailable;
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

     public boolean isAvailable()
    {
        return this.isAvailable;
    }
}
