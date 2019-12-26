package com.slusarz.worksupport.commontypes.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Function;

@Slf4j
@AllArgsConstructor
public class DefaultPropertiesHelper<PropertiesType, ReturnType> {

    private PropertiesType defaultProperties;

    private PropertiesType valueProperties;

    public ReturnType getOrDefault(Function<PropertiesType, ReturnType> getter) {
        log.debug("Get: " + getter);
        ReturnType value = getter.apply(valueProperties);
        if (Objects.isNull(value)) {
            ReturnType apply = getter.apply(defaultProperties);
            log.debug("Get Default Value: " + apply);
            return apply;
        }
        log.debug("Get value: " + value);
        return value;

    }
}
