package org.test.casumo.modules.rent.service.domain;

import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public interface RentService {


    /**
     * Rent movie
     * @param customerId
     * @param movieId
     * @return
     */
    RentDetailsDto rentMovie(long customerId,long movieId,int days) throws Exception;


    /**
     *
     * @param movieId
     * @return
     */
    boolean isMoveAvailable(long movieId);





    /**
     *
     * @param date
     * @return
     */
    public static Timestamp getTimestampOfRentDate(int date){
        Date localDate = Date.from( LocalDate.now().plusDays(date).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new Timestamp(localDate.getTime());
    }
}
