package io.chagchagchag.oauth2client.oauth2_client_example.member.security;

import lombok.Getter;

@Getter
public enum MemberRoles {
  ROLE_USER("ROLE_USER");
  private final String roleName;
  MemberRoles(String roleName){
    this.roleName = roleName;
  }
}
