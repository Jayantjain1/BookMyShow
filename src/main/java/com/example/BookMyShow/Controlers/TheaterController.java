package com.example.BookMyShow.Controlers;

import com.example.BookMyShow.DTOS.TheaterRequestDTO;
import com.example.BookMyShow.Model.TheaterEntity;
import com.example.BookMyShow.ResoponseDTO.TheaterReponseDTO;
import com.example.BookMyShow.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add-theater")
    public ResponseEntity<String> addTheater(@RequestBody() TheaterRequestDTO theaterRequestDTO){
        try{
            String reponse = theaterService.createTheater(theaterRequestDTO);
            return new ResponseEntity<>(reponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-theater-by-id")
    public ResponseEntity<TheaterReponseDTO> getTheaterById(@RequestParam("id") int theaterId){
        try {
            TheaterReponseDTO theaterReponseDTO = theaterService.getTheaterById(theaterId);
            return new ResponseEntity<>(theaterReponseDTO , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-theaters-by-moviename")
    public ResponseEntity<List<TheaterReponseDTO>> getAllTheaterByMoviename(@RequestParam("name") String movieName){
        try{
            List<TheaterReponseDTO> theaterReponseDTOList = theaterService.getAllTheaterByMovieName(movieName);
            return new ResponseEntity<>(theaterReponseDTOList,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-all-theater")
    public ResponseEntity<List<TheaterReponseDTO>> getAllTheater(){
        try{
            List<TheaterReponseDTO> theaterReponseDTOList = theaterService.getAllTheater();
            return new ResponseEntity<>(theaterReponseDTOList,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }

    //Get theaters by theaterId

}
