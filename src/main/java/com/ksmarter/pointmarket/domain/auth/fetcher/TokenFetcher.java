package com.ksmarter.pointmarket.domain.auth.fetcher;

import com.ksmarter.pointmarket.domain.auth.dto.ResponseLogin;
import com.ksmarter.pointmarket.domain.auth.service.LoginService;
import com.ksmarter.pointmarket.domain.common.context.SmarterContext;
import com.ksmarter.pointmarket.generated.types.TokenInput;
import com.ksmarter.pointmarket.security.jwt.filter.CustomJwtFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import graphql.schema.DataFetchingEnvironment;

@DgsComponent
public class TokenFetcher {

    private final LoginService loginService;

    public TokenFetcher(LoginService loginService) {
        this.loginService = loginService;
    }

    @DgsData(parentType = "MutationResolver")
    public ResponseLogin.Token login(DataFetchingEnvironment dfe,
                                     @InputArgument String userid,
                                     @InputArgument String password) {

        ResponseLogin.Token token = loginService.authenticate(userid, password);

        this.setAuthorizationToken(dfe, token);

        return token;
    }

    @DgsData(parentType = "MutationResolver")
    public ResponseLogin.Token refresh(DataFetchingEnvironment dfe,
                                       @InputArgument String refreshToken) {
        ResponseLogin.Token token = loginService.refreshToken(refreshToken);

        this.setAuthorizationToken(dfe, token);

        return token;
    }


    private void setAuthorizationToken(DataFetchingEnvironment dfe, ResponseLogin.Token token) {
        DgsContext.getRequestData(dfe).getHeaders().add(CustomJwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.accessToken());
    }
}
