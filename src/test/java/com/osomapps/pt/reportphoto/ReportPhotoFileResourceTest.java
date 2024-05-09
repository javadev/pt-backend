package com.osomapps.pt.reportphoto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.OutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class ReportPhotoFileResourceTest {

    @Mock private ReportPhotoFileService reportPhotoFileService;

    @InjectMocks private ReportPhotoFileResource reportPhotoFileResource;

    @Test
    public void findOne() throws Exception {
        when(reportPhotoFileService.findOne(eq("1"), eq(1L), eq(""), any(OutputStream.class)))
                .thenReturn(new ReportPhotoFileDTO());
        reportPhotoFileResource.findOne("1", 1L, "", new MockHttpServletResponse());
        verify(reportPhotoFileService).findOne(eq("1"), eq(1L), eq(""), any(OutputStream.class));
    }

    @Test(expected = IOException.class)
    public void findOne_with_error() throws Exception {
        when(reportPhotoFileService.findOne(eq("1"), eq(1L), eq(""), any(OutputStream.class)))
                .thenThrow(new IOException());
        reportPhotoFileResource.findOne("1", 1L, "", new MockHttpServletResponse());
    }
}
