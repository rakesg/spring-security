package com.eziq.monitoring.server.service;

import com.eziq.monitoring.server.model.Token;
import com.eziq.monitoring.server.model.TokenResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public TokenService(JwtEncoder encoder, JwtDecoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }


    public TokenResponse generateToken(Authentication authentication) {
        TokenResponse response = new TokenResponse();
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("eziq-monitoring")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        Token token = new Token();
        token.setToken(this.encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue());
        return getTokenStatus(token);
    }

    public TokenResponse getTokenStatus(Token token) {
        TokenResponse response = new TokenResponse();
        try{
            var decodedToken = this.decoder.decode(token.getToken().replace("Bearer ", ""));
            response.setToken(decodedToken.getTokenValue());
            response.setIss(decodedToken.getClaims().get("iss").toString());
            response.setUser(decodedToken.getClaims().get("sub").toString());
            response.setValid_till(decodedToken.getClaims().get("exp").toString());
            response.setValid_from(decodedToken.getClaims().get("iat").toString());
            response.setStatus("active");
        }catch (JwtValidationException jex){
            response.setToken(token.getToken());
            response.setValid_till(jex.getMessage());
            response.setStatus("expired");
        }
        return response;
    }
}
