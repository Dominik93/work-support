package com.slusarz.worksupport.module.scriptexecutor.controller.mapper;

import com.slusarz.worksupport.module.scriptexecutor.domain.script.Option;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OptionMapper {

    public List<Option> toOptions(List<com.slusarz.worksupport.module.scriptexecutor.specification.model.Option> options) {
        return options.stream().map(this::toOption).collect(Collectors.toList());
    }

    public Option toOption(com.slusarz.worksupport.module.scriptexecutor.specification.model.Option option) {
        return Option.of(option.getOption(), option.getValue());
    }

    public List<com.slusarz.worksupport.module.scriptexecutor.specification.model.Option> toOptionsApi(List<Option> options) {
        return options.stream().map(this::toOptionApi).collect(Collectors.toList());
    }

    public com.slusarz.worksupport.module.scriptexecutor.specification.model.Option toOptionApi(Option option) {
        com.slusarz.worksupport.module.scriptexecutor.specification.model.Option retOption = new com.slusarz.worksupport.module.scriptexecutor.specification.model.Option();
        retOption.setOption(option.getOption());
        retOption.setValue(option.getValue());
        return retOption;
    }
}
