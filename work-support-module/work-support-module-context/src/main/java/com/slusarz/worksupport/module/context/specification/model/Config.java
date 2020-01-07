package com.slusarz.worksupport.module.context.specification.model;

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
public class Config {

    private List<String> environments;

    private String defaultEnvironment;

    private ModuleConfig logDownloader;

    private ModuleConfig scriptExecutor;

    private ModuleConfig sqlExecutor;

}
