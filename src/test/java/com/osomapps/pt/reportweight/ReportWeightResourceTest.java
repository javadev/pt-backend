package com.osomapps.pt.reportweight;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportWeightResourceTest {

    @Mock private ReportWeightService reportWeightService;

    @InjectMocks private ReportWeightResource reportWeightResource;

    @Test
    public void findAll() throws Exception {
        reportWeightResource.findAll("");
        verify(reportWeightService).findAll(eq(""));
    }

    @Test
    public void create() {
        reportWeightResource.create("", new WeightRequestDTO());
        verify(reportWeightService).create(eq(""), any(WeightRequestDTO.class));
    }
}
