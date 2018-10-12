package com.osomapps.pt.admin.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.xlsx.XlsxProgramModifier;
import com.osomapps.pt.xlsx.XlsxProgramParser;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

@Slf4j
@Service
class AdminUserProgramFileService {

    private final DictionaryService dictionaryService;
    private final InUserRepository inUserRepository;

    AdminUserProgramFileService(DictionaryService dictionaryService,
            InUserRepository inUserRepository) {
        this.dictionaryService = dictionaryService;
        this.inUserRepository = inUserRepository;
    }

    ProgramResponseDTO createXlsx(Long id, FastByteArrayOutputStream outputStream) {
        final InUser inUser = inUserRepository.findOne(id);
        if (inUser == null) {
            throw new ResourceNotFoundException("User with id " + id + " not found.");
        }        
        try (InputStream inputStream = XlsxProgramParser.class.getResourceAsStream("program01.xlsx");
            FastByteArrayOutputStream localOutputStream = new FastByteArrayOutputStream()) {
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                localOutputStream.write(buffer, 0, length);
            }
            final XlsxProgramModifier xlsxProgramModifier = XlsxProgramModifier.of(
                    localOutputStream.getInputStream(), dictionaryService);
            xlsxProgramModifier.updateCellData(outputStream, inUser);
            return new ProgramResponseDTO().setFileName("program_for_user_" + id + ".xlsx")
                    .setFileType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            throw new UnsupportedOperationException(ex);
        }
    }

}
