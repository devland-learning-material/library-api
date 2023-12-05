package excercise.library.library.authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import excercise.library.library.applicationuser.ApplicationUserService;
import excercise.library.library.authentication.jwt.JwtAuthenticationEntryPoint;
import excercise.library.library.authentication.jwt.JwtAuthenticationTokenFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfigurer {
  private final ApplicationUserService userDetailsService;

  private final JwtAuthenticationEntryPoint unauthorizedHandler;

  @Bean
  JwtAuthenticationTokenFilter authenticationJwtTokenFilter() {
    return new JwtAuthenticationTokenFilter();
  }

  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  AuthenticationManager authenticationManagerBean(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity
        .getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(this.userDetailsService)
        .passwordEncoder(this.bCryptPasswordEncoder());

    return authenticationManagerBuilder.build();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(CorsConfigurer::disable)
        .csrf(CsrfConfigurer::disable)
        .exceptionHandling(exception -> exception.authenticationEntryPoint(this.unauthorizedHandler))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(request -> request
            .requestMatchers("/tokens", "/books/**", "/authors/**", "/swagger-ui/**", "/v3/api-docs/**")
            .permitAll().anyRequest().authenticated());

    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
