package com.osomapps.pt.reportweight;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportWeightResourceTest {

    @Mock
    private ReportWeightService reportWeightService;    

    @InjectMocks
    private ReportWeightResource reportWeightResource;

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
