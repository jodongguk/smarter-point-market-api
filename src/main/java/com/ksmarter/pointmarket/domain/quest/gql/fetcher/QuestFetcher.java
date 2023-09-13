package com.ksmarter.pointmarket.domain.quest.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.badge.domain.Badge;
import com.ksmarter.pointmarket.domain.badge.repository.BadgeRepository;
import com.ksmarter.pointmarket.domain.quest.domain.Quest;
import com.ksmarter.pointmarket.domain.quest.repository.QuestRepository;
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
public class QuestFetcher {

    private final QuestRepository questRepository;

    public QuestFetcher(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Quest> quests(DataFetchingEnvironment dfe,
                                    @InputArgument BadgeFilter filter,
                                    @InputArgument Integer first,
                                    @InputArgument String after) {

        List<Quest> quests = questRepository.findAll();
        return new SimpleListConnection<>(quests).get(dfe);
    }

}
