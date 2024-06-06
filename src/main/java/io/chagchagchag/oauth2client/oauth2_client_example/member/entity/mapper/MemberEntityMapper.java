package io.chagchagchag.oauth2client.oauth2_client_example.member.entity.mapper;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.MemberEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {

  // TODO
  public Map<String, Object> toUserAttributes(MemberEntity memberEntity){
    return new HashMap<>();
  }

  /* Notice */
  // MemberEntity 에 작성했던 코드를 이곳으로 분리
  // Entity 에서는 raw 데이터를 저장하고 엔티티 연산만 주력으로 책임지도록 하는게 맞다고 생각해서 Mapper 로 분리
  // (OAuth2 서비스 내의 변경사항이 데이터 저장소(Entity)에 영향을 주면 안되므로)
  public List<String> parseRoleList(MemberEntity memberEntity){
    String roles = memberEntity.getRoles();

    if(roles.length() > 0)
      return List.of(roles.split(","));
    else
      return List.of();
  }

  public User defaultUserDetails(MemberEntity memberEntity){
    return new User(
        memberEntity.getEmail(),
        memberEntity.getPassword(),
        true, true, true, true,
        new ArrayList<GrantedAuthority>()
    );
  }

}
