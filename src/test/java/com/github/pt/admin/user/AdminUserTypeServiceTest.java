package com.github.pt.admin.user;

import com.github.pt.dictionary.DictionaryService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserTypeServiceTest {

    @Mock
    private InUserTypeRepository inUserTypeRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private AdminUserTypeService adminUserTypeService;

    @Test
    public void findAll() {
        when(inUserTypeRepository.findAll()).thenReturn(Arrays.asList(new InUserType()));
        adminUserTypeService.findAll();
    }

}
