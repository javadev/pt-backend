package com.osomapps.pt.admin.user;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminUserGenderServiceTest {
    @InjectMocks
    private AdminUserGenderService adminUserGenderService;

    @Test
    public void findOne() {
        List<UserGenderResponseDTO> userGenderResponseDTOs = adminUserGenderService.findAll();
        assertThat(userGenderResponseDTOs.size(), equalTo(2));
    }

}
