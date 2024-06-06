package io.chagchagchag.oauth2client.oauth2_client_example.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Profile({"!h2"})
@RequiredArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain filterChain(
      ServerHttpSecurity httpSecurity
  ){

    return httpSecurity
        .csrf(csrfSpec -> csrfSpec.disable())
        .formLogin(formLoginSpec -> formLoginSpec.disable())
        .httpBasic(httpBasicSpec -> httpBasicSpec.disable())
        .authorizeExchange(authorizeExchangeSpec ->
          authorizeExchangeSpec
              .pathMatchers("/", "/welcome", "/img/**", "/api/users/signup", "/healthcheck/**")
              .permitAll()
              .pathMatchers("/swagger-ui.html", "/webjars/**")
              .permitAll()
              .pathMatchers("/healthcheck/ready")
              .permitAll()
              .pathMatchers("/api/users/login", "/api/users/signup")
              .permitAll()
              .pathMatchers("/logout", "/api/users/profile/**")
              .hasAnyAuthority("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN")
        )
        .oauth2Login(Customizer.withDefaults())
        .oauth2Client(Customizer.withDefaults())
        .build();

//    return http.csrf().disable().headers().frameOptions().disable() // h2-console을 사용하기 위해 옵션 disable
//        .and()
//        .authorizeRequests().antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
//        .antMatchers("/api/v1/**").hasRole(Role.USER.name())    // 권한 관리 대상, USER 권한을 갖는 사람만 가능하도록
//        .anyRequest().authenticated()   // 나머지 URL은 로그인한 사용자들에게 허용
//        .and()
//        .logout()
//        .logoutSuccessUrl("/")
//        .and()
//        .oauth2Login()
//        .userInfoEndpoint()
//        .userService(customOAuth2UserService).and().and().build();  // 로그인 성공 후 후속조치
//    출처: https://chamggae.tistory.com/217 [suhaha 개발 일지:티스토리]
  }
}
