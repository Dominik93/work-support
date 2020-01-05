package com.slusarz.worksupport.module.scriptexecutor.specification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteScriptRequest {

    private String scriptName;

    private List<Option> options;

}
