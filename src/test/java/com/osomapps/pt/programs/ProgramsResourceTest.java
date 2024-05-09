package com.osomapps.pt.programs;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProgramsResourceTest {

    @Mock private ProgramService programService;

    @InjectMocks private ProgramsResource programsResource;

    @Test
    public void findAll() throws Exception {
        programsResource.findAll("");
        verify(programService).getExamples(eq(""));
    }
}
