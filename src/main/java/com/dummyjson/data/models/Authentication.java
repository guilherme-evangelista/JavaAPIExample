package com.dummyjson.data.models;

import com.dummyjson.core.utils.StringUtils;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Authentication {

    private String token;

    public void validate() {
        StringUtils.requireNonEmpty(this.token, "Token is null or empty.");
    }

}
