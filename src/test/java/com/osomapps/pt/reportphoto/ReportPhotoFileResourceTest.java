package com.osomapps.pt.reportphoto;

import java.io.IOException;
import java.io.OutputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class ReportPhotoFileResourceTest {

    @Mock
    private ReportPhotoFileService reportPhotoFileService;

    @InjectMocks
    private ReportPhotoFileResource reportPhotoFileResource;

    @Test
    public void findOne() throws Exception {
        when(reportPhotoFileService.findOne(eq("1"), eq(1L), eq(""), any(OutputStream.class)))
                .thenReturn(new ReportPhotoFileDTO());
        reportPhotoFileResource.findOne("1", 1L, "", new MockHttpServletResponse());
        verify(reportPhotoFileService).findOne(eq("1"), eq(1L), eq(""), any(OutputStream.class));
    }

    @Test
    public void findOne_with_error() throws Exception {
        when(reportPhotoFileService.findOne(eq("1"), eq(1L), eq(""), any(OutputStream.class)))
                .thenThrow(new IOException());
        assertThrows(IOException.class, () -> {reportPhotoFileResource.findOne("1", 1L, "", new MockHttpServletResponse());});
    }
}
