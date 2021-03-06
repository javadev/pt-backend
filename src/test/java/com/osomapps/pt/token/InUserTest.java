package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.Test;

public class InUserTest {
    @Test
    public void create() {
        assertThat(new InUser(), notNullValue());
    }

    @Test
    public void createAllArgs() {
        assertThat(
                new InUser(
                        1L,
                        LocalDateTime.now(),
                        "d_sex",
                        20F,
                        LocalDate.now(),
                        2F,
                        3F,
                        "d_level",
                        null,
                        LocalDateTime.now(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        Collections.emptyList(),
                        null,
                        null,
                        null),
                notNullValue());
    }

    @Test
    public void setters() {
        InUser inUser = new InUser();
        inUser.setId(1L);
        inUser.setCreated(LocalDateTime.MAX);
        inUser.setD_sex("");
        inUser.setAge(0F);
        inUser.setBirthday(LocalDate.MAX);
        inUser.setHeight(Float.MAX_VALUE);
        inUser.setWeight(Float.MAX_VALUE);
        inUser.setD_level("");
        inUser.setAvatar_dataurl("");
        inUser.setUpdated(LocalDateTime.MAX);
        inUser.setInUserFacebooks(Collections.emptyList());
        inUser.setInUserLogins(Collections.emptyList());
        inUser.setInUserLogouts(Collections.emptyList());
        inUser.setInPrograms(Collections.emptyList());
        inUser.setInUserWeights(Collections.emptyList());
        inUser.setInUserCertificates(Collections.emptyList());
        inUser.setInUserEmails(Collections.emptyList());
        inUser.setInUserType(null);
        inUser.setInUserGoals(Collections.emptyList());
        inUser.setInUserPhotos(Collections.emptyList());
        assertThat(inUser, notNullValue());
    }

    @Test
    public void getters() {
        InUser inUser = new InUser();
        inUser.getId();
        inUser.getCreated();
        inUser.getD_sex();
        inUser.getAge();
        inUser.getBirthday();
        inUser.getHeight();
        inUser.getWeight();
        inUser.getD_level();
        inUser.getAvatar_dataurl();
        inUser.getUpdated();
        inUser.getInUserFacebooks();
        inUser.getInUserLogins();
        inUser.getInUserLogouts();
        inUser.getInPrograms();
        inUser.getInUserWeights();
        inUser.getInUserCertificates();
        inUser.getInUserEmails();
        inUser.getInUserType();
        inUser.getInUserGoals();
        inUser.getInUserPhotos();
        assertThat(inUser, notNullValue());
    }
}
