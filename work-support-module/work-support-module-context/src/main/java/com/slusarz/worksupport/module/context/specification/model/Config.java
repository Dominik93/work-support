package com.slusarz.worksupport.module.context.specification.model;

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
public class Config {

    private ModuleConfig log;

    private ModuleConfig script;

    private ModuleConfig sql;

    private ModuleConfig test;
}
