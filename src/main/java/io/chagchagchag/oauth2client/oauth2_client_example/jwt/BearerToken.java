package io.chagchagchag.oauth2client.oauth2_client_example.jwt;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

@Getter
public class BearerToken extends AbstractAuthenticationToken {
  private final String jwt;

  public BearerToken(String jwt){
    super(AuthorityUtils.NO_AUTHORITIES);
    this.jwt = jwt;
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
