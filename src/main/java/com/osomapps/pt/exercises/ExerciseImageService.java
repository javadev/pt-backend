package com.osomapps.pt.exercises;

import com.osomapps.pt.ResourceNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
class ExerciseImageService {
    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;
    private final ExerciseFileRepository exerciseFileRepository;

    ExerciseImageService(ExerciseFileRepository exerciseFileRepository) {
        this.exerciseFileRepository = exerciseFileRepository;
    }

    ExerciseImageDTO findOne(Long id, String fileName, OutputStream outputStream)
            throws IOException {
        final ExerciseFile exerciseFile = exerciseFileRepository.findOne(id);
        if (exerciseFile == null) {
            throw new ResourceNotFoundException("File with id " + id + " not found");
        }
        dataUrlToOutputStream(exerciseFile.getData_url(), outputStream);
        return new ExerciseImageDTO().setId(exerciseFile.getId())
                .setFileName(exerciseFile.getFile_name()).setFileType(exerciseFile.getFile_type());
    }

    void dataUrlToOutputStream(String dataUrl, OutputStream outputStream) throws IOException {
        final String encodedString = dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH);
        outputStream.write(Base64.getDecoder().decode(encodedString));
    }
}
