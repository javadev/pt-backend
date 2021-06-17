package com.osomapps.pt.admin.program;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramResourceTest {

    @Mock private AdminProgramService adminProgramService;

    @InjectMocks private AdminProgramResource adminProgramResource;

    @Test
    public void findOne() {
        adminProgramResource.findOne(1L);
        verify(adminProgramService).findOne(eq(1L));
    }

    @Test
    public void create() {
        adminProgramResource.create(new ProgramRequestDTO());
        verify(adminProgramService).create(any(ProgramRequestDTO.class));
    }

    @Test
    public void update() {
        adminProgramResource.update(1L, new ProgramRequestDTO());
        verify(adminProgramService).update(eq(1L), any(ProgramRequestDTO.class));
    }

    @Test
    public void delete() {
        adminProgramResource.delete(1L);
        verify(adminProgramService).delete(anyLong());
    }
}
