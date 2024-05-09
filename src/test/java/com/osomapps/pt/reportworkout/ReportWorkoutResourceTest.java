package com.osomapps.pt.reportworkout;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportWorkoutResourceTest {

    @Mock private ReportWorkoutService reportWorkoutService;

    @InjectMocks private ReportWorkoutResource reportWorkoutResource;

    @Test
    public void findAll() throws Exception {
        reportWorkoutResource.findAll("");
        verify(reportWorkoutService).findAll(eq(""));
    }

    @Test
    public void create() {
        reportWorkoutResource.create("", new WorkoutReportRequestDTO());
        verify(reportWorkoutService).create(eq(""), any(WorkoutReportRequestDTO.class));
    }
}
