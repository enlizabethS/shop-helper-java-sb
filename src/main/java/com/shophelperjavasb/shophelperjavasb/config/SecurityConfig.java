package com.shophelperjavasb.shophelperjavasb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shophelperjavasb.shophelperjavasb.config.details.StandardResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true) // глобальная защита методов
public class SecurityConfig {
    private UserDetailsService userDetailsServiceImpl;
    private PasswordEncoder passwordEncoder;
    ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.authorizeRequests()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/v3/api-docs/**").permitAll()
            .antMatchers("/api/auth/signup").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginProcessingUrl("/api/auth/login")
            .successHandler((request, response, authentication) -> {
                fillResponse(response, 200, "Successful authentication");
            })
            .failureHandler((request, response, exception) ->
                fillResponse(response, 401, "Wrong login or password")
            )
            .and()
            .exceptionHandling()
            .defaultAuthenticationEntryPointFor((request, response, authException) ->
                    fillResponse(response, 403, "User not authentication"),
                new AntPathRequestMatcher("/api/**")
            )
            .and()
            .logout()
            .logoutUrl("/api/auth/logout")
            .logoutSuccessHandler((request, response, authentication) ->
                fillResponse(response, 200, "Successful logout")
            )
//            .deleteCookies("JSESSIONID")
        ;

        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    private void fillResponse(HttpServletResponse response, int statusCode, String message) {
        response.setStatus(statusCode);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        StandardResponseDto standardResponseDto = StandardResponseDto.builder()
            .message(message)
            .status(statusCode)
            .build();

        try {
            response.getWriter().write(objectMapper.writeValueAsString(standardResponseDto));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}

//            .formLogin(form -> form
//                .loginProcessingUrl("/api/auth/login")
//                .permitAll()
//                .defaultSuccessUrl("/swagger-ui/index.html")
//                .failureUrl("/login?error=true")
//            )
//    .formLogin()
//    .loginProcessingUrl("/api/auth/login")
//    .defaultSuccessUrl("/swagger-ui/index.html")
//    .failureUrl("/login?error=true")
//    .and()
//    .exceptionHandling()
//    .defaultAuthenticationEntryPointFor(new Http403ForbiddenEntryPoint(), new AntPathRequestMatcher("/api/**"))
//    .and()
//    .logout()
//    .logoutUrl("/api/auth/logout")
//            .deleteCookies("JSESSIONID")