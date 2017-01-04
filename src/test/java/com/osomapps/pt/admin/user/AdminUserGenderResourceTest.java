package com.osomapps.pt.admin.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserGenderResourceTest {

    @Mock
    private AdminUserGenderService userGenderService;
    @InjectMocks
    private AdminUserGenderResource adminUserGenderResource;

    @Test
    public void findAll() {
        adminUserGenderResource.findAll();
        verify(userGenderService).findAll();
    }
}
