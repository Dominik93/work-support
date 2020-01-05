package com.slusarz.worksupport.module.scriptexecutor.specification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class GetScriptsResponse {

    private List<Script> scripts;

}
