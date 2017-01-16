package com.osomapps.pt.reportphoto;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("api/v1/report-photo-file")
class ReportPhotoFileResource {

    private final ReportPhotoFileService reportPhotoFileService;

    @Autowired
    ReportPhotoFileResource(ReportPhotoFileService reportPhotoFileService) {
        this.reportPhotoFileService = reportPhotoFileService;
    }

    @GetMapping("{id}/{fileName}")
    @ResponseBody
    void findOne(@RequestHeader(value = "X-Token") String token,
            @PathVariable Long id, @PathVariable String fileName, HttpServletResponse response)
            throws IOException {
        try (FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()) {
            final ReportPhotoFileDTO reportPhotoFileDTO = reportPhotoFileService.findOne(
                    token, id, fileName, outputStream);
            response.setContentType(reportPhotoFileDTO.getFileType());
            outputStream.writeTo(response.getOutputStream());
            response.getOutputStream().close();
            outputStream.reset();
        }
    }
}
