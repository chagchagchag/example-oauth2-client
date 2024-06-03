package io.chagchagchag.oauth2client.oauth2_client_example.member.entity;

import java.time.LocalDateTime;
import java.util.List;
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
  private String roles;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @PersistenceCreator
  public MemberEntity(
      Long id, String name, String email, String roles
  ){
    this.id = id;
    this.name = name;
    this.email = email;
    this.roles = roles;
  }

  @Builder(builderClassName = "CreateUserBuilder", builderMethodName = "createBuilder")
  public MemberEntity(
      String name, String email, String roles
  ){
    this(null, name, email, roles);
  }

  // 일반적으로는 getRoleList() 라고 쓰는 편이지만,
  // Lombok 과 혼동의 여지가 있어서 parse** 으로 지정
  public List<String> parseRoleList(){
    if(roles.length() > 0)
      return List.of(roles.split(","));
    else
      return List.of();
  }
}
