package com.osomapps.pt.reportphoto;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.goals.InUserPhotoRepository;
import com.osomapps.pt.token.InUserPhoto;
import com.osomapps.pt.user.UserService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
class ReportPhotoFileService {
    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;
    private final InUserPhotoRepository inUserPhotoRepository;
    private final UserService userService;

    ReportPhotoFileService(InUserPhotoRepository inUserPhotoRepository,
            UserService userService) {
        this.inUserPhotoRepository = inUserPhotoRepository;
        this.userService = userService;
    }

    ReportPhotoFileDTO findOne(String token, Long id, String fileName, OutputStream outputStream)
            throws IOException {
        if (!token.isEmpty()) {
            userService.checkUserToken(token);
            final InUserPhoto inUserPhoto = inUserPhotoRepository.findById(id).orElse(null);
            if (inUserPhoto == null) {
                throw new ResourceNotFoundException("File with id " + id + " not found");
            }
            dataUrlToOutputStream(inUserPhoto.getData_url(), outputStream);
            return new ReportPhotoFileDTO().setId(inUserPhoto.getId())
                    .setFileName(inUserPhoto.getFile_name()).setFileType(inUserPhoto.getFile_type());
        }
        return new ReportPhotoFileDTO();
    }

    void dataUrlToOutputStream(String dataUrl, OutputStream outputStream) throws IOException {
        final String encodedString = dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH);
        outputStream.write(Base64.getDecoder().decode(encodedString));
    }
}
