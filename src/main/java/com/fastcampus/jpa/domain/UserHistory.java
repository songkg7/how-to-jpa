package com.fastcampus.jpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @Enumerated(EnumType.STRING)
    private Gender gender;

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
