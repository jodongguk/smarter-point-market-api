package com.ksmarter.pointmarket.domain.franchisor.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import com.ksmarter.pointmarket.domain.franchisor.repository.FranchisorRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;

@DgsComponent
public class FranchisorFetcher {

    private final AccountRepository accountRepository;
    private final FranchisorRepository franchisorRepository;

    public FranchisorFetcher(AccountRepository accountRepository, FranchisorRepository franchisorRepository) {
        this.accountRepository = accountRepository;
        this.franchisorRepository = franchisorRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRANCHISOR')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Franchisor> franchisors(DgsDataFetchingEnvironment dfe) {
        List<Franchisor> franchisors = franchisorRepository.findAll();
        return new SimpleListConnection<>(franchisors).get(dfe);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRANCHISOR')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Franchisor franchisor(DataFetchingEnvironment dfe, @InputArgument("id") Long id) {
        return franchisorRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FRANCHISOR')")
    @DgsData(parentType = DgsConstants.FRANCHISOR.TYPE_NAME, field = DgsConstants.FRANCHISOR.Accounts)
    public Set<Account> accounts(DataFetchingEnvironment dfe) {
        Franchisor franchisor = dfe.getSource();
        return accountRepository.findByFranchisors_Id_FranchisorId(franchisor.getId());
    }

}
