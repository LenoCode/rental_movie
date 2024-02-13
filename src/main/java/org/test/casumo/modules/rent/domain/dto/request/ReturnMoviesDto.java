package org.test.casumo.modules.rent.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ReturnMoviesDto {
    private List<Long> movies;
}
