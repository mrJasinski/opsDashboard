package com.opsDashboard.merchant;

import com.opsDashboard.utils.Country;

class Merchant
{
    private int id;
    private Country country;
    private String email;
    private String name;
    private String address;
    private String phone;

    Merchant()
    {
    }

    Merchant(final Country country, final String email, final String name, final String address, final String phone)
    {
        this.country = country;
        this.email = email;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId()
    {
        return this.id;
    }

    public Country getCountry()
    {
        return this.country;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getPhone()
    {
        return this.phone;
    }
}
