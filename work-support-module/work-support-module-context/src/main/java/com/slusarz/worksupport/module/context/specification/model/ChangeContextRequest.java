package com.slusarz.worksupport.module.context.specification.model;

import lombok.Data;

@Data
public class ChangeContextRequest {

    private String environment;

    private String database;

}
