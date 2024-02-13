package org.test.casumo.modules.rent.service.domain;

import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;
import org.test.casumo.modules.rent.domain.dto.response.ReturnDetailsDto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
     */
    ReturnDetailsDto returnMovies(List<Long> movies);

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


    /**
     *
     * @param timestamp
     * @return
     */
    public static long checkHowManyDaysLate(Timestamp timestamp){
        Date now = new Date(System.currentTimeMillis());
        Date rentDate = new Date(timestamp.getTime());
        long differentInMilies =(now.getTime() - rentDate.getTime());
        if(differentInMilies < 0)
            return 0;
        else
            return  TimeUnit.DAYS.convert(Math.abs(differentInMilies), TimeUnit.MILLISECONDS);
    }
}
