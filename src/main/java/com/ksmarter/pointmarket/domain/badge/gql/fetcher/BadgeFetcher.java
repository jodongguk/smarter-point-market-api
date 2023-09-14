package com.ksmarter.pointmarket.domain.badge.gql.fetcher;


import com.ksmarter.pointmarket.domain.badge.domain.Badge;
import com.ksmarter.pointmarket.domain.badge.repository.BadgeRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.BadgeFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@DgsComponent
public class BadgeFetcher {

    private final BadgeRepository badgeRepository;

    public BadgeFetcher(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public Connection<Badge> badges(DataFetchingEnvironment dfe,
                                    @InputArgument BadgeFilter filter,
                                    @InputArgument Integer first,
                                    @InputArgument String after) {

        List<Badge> badges = badgeRepository.findAll();
        return new SimpleListConnection<>(badges).get(dfe);
    }

}
