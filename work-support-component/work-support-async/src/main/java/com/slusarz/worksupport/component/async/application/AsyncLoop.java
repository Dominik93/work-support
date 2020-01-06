package com.slusarz.worksupport.component.async.application;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
public class AsyncLoop<T> {

    public void invokeAsync(List<T> processedObjects, Consumer<T> asyncFunction) {
        processedObjects.forEach(asyncFunction);
    }

}
