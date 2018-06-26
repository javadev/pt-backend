package com.osomapps.pt.admin.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
