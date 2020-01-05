package com.slusarz.worksupport.module.scriptexecutor.domain.script;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ToString
@AllArgsConstructor(staticName = "of")
public class Argument {

    @Getter
    @NonNull
    private String argument;

    public static List<Argument> ofList(final String arguments) {
        return Optional.ofNullable(arguments)
                .map(argument -> Arrays.stream(argument.split(","))
                        .map(Argument::of)
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    public String toArgument() {
        return String.format("%s", Optional.ofNullable(argument).orElse(""));
    }

}
