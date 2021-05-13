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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;
}
