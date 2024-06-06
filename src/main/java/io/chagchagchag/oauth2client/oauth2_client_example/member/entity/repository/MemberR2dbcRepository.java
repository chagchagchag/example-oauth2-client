package io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.MemberEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface MemberR2dbcRepository extends ReactiveSortingRepository<MemberEntity, Long> {
  Mono<MemberEntity> findMemberEntityByEmail(String email);
}
