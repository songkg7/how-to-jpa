package com.fastcampus.jpa.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "user",
        indexes = {@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    // NOTE: enum 의 기본값이 ORDINAL 이기 때문에 반드시 STRING 으로 바꿔줘야한다.
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(updatable = false) // update 시에 반영하지 않는다.
    private LocalDateTime createdAt;

//    @Column(insertable = false)
    private LocalDateTime updatedAt;



//    @Transient // 영속성 처리에서 제외하여 데이터베이스에 반영하지 않는 어노테이션
//    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
}
