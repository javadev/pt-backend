package com.github.pt.admin.program;

import java.io.ByteArrayInputStream;
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
        final ByteArrayInputStream inputStream = programService.dataUrlToInputStream(programResponseDTO.getDataUrl());
        response.setContentType(programResponseDTO.getFileType());
        response.setHeader("Content-disposition",
                "attachment; filename=" + programResponseDTO.getFileName());
        final byte[] array = new byte[inputStream.available()];
        inputStream.read(array);
        response.getOutputStream().write(array);
        response.getOutputStream().close();
        return null;
    }
}
