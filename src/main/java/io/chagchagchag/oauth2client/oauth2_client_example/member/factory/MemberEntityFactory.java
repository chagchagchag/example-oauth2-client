package io.chagchagchag.oauth2client.oauth2_client_example.member.factory;

import io.chagchagchag.oauth2client.oauth2_client_example.member.MemberEntity;
import io.chagchagchag.oauth2client.oauth2_client_example.member.security.MemberRoles;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityFactory {
  public MemberEntity ofCreateRoleMember(
      String name, String email
  ){
    MemberRoles userRole = MemberRoles.ROLE_USER;
    return MemberEntity.createBuilder()
        .name(name)
        .email(email)
        .roles(userRole.getRoleName())
        .build();
  }
}
