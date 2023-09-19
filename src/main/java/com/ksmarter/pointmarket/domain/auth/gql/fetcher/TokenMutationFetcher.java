package com.ksmarter.pointmarket.domain.auth.gql.fetcher;

import com.ksmarter.pointmarket.domain.auth.dto.ResponseLogin;
import com.ksmarter.pointmarket.domain.auth.service.LoginService;
import com.ksmarter.pointmarket.security.jwtaa.filter.CustomJwtFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.internal.DgsWebMvcRequestData;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.context.request.ServletWebRequest;

@DgsComponent
public class TokenMutationFetcher {

    private final LoginService loginService;

    public TokenMutationFetcher(LoginService loginService) {
        this.loginService = loginService;
    }

    @PermitAll
    @DgsData(parentType = "MutationResolver")
    public ResponseLogin.Token login(DgsDataFetchingEnvironment dfe,
                                     @InputArgument String userid,
                                     @InputArgument String password) {

        ResponseLogin.Token token = loginService.authenticate(userid, password);

        this.setAuthorizationToken(dfe, token);

        return token;
    }

    @PermitAll
    @DgsData(parentType = "MutationResolver")
    public ResponseLogin.Token refresh(DgsDataFetchingEnvironment dfe,
                                       @InputArgument String refreshToken) {

        ResponseLogin.Token token = loginService.refreshToken(refreshToken);

        this.setAuthorizationToken(dfe, token);

        return token;
    }


    private void setAuthorizationToken(DgsDataFetchingEnvironment dfe, ResponseLogin.Token token) {
        DgsWebMvcRequestData requestData = (DgsWebMvcRequestData) dfe.getDgsContext().getRequestData();
        ServletWebRequest webRequest = (ServletWebRequest) requestData.getWebRequest();
        webRequest.getResponse().addHeader(CustomJwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.accessToken());
    }
}
