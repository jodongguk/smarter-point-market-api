package com.ksmarter.pointmarket.domain.account.gql.fetcher;

import com.ksmarter.pointmarket.constants.DgsTypeConst;
import com.ksmarter.pointmarket.domain.account.domain.Authority;
import com.ksmarter.pointmarket.domain.account.gql.context.AccountContextBuilder;
import com.ksmarter.pointmarket.domain.account.domain.Account;
import com.ksmarter.pointmarket.domain.account.repository.AccountRepository;
import com.ksmarter.pointmarket.domain.account.repository.AuthorityRepository;
import com.ksmarter.pointmarket.domain.franchisor.domain.Franchisor;
import com.ksmarter.pointmarket.domain.franchisor.repository.FranchisorRepository;
import com.ksmarter.pointmarket.domain.institute.domain.Institute;
import com.ksmarter.pointmarket.domain.institute.repository.InstituteRepository;
import com.ksmarter.pointmarket.generated.DgsConstants;
import com.ksmarter.pointmarket.generated.types.AccountFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Set;

@Slf4j
@DgsComponent
public class AccountFetcher {
    private final AccountContextBuilder accountContextBuilder;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final FranchisorRepository franchisorRepository;
    private final InstituteRepository instituteRepository;

    public AccountFetcher(AccountRepository accountRepository, AuthorityRepository authorityRepository, AccountContextBuilder accountContextBuilder, FranchisorRepository franchisorRepository, InstituteRepository instituteRepository) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.accountContextBuilder = accountContextBuilder;
        this.franchisorRepository = franchisorRepository;
        this.instituteRepository = instituteRepository;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Connection<Account> accounts(DgsDataFetchingEnvironment dfe,
                                        @InputArgument AccountFilter filter,
                                        @InputArgument Integer first,
                                        @InputArgument String after) {

        List<Account> accounts = accountRepository.findAll();
        accountContextBuilder.withAccounts(accounts).build();
        return new SimpleListConnection<>(accounts).get(dfe);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsTypeConst.QueryResolver)
    public Account account(DgsDataFetchingEnvironment dfe,
                           @InputArgument("id") Long id) {
        return accountRepository.findById(id).orElseThrow(DgsEntityNotFoundException::new);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DgsData(parentType = DgsConstants.ACCOUNT.TYPE_NAME, field = DgsConstants.ACCOUNT.Authorities)
    public Set<Authority> authorities(DgsDataFetchingEnvironment dfe) {
        Account account = dfe.getSource();
        return authorityRepository.findByAuthority_Id_AccountId(account.getId());
    }

    @PreAuthorize("hasAnyRole('ROLE_PARENT')")
    @DgsData(parentType = DgsConstants.ACCOUNT.TYPE_NAME, field = DgsConstants.ACCOUNT.Children)
    public Set<Account> children(DgsDataFetchingEnvironment dfe) {
        Account account = dfe.getSource();
        return accountRepository.findByChildrens_Id_ParentId(account.getId());
    }

    @PreAuthorize("hasAnyRole('ROLE_INSTITUTE')")
    @DgsData(parentType = DgsConstants.ACCOUNT.TYPE_NAME, field = DgsConstants.ACCOUNT.Institutes)
    public Set<Institute> institutes(DgsDataFetchingEnvironment dfe) {
        Account account = dfe.getSource();
        return instituteRepository.findByAccounts_Id_AccountId(account.getId());
    }

    @PreAuthorize("hasAnyRole('ROLE_FRANCHISOR')")
    @DgsData(parentType = DgsConstants.ACCOUNT.TYPE_NAME, field = DgsConstants.ACCOUNT.Franchisors)
    public Set<Franchisor> franchisors(DgsDataFetchingEnvironment dfe) {
        Account account = dfe.getSource();
        return franchisorRepository.findByAccounts_Id_FranchisorId(account.getId());
    }

}
