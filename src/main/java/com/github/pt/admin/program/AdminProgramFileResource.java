package com.github.pt.admin.program;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/program-file")
class AdminProgramFileResource {

    private final AdminProgramService programService;

    @Autowired
    AdminProgramFileResource(AdminProgramService programService) {
        this.programService = programService;
    }

    @GetMapping(value = "{id}/{fileName}")
    @ResponseBody
    Object findOne(@PathVariable Long id, @PathVariable String fileName, HttpServletResponse response)
            throws IOException {
        final ProgramResponseDTO programResponseDTO = programService.findOne(id);
        final ByteArrayOutputStream outputStream = programService.createXlsx(programResponseDTO.getDataUrl());
        response.setContentType(programResponseDTO.getFileType());
        response.setHeader("Content-disposition",
                "attachment; filename=" + programResponseDTO.getFileName());
        response.getOutputStream().write(outputStream.toByteArray());
        response.getOutputStream().close();
        return null;
    }
}
