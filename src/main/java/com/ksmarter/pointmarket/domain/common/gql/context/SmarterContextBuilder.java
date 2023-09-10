package com.ksmarter.pointmarket.domain.common.gql.context;

import com.netflix.graphql.dgs.context.DgsCustomContextBuilderWithRequest;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
public class SmarterContextBuilder implements DgsCustomContextBuilderWithRequest<SmarterContext> {

    @Override
    public SmarterContext build(@Nullable Map<String, ?> map, @Nullable HttpHeaders httpHeaders, @Nullable WebRequest webRequest) {
        httpHeaders.entrySet().forEach(stringListEntry -> {
            log.info("key is {}", stringListEntry.getKey());
        });
        return new SmarterContext();
    }
}
