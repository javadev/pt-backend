package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InUserPhotoTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new InUserPhoto(1L, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserPhoto inUserPhoto =
                new InUserPhoto()
                        .setId(1L)
                        .setCreated(null)
                        .setGoal_id(null)
                        .setFile_size(null)
                        .setInUsers(null);
        assertThat(inUserPhoto, notNullValue());
    }

    @Test
    public void getters() {
        InUserPhoto inUserPhoto = new InUserPhoto();
        inUserPhoto.getId();
        inUserPhoto.getCreated();
        inUserPhoto.getGoal_id();
        inUserPhoto.getFile_size();
        inUserPhoto.getInUsers();
        assertThat(inUserPhoto, notNullValue());
    }
}
