package org.test.casumo.modules.rent.domain.dao;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "movie_type")
@Getter
@Setter
@NoArgsConstructor

public class MovieTypeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "type")
    private String type;


    @OneToMany(mappedBy = "movieTypeDao",fetch = FetchType.LAZY)
    private Set<MovieDao> movies;

    @Column(name="price")
    private int price;
}
