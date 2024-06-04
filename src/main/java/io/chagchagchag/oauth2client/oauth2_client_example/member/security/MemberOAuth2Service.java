package io.chagchagchag.oauth2client.oauth2_client_example.member.security;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository.MemberR2dbcRepository;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
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

//    memberR2dbcRepository.findMemberEntityByEmail(oAuth2Attributes.getEmail())
//        .switchIfEmpty(Mono.error(new IllegalArgumentException("존재하지 않는 회원입니다.")))
//        .map(memberEntity -> {
//          Set<GrantedAuthority> authorities = new LinkedHashSet<>();
//          authorities.add(new OAuth2UserAuthority())
//        })
    // todo... 우와... 은근히 할거 드럽게 많네...

    return null;
  }

  @Getter
  class OAuth2Attributes{
    private String registrationId;
    private String userNameAttributeName;
    private String email;
    private String name;
    private String profileImage;

    public OAuth2Attributes(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
      this.registrationId = registrationId;
      this.userNameAttributeName = userNameAttributeName;
      this.email = Optional
          .ofNullable((String) attributes.get("email"))
          .orElseThrow(() -> new IllegalArgumentException("email 이 비어있어요!!"));

      this.name = Optional
          .ofNullable((String) attributes.get("name"))
          .orElseThrow(() -> new IllegalArgumentException("name 이 비어있어요!!"));

      this.profileImage = Optional
          .ofNullable((String) attributes.get("profile_image"))
          .orElseGet(() -> "");
    }
  }


}
