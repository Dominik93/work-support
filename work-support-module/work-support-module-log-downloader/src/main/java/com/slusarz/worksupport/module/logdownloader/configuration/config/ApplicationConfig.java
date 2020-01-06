package com.slusarz.worksupport.module.logdownloader.configuration.config;

import com.slusarz.worksupport.commontypes.domain.Environment;
import com.slusarz.worksupport.module.logdownloader.domain.Tag;
import com.slusarz.worksupport.module.logdownloader.domain.application.ApplicationName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties
public class ApplicationConfig {

    private ApplicationName name;

    private Tag tag;

    private List<Environment> environments;

    private LogFileConfig logFile;

}