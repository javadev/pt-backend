package com.osomapps.pt.admin.user;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminUserLevelServiceTest {
    @InjectMocks
    private AdminUserLevelService adminUserLevelService;

    @Test
    public void findOne() {
        List<UserLevelResponseDTO> userLevelResponseDTOs = adminUserLevelService.findAll();
        assertThat(userLevelResponseDTOs.size(), equalTo(2));
    }

}
