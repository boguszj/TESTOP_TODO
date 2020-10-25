package com.tesop.testop.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        CorsConfiguration configuration = getCorsConfiguration();
        UrlBasedCorsConfigurationSource source = getUrlBasedCorsConfigurationSource(configuration);
        source.registerCorsConfiguration("/**", configuration);
        httpSecurity
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .and()
                .csrf().disable()
                .cors().configurationSource(source);
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(securityProperties.getCorsAllowedOrigins());
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Collections.singletonList("content-type"));
        return configuration;
    }

    private UrlBasedCorsConfigurationSource getUrlBasedCorsConfigurationSource(CorsConfiguration configuration) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
