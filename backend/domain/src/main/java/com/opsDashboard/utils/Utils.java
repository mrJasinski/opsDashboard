package com.opsDashboard.utils;

import com.opsDashboard.specialAccess.SAStatus;

import java.util.Set;

public class Utils
{
//    public static Set<Country.Name> balkans = Set.of(Country.BA, Country.HR, Country.ME, Country.MK, Country.RS);
//    public static Set<Country.Name> baltics = Set.of(Country.EE, Country.LT, Country.LV, Country.UA);
//    public static Set<Country.Name> czsk = Set.of(Country.CZ, Country.SK);
//    public static Set<Country.Name> ceeWithoutPL = Set.of(Na.BA, Country.HR, Country.ME, Country.MK, Country.RS, Country.EE, Country.LT, Country.LV, Country.UA, Country.CZ, Country.SK);

    public static Set<SAStatus> ongoingSAStatuses = Set.of(SAStatus.HQ_REJECTED, SAStatus.CREATED, SAStatus.WAITING_FOR_HQ_APPROVAL, SAStatus.WAITING_FOR_LOCAL_APPROVAL);
}
