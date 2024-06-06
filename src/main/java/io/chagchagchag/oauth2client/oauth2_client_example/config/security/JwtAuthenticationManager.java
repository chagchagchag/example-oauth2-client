package io.chagchagchag.oauth2client.oauth2_client_example.config.security;

import io.chagchagchag.oauth2client.oauth2_client_example.jwt.BearerToken;
import io.chagchagchag.oauth2client.oauth2_client_example.jwt.Jwt;
import io.chagchagchag.oauth2client.oauth2_client_example.jwt.JwtSupport;
import io.chagchagchag.oauth2client.oauth2_client_example.member.security.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
  private final JwtSupport jwtSupport;
  private final CustomUserDetailsService userDetailsService;

  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    return Mono.justOrEmpty(authentication)
        .filter(auth -> auth instanceof BearerToken)
        .cast(BearerToken.class)
        .map(bearerToken -> degenerateToken(bearerToken))
        .flatMap(jwtDto -> validateJwt(jwtDto))
        .flatMap(jwtDto -> findUserByEmail(jwtDto.email()))
        .onErrorMap(throwable -> new IllegalArgumentException("INVALID JWT"));
  }

  public Jwt degenerateToken(BearerToken token){
    return jwtSupport.degenerateToken(SecurityProperties.key, token.getJwt());
  }

  public Mono<Jwt> validateJwt(Jwt jwt){
    if(jwtSupport.checkIfNotExpired(jwt.expiration())){
      return Mono.just(jwt);
    }
    return Mono.error(new IllegalArgumentException("Token Invalid"));
  }

  private Mono<Authentication> findUserByEmail(String email){
    return userDetailsService
        .findByUsername(email)
        .map(userDetails -> {
          var authentication = new UsernamePasswordAuthenticationToken(
              userDetails.getUsername(),
              userDetails.getPassword(),
              userDetails.getAuthorities()
          );

          SecurityContextHolder.getContext().setAuthentication(authentication);
          return authentication;
        });
  }

  private Mono<Authentication> validate(BearerToken token){
    Jwt jwt = jwtSupport.degenerateToken(SecurityProperties.key, token.getJwt());

    if(jwtSupport.checkIfNotExpired(jwt.expiration())){
      return userDetailsService
          .findByUsername(jwt.email())
          .map(userDetails -> new UsernamePasswordAuthenticationToken(
              userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
          ));
    }

    throw new IllegalArgumentException("Token Invalid");
  }
}
