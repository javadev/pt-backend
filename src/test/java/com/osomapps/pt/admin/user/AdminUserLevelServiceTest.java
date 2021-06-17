package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserLevelServiceTest {
    @InjectMocks private AdminUserLevelService adminUserLevelService;

    @Test
    public void findOne() {
        List<UserLevelResponseDTO> userLevelResponseDTOs = adminUserLevelService.findAll();
        assertThat(userLevelResponseDTOs.size(), equalTo(2));
    }
}
