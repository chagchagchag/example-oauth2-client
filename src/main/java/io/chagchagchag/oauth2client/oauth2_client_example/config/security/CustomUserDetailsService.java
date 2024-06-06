package io.chagchagchag.oauth2client.oauth2_client_example.config.security;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.mapper.MemberEntityMapper;
import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository.MemberR2dbcSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements ReactiveUserDetailsService {
  private final MemberR2dbcSearchRepository memberR2dbcSearchRepository;
  private final MemberEntityMapper memberEntityMapper;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return memberR2dbcSearchRepository
        .findMemberEntityByEmail(username)
        .map(memberEntity -> memberEntityMapper.defaultUserDetails(memberEntity));
  }
}
