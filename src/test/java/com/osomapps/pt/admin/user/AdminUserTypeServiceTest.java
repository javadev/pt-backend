package com.osomapps.pt.admin.user;

import com.osomapps.pt.dictionary.DictionaryService;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
