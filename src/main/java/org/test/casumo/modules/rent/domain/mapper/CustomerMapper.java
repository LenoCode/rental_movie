package org.test.casumo.modules.rent.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dto.response.CustomerDto;
import org.test.casumo.modules.rent.domain.dto.response.MovieDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    CustomerDto generalMapping(CustomerDao movieDao);
}
