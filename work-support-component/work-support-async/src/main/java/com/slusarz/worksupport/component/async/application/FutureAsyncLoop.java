package com.slusarz.worksupport.component.async.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.function.Function;

@Component
public class FutureAsyncLoop<Object, ReturnType> {

    @Autowired
    private FuturesCollector<ReturnType> futuresCollector;

    public List<ReturnType> invokeAsync(List<Object> objectsToProcess, Function<Object, Future<ReturnType>> asyncFunction) {
        List<Future<ReturnType>> returnedObjects = new ArrayList<>();
        for (Object processObjects : objectsToProcess) {
            returnedObjects.add(asyncFunction.apply(processObjects));
        }
        return futuresCollector.collectFutures(returnedObjects);
    }

}
