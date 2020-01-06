package com.slusarz.worksupport.module.logdownloader.configuration.config;

import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationFileName;
import com.slusarz.worksupport.module.logdownloader.domain.application.ConcatenationCharacter;
import com.slusarz.worksupport.module.logdownloader.domain.application.Format;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties
public class LogFileConfig {

    private ApplicationFileName fileName;

    private String dateFormat;

    private Format logFormat;

    private Format compressFormat;

    private ConcatenationCharacter concatCharacter;

}
