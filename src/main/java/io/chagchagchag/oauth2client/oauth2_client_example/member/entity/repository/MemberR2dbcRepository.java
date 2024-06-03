package io.chagchagchag.oauth2client.oauth2_client_example.member.entity.repository;

import io.chagchagchag.oauth2client.oauth2_client_example.member.entity.MemberEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface MemberR2dbcRepository extends R2dbcRepository<MemberEntity, Long> {
}
