package com.slusarz.worksupport.module.context.specification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InitResponse {

    private ModuleActions moduleActions;

    private Config config;

    private Actions actions;


}
