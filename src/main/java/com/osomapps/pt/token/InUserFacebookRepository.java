package com.osomapps.pt.token;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InUserFacebookRepository extends JpaRepository<InUserFacebook, Long> {

    List<InUserFacebook> findByUserId(String userId);

    List<InUserFacebook> findByDeviceId(String deviceId);
}
