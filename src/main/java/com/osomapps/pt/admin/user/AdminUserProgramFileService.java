package com.osomapps.pt.admin.user;

import com.osomapps.pt.xlsx.XlsxProgramParser;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

@Service
@Slf4j        
class AdminUserProgramFileService {

    ProgramResponseDTO createXlsx(Long id, FastByteArrayOutputStream outputStream) {
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = XlsxProgramParser.class.getResourceAsStream("program01.xlsx")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return new ProgramResponseDTO().setFileName("program_for_user_" + id + ".xlsx")
                .setFileType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

}
