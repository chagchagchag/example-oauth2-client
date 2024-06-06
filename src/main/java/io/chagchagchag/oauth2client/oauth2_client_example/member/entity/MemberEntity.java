package io.chagchagchag.oauth2client.oauth2_client_example.member.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("MEMBER")
@AllArgsConstructor(staticName = "ofAll")
public class MemberEntity {
  @Id
  private Long id;

  private String name;
  private String email;
  private String password;
  private String roles;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @PersistenceCreator
  public MemberEntity(
      Long id, String name, String email, String password, String roles
  ){
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  @Builder(builderClassName = "CreateUserBuilder", builderMethodName = "createBuilder")
  public MemberEntity(
      String name, String email, String password, String roles
  ){
    this(null, name, email, password, roles);
  }

}
