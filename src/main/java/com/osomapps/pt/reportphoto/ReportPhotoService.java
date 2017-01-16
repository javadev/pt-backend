package com.osomapps.pt.reportphoto;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUserPhoto;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.osomapps.pt.goals.InUserPhotoRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import org.springframework.validation.MapBindingResult;

@Service
class ReportPhotoService {
    private final InUserPhotoRepository inUserPhotoRepository;
    private final UserService userService;
    private final DataurlValidator dataurlValidator;

    ReportPhotoService(InUserPhotoRepository inUserPhotoRepository,
            UserService userService,
            DataurlValidator dataurlValidator) {
        this.inUserPhotoRepository = inUserPhotoRepository;
        this.userService = userService;
        this.dataurlValidator = dataurlValidator;
    }

    List<PhotoResponseDTO> findAll(String token) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            return inUserLogin.getInUser().getInUserPhotos().stream().map(inUserPhoto
                    -> new PhotoResponseDTO().setDate(inUserPhoto.getCreated())
                            .setPhoto("/api/v1/report-photo-file/" + inUserPhoto.getId()
                                    + "/" + inUserPhoto.getFile_name())
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    PhotoResponseDTO create(String token, PhotoRequestDTO photoRequestDTO) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
            dataurlValidator.validate(photoRequestDTO.getDataurl(), errors);
            if (errors.hasErrors()) {
                throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
            }
            final String fileType = extractFileType(photoRequestDTO.getDataurl());
            final InUserPhoto inUserPhoto = new InUserPhoto()
                .setGoal_id(photoRequestDTO.getGoal_id())
                .setInUsers(Arrays.asList(inUserLogin.getInUser()))
                .setData_url(photoRequestDTO.getDataurl())
                .setFile_type(fileType)
                .setFile_name(extractFileName(fileType));
            final InUserPhoto inUserPhotoSaved = inUserPhotoRepository.save(inUserPhoto);
            return new PhotoResponseDTO().setDate(LocalDateTime.now())
                    .setPhoto("/api/v1/report-photo-file/" + inUserPhotoSaved.getId() + "/" + inUserPhoto.getFile_name());
        }
        return new PhotoResponseDTO();
    }

    private String extractFileType(String dataurl) {
        return dataurl.substring(5, dataurl.indexOf(";"));
    }

    private String extractFileName(String fileType) {
        switch (fileType) {
            case "image/png":
                return "photo.png";
            case "image/jpeg":
            default:
                return "photo.jpg";
        }
    }
}
