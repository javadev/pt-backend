package com.osomapps.pt.admin.program;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
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
        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        final ProgramResponseDTO programResponseDTO = programService.createXlsx(id, outputStream);
        response.setContentType(programResponseDTO.getFileType());
        response.setHeader("Content-disposition",
                "attachment; filename=" + programResponseDTO.getFileName());
        outputStream.writeTo(response.getOutputStream());
        response.getOutputStream().close();
        outputStream.reset();
        outputStream.close();
        return null;
    }
}
