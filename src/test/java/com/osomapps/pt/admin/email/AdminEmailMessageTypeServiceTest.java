package com.osomapps.pt.admin.email;

import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
public class AdminEmailMessageTypeServiceTest {

    @Mock
    private EmailMessageTypeRepository emailMessageTypeRepository;
    @InjectMocks
    private AdminEmailMessageTypeService adminEmailMessageTypeService;

    @Test
    public void findAll() {
        when(emailMessageTypeRepository.findAll(any(Sort.class))).thenReturn(
                Arrays.asList(new EmailMessageType()));
        List<EmailMessageTypeResponseDTO> responseDTOs = adminEmailMessageTypeService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

}
