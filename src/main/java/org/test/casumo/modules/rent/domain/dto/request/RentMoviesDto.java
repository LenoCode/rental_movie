package org.test.casumo.modules.rent.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class RentMoviesDto {

    private List<Long> movies;
    private long customer;
    private int duration;
}
