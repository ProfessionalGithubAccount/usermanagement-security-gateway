package com.iiht.config;

import com.iiht.filter.MyJWTFilter;

import com.iiht.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    MyJWTFilter myJWTFilter;
//    @Bean
//    public PasswordEncoder passwordEncoder2(){
//
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider myAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception {
       http
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .addFilterBefore(myJWTFilter, UsernamePasswordAuthenticationFilter.class)
               .csrf((csrf) -> csrf.disable())
               .authorizeHttpRequests((requests) -> requests
                       .requestMatchers("/registerstudent","/registerprofessor","/loginstudent","/loginprofessor").permitAll()
                       .requestMatchers("/students/**").hasAnyAuthority("STUDENT")
                       .requestMatchers("/professor/**").hasAnyAuthority("PROFESSOR")
                       .requestMatchers("/api1").hasAnyAuthority("ADMIN")
                       .requestMatchers("/api2").hasAnyAuthority("HR")
                       //.requestMatchers("/api1","/api2").authenticated()
                       .requestMatchers("/","/authenticate").permitAll())
               .formLogin(Customizer.withDefaults())
               .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
