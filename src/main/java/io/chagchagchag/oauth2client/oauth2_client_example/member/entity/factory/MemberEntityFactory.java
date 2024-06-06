package io.chagchagchag.oauth2client.oauth2_client_example.member.entity.factory;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.MemberEntity;
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
