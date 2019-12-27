package com.slusarz.worksupport.commontypes.application;

import java.util.function.Function;

public interface DefaultPropertiesHelper<PropertiesType, ReturnType> {

    ReturnType getOrDefault(Function<PropertiesType, ReturnType> getter);
}
