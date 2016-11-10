package com.github.pt.reportweight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @RequestMapping(method = RequestMethod.GET)
    List<WeightResponseDTO> list(@RequestHeader(value = "X-Token") String token) {
        return reportWeightService.findAll(token);
    }

    @RequestMapping(method = RequestMethod.POST)
    WeightResponseDTO create(@RequestHeader(value = "X-Token") String token,
            @RequestBody WeightRequestDTO weightRequestDTO) {
        return reportWeightService.create(token, weightRequestDTO);
    }
}
