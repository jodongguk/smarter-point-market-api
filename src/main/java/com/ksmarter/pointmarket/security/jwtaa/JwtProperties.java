package com.ksmarter.pointmarket.security.jwtaa;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String header;
    private String secret;
    private String refreshTokenSecret;
    private Long accessTokenValidityInSeconds;
    private Long refreshTokenValidityInSeconds;
}
