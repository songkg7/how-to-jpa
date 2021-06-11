package com.fastcampus.jpa.domain;

import com.fastcampus.jpa.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "user",
        indexes = {@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    // NOTE: enum 의 기본값이 ORDINAL 이기 때문에 반드시 STRING 으로 바꿔줘야한다.
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
