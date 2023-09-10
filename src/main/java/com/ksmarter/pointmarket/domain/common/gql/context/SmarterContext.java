package com.ksmarter.pointmarket.domain.common.gql.context;

import lombok.Getter;

@Getter
public class SmarterContext {

    private final String customState = "Custom state!";

    public String getCustomState() {
        return customState;
    }

}
