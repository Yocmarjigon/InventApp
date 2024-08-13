package com.application.inventApp.Security;

import com.application.inventApp.Entity.Enums.Rol;
import com.application.inventApp.Services.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(http -> {
          http.requestMatchers("/product/*").hasAnyRole("VENDEDOR", "GESTOR");
          http.requestMatchers("/category/*").hasAnyRole("VENDEDOR", "GESTOR");
          http.requestMatchers("/supplier/*").hasAnyRole("VENDEDOR", "GESTOR");
          http.requestMatchers("/order/*").hasAnyRole("VENDEDOR", "GESTOR");
          http.requestMatchers("/sale/*").hasAnyRole("VENDEDOR", "GESTOR");
          http.requestMatchers("/user/find-all").hasAnyRole("ADMIN");
          http.requestMatchers("/user/find-id").hasAnyRole("ADMIN");
          http.requestMatchers("/user/save").hasAnyRole("ADMIN");
          http.requestMatchers("/user/update").hasAnyRole("ADMIN");
          http.requestMatchers("/user/delete").hasAnyRole("ADMIN");
          http.anyRequest().denyAll();
        })
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public AuthenticationProvider authenticationProvider(UserService userService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails userDetails = User
        .withUsername("juan")
        .password("1234")
        .roles("ADMIN")
        .authorities("READ", "CREATE")
        .build();
    return new InMemoryUserDetailsManager(userDetails);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
