package com.github.pt.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface InUserFacebookRepository extends JpaRepository<InUserFacebook, Long> {

    List<InUserFacebook> findByUserId(String userId);
    
    List<InUserFacebook> findByTokenAndDeviceId(String token, String deviceId);

}
