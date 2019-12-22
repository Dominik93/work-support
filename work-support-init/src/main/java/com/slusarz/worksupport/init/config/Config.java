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

    private ModuleConfig log;

    private ModuleConfig script;

    private ModuleConfig sql;

    private ModuleConfig test;
}
