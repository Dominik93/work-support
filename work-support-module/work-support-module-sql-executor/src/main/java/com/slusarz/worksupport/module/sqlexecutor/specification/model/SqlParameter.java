package com.slusarz.worksupport.module.sqlexecutor.specification.model;

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
public class SqlParameter {

    private String name;

    private String value;

}
