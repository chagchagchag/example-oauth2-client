package io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.MemberEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MemberR2dbcCommandRepository extends ReactiveCrudRepository<MemberEntity, Long> {

}
