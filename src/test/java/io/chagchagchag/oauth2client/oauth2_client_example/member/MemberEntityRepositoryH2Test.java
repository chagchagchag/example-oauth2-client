package io.chagchagchag.oauth2client.oauth2_client_example.member;

import io.chagchagchag.oauth2client.oauth2_client_example.config.H2R2dbcConfig;
import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.MemberEntity;
import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.factory.MemberEntityFactory;
import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository.MemberR2dbcCommandRepository;
import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository.MemberR2dbcSearchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ActiveProfiles({"h2"})
@Import(H2R2dbcConfig.class)
@DataR2dbcTest
public class MemberEntityRepositoryH2Test {
  @Autowired
  private MemberR2dbcSearchRepository memberR2DbcSearchRepository;
  @Autowired
  private MemberR2dbcCommandRepository memberR2dbcCommandRepository;
  private MemberEntityFactory memberEntityFactory;

  @Test
  public void TEST_MEMBER_INSERT(){
    memberEntityFactory = new MemberEntityFactory();

    // given
    MemberEntity member = memberEntityFactory.ofCreateRoleMember("제시린가드", "abc@gmail.com");

    // when
    Mono<MemberEntity> saved = memberR2dbcCommandRepository.save(member)
        .flatMap(memberEntity -> memberR2DbcSearchRepository.findMemberEntityByEmail(member.getEmail()));

    // then
    StepVerifier.create(saved)
        .expectNextMatches(memberEntity -> memberEntity.getEmail().equals(member.getEmail()));
  }
}
