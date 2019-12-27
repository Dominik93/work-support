package com.slusarz.worksupport.commontypes.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Slf4j
@AllArgsConstructor
public class MultipleDefaultPropertiesHelper<PropertiesType, ReturnType> implements DefaultPropertiesHelper<PropertiesType, ReturnType> {

    private PropertiesType valueProperties;

    private List<PropertiesType> defaultProperties;

    @Override
    public ReturnType getOrDefault(Function<PropertiesType, ReturnType> getter) {
        List<PropertiesType> properties = new ArrayList<>();
        properties.add(valueProperties);
        properties.addAll(defaultProperties);

        for (PropertiesType property : properties) {
            ReturnType returnValue = getter.apply(property);
            if (Objects.nonNull(returnValue)) {
                return returnValue;
            }
        }
        return null;
    }


}
