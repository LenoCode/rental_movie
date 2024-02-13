package org.test.casumo.modules.rent.controller.api.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.casumo.modules.rent.domain.dto.request.RentMoviesDto;
import org.test.casumo.modules.rent.domain.dto.request.ReturnMoviesDto;
import org.test.casumo.modules.rent.domain.dto.response.RentDetailsDto;
import org.test.casumo.modules.rent.domain.dto.response.ReturnDetailsDto;
import org.test.casumo.modules.rent.service.domain.RentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rent")
public class RentController {

    @Autowired
    private RentService rentService;


    @PostMapping()
    public ResponseEntity<List<RentDetailsDto>> rent(@RequestBody RentMoviesDto rentMoviesDto){
        try {
            List<RentDetailsDto> result = new ArrayList<>();
            rentMoviesDto.getMovies().forEach(movie ->
                    {
                        try {
                            result.add(rentService.rentMovie(rentMoviesDto.getCustomer(),movie,rentMoviesDto.getDuration()));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    );
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/return")
    public ResponseEntity<ReturnDetailsDto> returnMovie(@RequestBody ReturnMoviesDto returnMoviesDto){
        return ResponseEntity.ok(rentService.returnMovies(returnMoviesDto.getMovies()));
    }
}
