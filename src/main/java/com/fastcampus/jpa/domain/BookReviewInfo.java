package com.fastcampus.jpa.domain;

import javax.persistence.*;

import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false) // optional = false 를 하면 left outer join 이 inner join 으로 변한다.
    private Book book;

    private float averageReviewScore;

    private int reviewCount;

}
