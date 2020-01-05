package com.slusarz.worksupport.module.scriptexecutor.domain.script;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class Option {

    @Getter
    @NonNull
    private String option;

    @Getter
    private String value;

    public static List<Option> ofList(final String options) {
        if (options.isEmpty()) {
            return Collections.emptyList();
        }
        return Optional.ofNullable(options)
                .map(o -> Arrays.stream(o.split(","))
                        .map(Option::of)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public String toOption() {
        return Optional.ofNullable(value)
                .map(value -> String.format("-%s %s", option, value))
                .orElse("");
    }

}
