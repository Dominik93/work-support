package com.slusarz.worksupport.init.context;

import com.slusarz.worksupport.init.actions.Actions;
import com.slusarz.worksupport.init.actions.ModuleActions;
import com.slusarz.worksupport.init.config.Config;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ModuleInit {

    private final ModuleActions moduleActions;

    private final Config config;

    private final Actions actions;

    public static ModuleInit empty() {
        return new ModuleInit(new ModuleActions(), new Config(), new Actions());
    }

}
