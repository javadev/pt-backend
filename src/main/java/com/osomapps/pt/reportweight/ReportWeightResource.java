package com.osomapps.pt.reportweight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/v1/report-weight")
class ReportWeightResource {

    private final ReportWeightService reportWeightService;
    
    @Autowired
    ReportWeightResource(ReportWeightService reportWeightService) {
        this.reportWeightService = reportWeightService;
    }

    @GetMapping
    List<WeightResponseDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return reportWeightService.findAll(token);
    }

    @PostMapping
    WeightResponseDTO create(@RequestHeader(value = "X-Token") String token,
            @RequestBody WeightRequestDTO weightRequestDTO) {
        return reportWeightService.create(token, weightRequestDTO);
    }
}
