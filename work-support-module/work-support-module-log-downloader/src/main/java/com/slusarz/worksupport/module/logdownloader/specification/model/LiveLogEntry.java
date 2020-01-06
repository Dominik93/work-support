package com.slusarz.worksupport.module.logdownloader.specification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiveLogEntry {

    private String application;

    private String content;

}
