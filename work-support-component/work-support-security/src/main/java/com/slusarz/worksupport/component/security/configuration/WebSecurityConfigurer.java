package com.slusarz.worksupport.component.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.headers().frameOptions().sameOrigin();
        security.authorizeRequests().anyRequest().permitAll();
        security.httpBasic().disable(); // TODO FIXME
        security.csrf().disable(); // TODO FIXME
        security.cors().disable(); // TODO FIXME
    }

}