package com.ksmarter.pointmarket.domain.institute.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import com.ksmarter.pointmarket.domain.franchisor.repository.FranchisorRepository;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@DgsComponent
public class InstituteFetcher {

    private final InstituteRepository instituteRepository;

    public InstituteFetcher(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Institute> institutes(DataFetchingEnvironment dfe) {
        List<Institute> institutes = instituteRepository.findAll();
        return new SimpleListConnection<>(institutes).get(dfe);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Institute institute(DataFetchingEnvironment dfe, @InputArgument("id") Long id) {
        return instituteRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

}
