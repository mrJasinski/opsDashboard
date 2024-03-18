package com.opsDashboard.user.dto;

public class UserLoggedDTO
{
    private String email;
    private String token;
    private int minutesLeft;

    public UserLoggedDTO(final String email, final String token, final int minutesLeft)
    {
        this.email = email;
        this.token = token;
        this.minutesLeft = minutesLeft;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getToken()
    {
        return this.token;
    }

    public int getMinutesLeft()
    {
        return this.minutesLeft;
    }
}
