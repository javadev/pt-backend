package com.osomapps.pt.reportphoto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/report-photo")
class ReportPhotoResource {

    private final ReportPhotoService reportPhotoService;

    @Autowired
    ReportPhotoResource(ReportPhotoService reportPhotoService) {
        this.reportPhotoService = reportPhotoService;
    }

    @GetMapping
    List<PhotoResponseDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return reportPhotoService.findAll(token);
    }

    @PostMapping
    PhotoResponseDTO create(
            @RequestHeader(value = "X-Token") String token,
            @RequestBody PhotoRequestDTO weightRequestDTO) {
        return reportPhotoService.create(token, weightRequestDTO);
    }
}
