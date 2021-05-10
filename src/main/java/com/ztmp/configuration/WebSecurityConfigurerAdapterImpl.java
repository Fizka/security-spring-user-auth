package com.ztmp.configuration;

import com.ztmp.exception.RestAccessDeniedHandler;
import com.ztmp.exception.RestAuthenticationEntryPoint;
import com.ztmp.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerAdapterImpl extends WebSecurityConfigurerAdapter {

    public final UserDetailsServiceImpl userDetailsServiceImpl;
    public final RestAccessDeniedHandler restAccessDeniedHandler;
    public final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    public WebSecurityConfigurerAdapterImpl(UserDetailsServiceImpl userDetailsServiceImpl, RestAccessDeniedHandler restAccessDeniedHandler, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @SuppressWarnings("deprecation")
    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/dashboard").hasRole("USER")
                .antMatchers("/dashboard/**", "/dashboard").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(restAccessDeniedHandler).authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .cors().disable();
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
