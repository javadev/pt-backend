package com.github.pt.programs;

import com.github.pt.token.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/programs")
public class ProgramsResource {

    private final InProgramRepository inProgramRepository;
    
    @Autowired
    public ProgramsResource(InProgramRepository inProgramRepository) {
        this.inProgramRepository = inProgramRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<InProgram> list() {
        return inProgramRepository.findAll();
    }
}
