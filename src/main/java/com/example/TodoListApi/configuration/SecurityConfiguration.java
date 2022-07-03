package com.example.TodoListApi.configuration;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




@EnableWebSecurity
@Configuration
@Log4j2
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationManager authenticationManager(ObjectPostProcessor objectPostProcessor) throws Exception {
        return new AuthenticationManagerBuilder(objectPostProcessor)
                .userDetailsService(new JdbcUserDetailsManager())
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    @DependsOn("authenticationManager")
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
        usernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login");
        usernamePasswordAuthenticationFilter.setPostOnly(true);
        usernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(((request, response, authException) -> {
            response.setStatus(200);
        }));
        return usernamePasswordAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http.csrf().disable()
                .cors().and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    log.info("Access Denied : {}", accessDeniedException.getStackTrace());
                    response.setStatus(403);
                    response.getWriter().write("Access Denied");
                })
                .authenticationEntryPoint(((request, response, authException) -> {
                    log.info("Not Authorized : {}", authException.getMessage());
                    response.setStatus(401);
                    response.getWriter().write("Bad Authentication Details");
                }))
                .and()
                .addFilterBefore(usernamePasswordAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .build();
    }



}

