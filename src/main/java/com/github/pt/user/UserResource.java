package com.github.pt.user;

import com.github.pt.token.InUser;
import com.github.pt.token.InUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/user")
public class UserResource {

    private final InUserRepository inUserRepository;
    private final UserService userService;
    
    @Autowired
    public UserResource(InUserRepository inUserRepository,
            UserService userService) {
        this.inUserRepository = inUserRepository;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public UserResponseDTO findOne(@RequestHeader(value = "X-Token") String token) {
        InUser inUser = userService.findOne(token);
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setGender(inUser.getD_sex());
        if (inUser.getAge() != null) {
            userResponse.setAge(inUser.getAge().longValue());
        }
        if (inUser.getHeight() != null) {
            userResponse.setHeight(inUser.getHeight().longValue());
        }
        if (inUser.getWeight() != null) {
            userResponse.setWeight(inUser.getWeight().longValue());
        }
        return userResponse;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestHeader(value = "X-Token") String token,
            @RequestBody UserRequestDTO userRequest) {
        userService.updateUser(token, userRequest);
    }
}
