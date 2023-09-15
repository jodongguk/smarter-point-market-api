package com.ksmarter.pointmarket.domain.common.gql.fetcher;

import com.ksmarter.pointmarket.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import jakarta.annotation.security.PermitAll;

import java.util.Date;

@DgsComponent
public class CommonQueryFetcher {

    @PermitAll()
    @DgsData(parentType = "QueryResolver", field = DgsConstants.QUERYRESOLVER.Service)
    public String service() {
        return new Date().toString();
    }

}
