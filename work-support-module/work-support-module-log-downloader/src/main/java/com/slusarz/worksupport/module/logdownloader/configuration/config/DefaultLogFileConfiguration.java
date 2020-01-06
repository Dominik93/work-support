package com.slusarz.worksupport.module.logdownloader.configuration.config;

import com.slusarz.worksupport.module.logdownloader.domain.application.ConcatenationCharacter;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultLogFileConfiguration {

    @Value("${log-downloader.default.log-file.date-format:#{null}}")
    private String dateFormat;

    @Value("${log-downloader.default.log-file.log-format:#{null}}")
    private Format logFormat;

    @Value("${log-downloader.default.log-file.compress-format:#{null}}")
    private Format compressFormat;

    @Value("${log-downloader.default.log-file.concat-character:#{null}}")
    private ConcatenationCharacter concatCharacter;

    public LogFileConfig getDefault() {
        return LogFileConfig.builder()
                .compressFormat(compressFormat)
                .concatCharacter(concatCharacter)
                .dateFormat(dateFormat)
                .logFormat(logFormat)
                .build();
    }
}
