package com.github.pt.admin.user;

import com.github.pt.token.InUser;
import com.github.pt.token.InUserFacebook;
import com.github.pt.token.InUserFacebookRepository;
import com.github.pt.token.InUserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminUserService {
    
    private final InUserRepository inUserRepository;
    private final InUserFacebookRepository inUserFacebookRepository;
    
    AdminUserService(InUserRepository inUserRepository,
            InUserFacebookRepository inUserFacebookRepository) {
        this.inUserRepository = inUserRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
    }
    
    List<UserResponseDTO> findAll() {
        return inUserRepository.findAll().stream().map(inUser ->
                new UserResponseDTO(inUser.getId(), inUser.getInUserFacebooks().isEmpty() ?
                        "?" : inUser.getInUserFacebooks().get(0).getUser_name())
        ).collect(Collectors.toList());
    }

    UserResponseDTO findOne(Long id) {
        final InUser inUser = inUserRepository.findOne(id);
        return new UserResponseDTO(inUser.getId(), inUser.getInUserFacebooks().isEmpty() ?
                        "?" : inUser.getInUserFacebooks().get(0).getUser_name());
    }

    UserResponseDTO create(UserRequestDTO userRequestDTO) {
        final InUser inUser = new InUser();
        final InUserFacebook inUserFacebook = new InUserFacebook();
        inUserFacebook.setUser_name(userRequestDTO.getName());
        inUserFacebook.setUserId("?");
        inUserFacebook.setDeviceId("?");
        final InUser savedInUser = inUserRepository.save(inUser);
        inUserFacebook.setInUser(savedInUser);
        inUserFacebookRepository.save(inUserFacebook);
        return new UserResponseDTO(savedInUser.getId(), inUserFacebook.getUser_name());
    }

    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO) {
        final InUser inUser = inUserRepository.findOne(id);
        if (inUser.getInUserFacebooks().isEmpty()) {
            final InUserFacebook inUserFacebook = new InUserFacebook();
            inUserFacebook.setUser_name(userRequestDTO.getName());
            inUserFacebook.setUserId("?");
            inUserFacebook.setDeviceId("?");
            inUserFacebook.setInUser(inUser);
            inUser.getInUserFacebooks().add(inUserFacebook);           
        } else {
            inUser.getInUserFacebooks().get(0).setUser_name(userRequestDTO.getName());
        }
        inUserFacebookRepository.save(inUser.getInUserFacebooks().get(0));
        return new UserResponseDTO(inUser.getId(), userRequestDTO.getName());
    }

    UserResponseDTO delete(Long id) {
        final InUser inUser = inUserRepository.findOne(id);
        final String name = inUser.getInUserFacebooks().isEmpty() ?
                        "?" : inUser.getInUserFacebooks().get(0).getUser_name();
        inUserRepository.delete(id);
        return new UserResponseDTO(inUser.getId(), name);
    }
}
