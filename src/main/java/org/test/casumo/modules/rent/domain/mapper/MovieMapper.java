package org.test.casumo.modules.rent.domain.mapper;


import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.test.casumo.modules.rent.domain.dao.MovieDao;
import org.test.casumo.modules.rent.domain.dao.MovieTypeDao;
import org.test.casumo.modules.rent.domain.dto.response.MovieDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(source = "movieTypeDao", target = "type", qualifiedByName = "extractType")
    MovieDto generalMapping(MovieDao movieDao);



    @Named("extractType")
    public static String extractType(MovieTypeDao movieTypeDao){
        return movieTypeDao.getType();
    }
}
