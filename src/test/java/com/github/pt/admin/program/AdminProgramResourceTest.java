package com.github.pt.admin.program;

import com.github.pt.admin.exercise.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramResourceTest {

    @Mock
    private AdminProgramService adminProgramService;    
    
    @InjectMocks
    private AdminProgramResource adminProgramResource;

    @Test
    public void create() {
        adminProgramResource.create(new ProgramRequestDTO());
        verify(adminProgramService).create(any(ProgramRequestDTO.class));
    }

    public void delete() throws Exception {
        adminProgramResource.delete(1L);
        verify(adminProgramService).delete(anyLong());
    }
}