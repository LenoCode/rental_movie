package org.test.casumo.modules.rent.domain.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name="name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "customerDao",fetch = FetchType.LAZY)
    private Set<RentsDao> rentsDao;


}
