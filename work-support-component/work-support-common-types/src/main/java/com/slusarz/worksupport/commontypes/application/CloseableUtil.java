package com.slusarz.worksupport.commontypes.application;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CloseableUtil {

    public static void close(Closeable... closeables) {
        Arrays.stream(closeables).forEach(CloseableUtil::close);
    }

    public static void close(Closeable closeable) {
        if (Objects.nonNull(closeable)) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

}
