package com.ksmarter.pointmarket.security;

import com.ksmarter.pointmarket.security.jwtaa.JwtProperties;
import com.ksmarter.pointmarket.security.jwtaa.provider.RefreshTokenProvider;
import com.ksmarter.pointmarket.security.jwtaa.provider.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Bean(name = "tokenProvider")
    public TokenProvider tokenProvider(JwtProperties jwtProperties) {
        log.debug("created bean tokenProvider");
        return new TokenProvider(jwtProperties.getSecret(), jwtProperties.getAccessTokenValidityInSeconds());
    }

    @Bean(name = "refreshTokenProvider")
    public RefreshTokenProvider refreshTokenProvider(JwtProperties jwtProperties) {
        return new RefreshTokenProvider(jwtProperties.getRefreshTokenSecret(), jwtProperties.getRefreshTokenValidityInSeconds());
    }
}
