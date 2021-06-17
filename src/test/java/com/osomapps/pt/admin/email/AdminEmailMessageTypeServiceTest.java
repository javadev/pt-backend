package com.osomapps.pt.admin.email;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.osomapps.pt.email.EmailMessageType;
import com.osomapps.pt.email.EmailMessageTypeRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminEmailMessageTypeServiceTest {

    @Mock private EmailMessageTypeRepository emailMessageTypeRepository;
    @InjectMocks private AdminEmailMessageTypeService adminEmailMessageTypeService;

    @Test
    public void findAll() {
        when(emailMessageTypeRepository.findAll(any(Sort.class)))
                .thenReturn(Arrays.asList(new EmailMessageType()));
        List<EmailMessageTypeResponseDTO> responseDTOs = adminEmailMessageTypeService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }
}
