package com.opsDashboard.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class UserUnitTest
{
    @Test
    void getWorkDayByDate()
    {
//        given
        var toTest = new User("foo", "bar");
        toTest.addWorkday(WorkLocation.HOME, LocalDate.now().plusDays(1), LocalTime.now());

//        when
        var result = catchThrowable(() -> toTest.getWorkDayByDate(LocalDate.now()));

//        then
        assertThat(result)
                .isInstanceOf(NoSuchElementException.class);

    }

//    Workday getWorkDayByDate(final LocalDate date)
//    {
//        return this.workdays
//                .stream()
//                .filter(w -> (w.getWorkday()).equals(date))
//                .toList()
//                .get(0);
//    }

}