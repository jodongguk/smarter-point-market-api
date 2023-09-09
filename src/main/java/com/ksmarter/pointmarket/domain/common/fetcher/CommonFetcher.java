package com.ksmarter.pointmarket.domain.common.fetcher;

import com.ksmarter.pointmarket.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import org.springframework.security.access.annotation.Secured;

import java.util.Date;

@DgsComponent
public class CommonFetcher {

    @Secured("ROLE_ADMIN")
    @DgsData(parentType = "QueryResolver", field = DgsConstants.QUERYRESOLVER.Service)
    public String service() {
        return new Date().toString();
    }

}
