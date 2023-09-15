package com.ksmarter.pointmarket.domain.institute.gql.fetcher;


import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;

@DgsComponent
public class InstituteQueryFetcher {

    private final AccountRepository accountRepository;
    private final InstituteRepository instituteRepository;

    public InstituteQueryFetcher(AccountRepository accountRepository, InstituteRepository instituteRepository) {
        this.accountRepository = accountRepository;
        this.instituteRepository = instituteRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public Connection<Institute> institutes(DataFetchingEnvironment dfe) {
        List<Institute> institutes = instituteRepository.findAll();
        return new SimpleListConnection<>(institutes).get(dfe);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.QUERYRESOLVER.TYPE_NAME)
    public Institute institute(DataFetchingEnvironment dfe, @InputArgument("id") Long id) {
        return instituteRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.INSTITUTE.TYPE_NAME, field = DgsConstants.INSTITUTE.Accounts)
    public Set<Account> accounts(DataFetchingEnvironment dfe) {
        Institute institute = dfe.getSource();
        return accountRepository.findByInstitutes_Id_InstituteId(institute.getId());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.INSTITUTE.TYPE_NAME, field = DgsConstants.INSTITUTE.Children)
    public Set<Account> children(DataFetchingEnvironment dfe) {
        Institute institute = dfe.getSource();
        return accountRepository.findByAttendInstitutes_Id_InstituteId(institute.getId());
    }

}
