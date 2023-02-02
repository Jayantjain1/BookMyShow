package com.example.BookMyShow.Controlers;

import com.example.BookMyShow.DTOS.ShowRequestDTO;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public String addShow(@RequestBody() ShowRequestDTO showRequestDTO){
        return showService.createShow(showRequestDTO);
    }
}
