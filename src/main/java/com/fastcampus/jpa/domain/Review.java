package com.fastcampus.jpa.domain;

import javax.persistence.*;

import lombok.*;

/**
 * @author songkg7
 * @since 2021/06/11 10:00 오후
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

}
