package com.github.pt.tokenemail;

import org.springframework.stereotype.Service;

@Service
class TokenEmailService {

    TokenEmailResponseDTO createOrReadNewToken(TokenEmailRequestDTO tokenEmailRequest, String remoteAddr) {
        return new TokenEmailResponseDTO();
    }

    void deleteToken(String token, String remoteAddr) {
    }
    
}
