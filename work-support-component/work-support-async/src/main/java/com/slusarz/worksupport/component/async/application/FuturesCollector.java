package com.slusarz.worksupport.component.async.application;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
public class FuturesCollector<T> {

    public List<T> collectFutures(List<Future<T>> futures) {
        return futures.stream().map(this::collectFuture).collect(Collectors.toList());
    }

    @SneakyThrows({InterruptedException.class, ExecutionException.class})
    private T collectFuture(Future<T> future) {
        return future.get();
    }

}
