package com.slusarz.worksupport.module.scriptexecutor.specification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteScriptResponse {

    private String token;

    private boolean success;

    public static ExecuteScriptResponse success(String token) {
        return ExecuteScriptResponse.builder()
                .token(token)
                .success(true)
                .build();
    }
}
