package com.osomapps.pt.admin.user;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserGenderResourceTest {

    @Mock private AdminUserGenderService userGenderService;
    @InjectMocks private AdminUserGenderResource adminUserGenderResource;

    @Test
    public void findAll() {
        adminUserGenderResource.findAll();
        verify(userGenderService).findAll();
    }
}
