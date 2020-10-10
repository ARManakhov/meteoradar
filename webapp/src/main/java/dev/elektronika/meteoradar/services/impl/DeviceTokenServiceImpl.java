package dev.elektronika.meteoradar.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.elektronika.meteoradar.model.Device;
import dev.elektronika.meteoradar.repository.DeviceRepository;
import dev.elektronika.meteoradar.services.DeviceTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeviceTokenServiceImpl implements DeviceTokenService {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public void generateToken(Device device){
        Date now = new Date();
        String token = JWT.create()
                .withClaim("id",device.getId())
                .withIssuedAt(now).sign(algorithm);
        device.setToken(token);
    }

    public String getDeviceId(String token) { //todo add exparation check and validation
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims().get("id").asString();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

}
