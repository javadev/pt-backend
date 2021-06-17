package com.osomapps.pt.exercises;

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
@RequestMapping("api/v1/exercise-image")
class ExercisesImageResource {

    private final ExerciseImageService exerciseImageService;

    @Autowired
    ExercisesImageResource(ExerciseImageService exerciseImageService) {
        this.exerciseImageService = exerciseImageService;
    }

    @GetMapping("{id}/{fileName}")
    @ResponseBody
    Object findOne(
            @PathVariable Long id, @PathVariable String fileName, HttpServletResponse response)
            throws IOException {
        try (FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()) {
            final ExerciseImageDTO exerciseImageDTO =
                    exerciseImageService.findOne(id, fileName, outputStream);
            response.setContentType(exerciseImageDTO.getFileType());
            outputStream.writeTo(response.getOutputStream());
            response.getOutputStream().close();
            outputStream.reset();
        }
        return null;
    }
}
