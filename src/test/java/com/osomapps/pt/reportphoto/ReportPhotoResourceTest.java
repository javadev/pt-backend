package com.osomapps.pt.reportphoto;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReportPhotoResourceTest {

    @Mock
    private ReportPhotoService reportPhotoService;    

    @InjectMocks
    private ReportPhotoResource reportPhotoResource;

    @Test
    public void findAll() throws Exception {
        reportPhotoResource.findAll("");
        verify(reportPhotoService).findAll(eq(""));
    }

    @Test
    public void create() {
        reportPhotoResource.create("", new PhotoRequestDTO());
        verify(reportPhotoService).create(eq(""), any(PhotoRequestDTO.class));
    }

}
