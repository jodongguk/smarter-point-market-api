package com.ksmarter.pointmarket.domain.franchisor.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import com.ksmarter.pointmarket.domain.franchisor.repository.FranchisorRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

@DgsComponent
public class FranchisorFetcher {

    private final FranchisorRepository franchisorRepository;

    public FranchisorFetcher(FranchisorRepository franchisorRepository) {
        this.franchisorRepository = franchisorRepository;
    }

    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Franchisor> franchisors(DgsDataFetchingEnvironment dfe) {
        List<Franchisor> franchisors = franchisorRepository.findAll();
        return new SimpleListConnection<>(franchisors).get(dfe);
    }

    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Franchisor franchisor(DataFetchingEnvironment dfe, @InputArgument("id") Long id) {
        return franchisorRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

}
