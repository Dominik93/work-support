package com.slusarz.worksupport.module.sqlexecutor.specification.model;

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
public class SqlMetadata {

    private String name;

    private String description;

    private List<SqlParameter> parameters;

}
