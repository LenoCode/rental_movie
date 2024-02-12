package org.test.casumo.modules.rent.domain.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RentDetailsDto {
    private CustomerDto customer;
    private MovieDto movie;
    private Timestamp returnDate;
    private int price;
}
