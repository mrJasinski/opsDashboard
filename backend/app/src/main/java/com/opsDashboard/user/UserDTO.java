package com.opsDashboard.user;

public class UserDTO
{
    private String email;

    UserDTO()
    {
    }

    UserDTO(final String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }
}
