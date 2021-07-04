package com.fastcampus.jpa.domain;

import com.fastcampus.jpa.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "home_city")),
        @AttributeOverride(name = "district", column = @Column(name = "home_district")),
        @AttributeOverride(name = "detail", column = @Column(name = "home_ditail")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "home_zipcode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "company_city")),
        @AttributeOverride(name = "district", column = @Column(name = "company_district")),
        @AttributeOverride(name = "detail", column = @Column(name = "company_ditail")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "company_zipcode"))
    })
    private Address companyAddress;

}
