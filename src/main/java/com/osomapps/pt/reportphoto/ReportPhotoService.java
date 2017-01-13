package com.osomapps.pt.reportphoto;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.goals.InUserGoalPhotoRepository;
import com.osomapps.pt.token.InUserGoalPhoto;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class ReportPhotoService {
    private final InUserGoalPhotoRepository inUserGoalPhotoRepository;
    private final UserService userService;

    ReportPhotoService(InUserGoalPhotoRepository inUserGoalPhotoRepository,
            UserService userService) {
        this.inUserGoalPhotoRepository = inUserGoalPhotoRepository;
        this.userService = userService;
    }

    List<PhotoResponseDTO> findAll(String token) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            return inUserLogin.getInUser().getInUserGoals().stream().map(inUserGoal
                    -> inUserGoal.getInUserGoalPhotos().stream().map(inUserGoalPhoto
                            -> new PhotoResponseDTO().setDate(inUserGoalPhoto.getCreated())
                            .setPhoto("/api/v1/report-photo-file/" + inUserGoalPhoto.getId() + "/photo.jpg")
                    ).collect(Collectors.toSet())).flatMap(x -> x.stream())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    PhotoResponseDTO create(String token, PhotoRequestDTO photoRequestDTO) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            InUserGoalPhoto inUserGoalPhoto = new InUserGoalPhoto();
            inUserLogin.getInUser().getInUserGoals().stream()
                    .filter(inUserGoal -> inUserGoal.getGoalId().equals(photoRequestDTO.getGoal_id()))
                    .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException(
                            "Goal with id (" + photoRequestDTO.getGoal_id() + ") not found"))
                    .getInUserGoalPhotos().add(inUserGoalPhoto);
            InUserGoalPhoto inUserGoalPhotoSaved = inUserGoalPhotoRepository.save(inUserGoalPhoto);
            return new PhotoResponseDTO().setDate(inUserGoalPhotoSaved.getCreated())
                    .setPhoto("/api/v1/report-photo-file/" + inUserGoalPhotoSaved.getId() + "/photo.jpg");
        }
        return new PhotoResponseDTO();
    }
}
