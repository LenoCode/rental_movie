package org.test.casumo.modules.rent.domain.dao;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "rents")
@Getter
@Setter
public class RentsDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @ManyToOne
    @JoinColumn(name = "movie",referencedColumnName = "id")
    private MovieDao movieDao;


    @ManyToOne
    @JoinColumn(name = "customer",referencedColumnName = "id")
    private CustomerDao customerDao;

    @Column(name = "return_date")
    private Timestamp returnDate;


    @Column(name="active")
    private boolean active;

    @Column(name="price")
    private int price;

}
