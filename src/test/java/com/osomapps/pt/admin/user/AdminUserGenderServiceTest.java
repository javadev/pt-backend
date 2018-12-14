package com.osomapps.pt.admin.user;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserGenderServiceTest {
    @InjectMocks
    private AdminUserGenderService adminUserGenderService;

    @Test
    public void findOne() {
        List<UserGenderResponseDTO> userGenderResponseDTOs = adminUserGenderService.findAll();
        assertThat(userGenderResponseDTOs.size(), equalTo(2));
    }

}
