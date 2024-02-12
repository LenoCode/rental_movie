package org.test.casumo.modules.rent.domain.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.test.casumo.modules.rent.domain.dao.CustomerDao;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;
import org.test.casumo.modules.rent.domain.dao.RentsDao;
import org.test.casumo.modules.rent.domain.dto.response.CustomerDto;
import org.test.casumo.modules.rent.domain.dto.response.MovieDto;
import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentMapper {

    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);


    @Mapping(source = "customerDao",target = "customer",qualifiedByName = "extractCustomerDto")
    @Mapping(source = "movieDao",target = "movie",qualifiedByName = "extractMovieDao")
    RentDetailsDto generalMapping(RentsDao rentsDao);


    @Named("extractCustomerDto")
    public static CustomerDto extractCustomerDto(CustomerDao customerDao){
        return CustomerMapperImpl.INSTANCE.generalMapping(customerDao);
    }

    @Named("extractMovieDao")
    public static MovieDto extractMovieDao(MovieDao movieDao){
        return MovieMapperImpl.INSTANCE.generalMapping(movieDao);
    }
}
