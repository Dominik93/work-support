package com.slusarz.worksupport.module.logdownloader.domain.livelog;

import lombok.Value;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Value(staticConstructor = "of")
public class Token {

    private String token;

    public static Token random() {
        return new Token(LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss")));
    }

}
