package com.osomapps.pt.admin.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserRepository;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.FastByteArrayOutputStream;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserProgramFileServiceTest {

    @Mock
    private DictionaryService dictionaryService;
    @Mock
    private InUserRepository inUserRepository;
    @InjectMocks
    private AdminUserProgramFileService adminUserProgramFileService;

    @Test(expected = ResourceNotFoundException.class)
    public void createXlsx_not_found() {
        adminUserProgramFileService.createXlsx(1L, new FastByteArrayOutputStream());
    }

    @Test
    public void createXlsx() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(new InUser());
        ProgramResponseDTO programResponseDTO = adminUserProgramFileService.createXlsx(
                1L, new FastByteArrayOutputStream());
        assertThat(programResponseDTO, notNullValue());
    }
}
