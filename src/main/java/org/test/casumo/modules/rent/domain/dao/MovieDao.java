package org.test.casumo.modules.rent.domain.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
public class MovieDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type",referencedColumnName = "id")
    private MovieTypeDao movieTypeDao;
}
