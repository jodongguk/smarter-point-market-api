package com.ksmarter.pointmarket.domain.auth.service;

import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.adapter.AccountAdapter;
import com.ksmarter.pointmarket.domain.auth.dto.ResponseLogin;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.security.jwt.provider.RefreshTokenProvider;
import com.ksmarter.pointmarket.security.jwt.provider.TokenProvider;
import com.ksmarter.pointmarket.security.jwt.exception.InvalidRefreshTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class LoginService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenProvider refreshTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginService(TokenProvider tokenProvider, RefreshTokenProvider refreshTokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.refreshTokenProvider = refreshTokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseLogin.Token authenticate(String userid, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userid, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String accessToken = tokenProvider.createToken(authentication);

        Long tokenWeight = ((AccountAdapter) authentication.getPrincipal()).getAccount().getTokenWeight();
        String refreshToken = refreshTokenProvider.createToken(authentication, tokenWeight);

        return ResponseLogin.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional(readOnly = true)
    public ResponseLogin.Token refreshToken(String refreshToken) {

        if (!refreshTokenProvider.validateToken(refreshToken)) throw new InvalidRefreshTokenException();

        Authentication authentication = refreshTokenProvider.getAuthentication(refreshToken);

        Account account = accountRepository.findOneWithAuthoritiesByUserid(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException(authentication.getName() + "을 찾을 수 없습니다"));

        if (account.getTokenWeight() > refreshTokenProvider.getTokenWeight(refreshToken)) {
            throw new InvalidRefreshTokenException();
        }

        String accessToken = tokenProvider.createToken(authentication);

        return ResponseLogin.Token.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
