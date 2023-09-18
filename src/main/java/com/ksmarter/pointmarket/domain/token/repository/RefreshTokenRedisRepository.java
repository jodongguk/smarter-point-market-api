package com.ksmarter.pointmarket.domain.token.repository;

import com.ksmarter.pointmarket.domain.token.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, Long> {
    RefreshToken findByRefreshToken(String refreshToken);
}
