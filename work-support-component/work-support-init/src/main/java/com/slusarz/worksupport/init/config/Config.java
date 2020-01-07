package com.slusarz.worksupport.init.config;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Config {

    private ModuleConfig logDownloader = ModuleConfig.ofDefault();

    private ModuleConfig scriptExecutor = ModuleConfig.ofDefault();

    private ModuleConfig sqlExecutor = ModuleConfig.ofDefault();

    private ModuleConfig test = ModuleConfig.ofDefault();
}
