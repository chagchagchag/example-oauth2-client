package io.chagchagchag.oauth2client.oauth2_client_example.member.security;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository.MemberR2dbcRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
//@Service
public class MemberOAuth2Service implements ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {
  private final MemberR2dbcRepository memberR2dbcRepository;


  @Override
  public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest
        .getClientRegistration()
        .getRegistrationId();

    String userNameAttributeName = userRequest
        .getClientRegistration()
        .getProviderDetails()
        .getUserInfoEndpoint()
        .getUserNameAttributeName();

    Map<String, Object> attributes = oAuth2User.getAttributes();

    OAuth2Attributes oAuth2Attributes = new OAuth2Attributes(
        registrationId, userNameAttributeName, attributes
    );

    // todo... 우와... 은근히 할거 드럽게 많네...
    // 참고 : https://docs.spring.io/spring-security/reference/reactive/oauth2/index.html

    return null;
  }

  // 임시
  class OAuth2Attributes{
    private String registrationId;
    private String userNameAttributeName;
    private Map<String, Object> attributes;

    public OAuth2Attributes(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
      this.registrationId = registrationId;
      this.userNameAttributeName = userNameAttributeName;
      this.attributes = attributes;
    }
  }


}
