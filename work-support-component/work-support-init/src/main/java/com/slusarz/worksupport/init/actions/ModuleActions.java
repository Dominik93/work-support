package com.slusarz.worksupport.init.actions;

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
public class ModuleActions {

    private boolean log;

    private boolean sqlExecutor;

    private boolean scriptExecutor;

    private boolean test;

}
