package com.genspark.SpringBootProject22.Controller;

import com.genspark.SpringBootProject22.Entity.Card;
import com.genspark.SpringBootProject22.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private CardService cardService;
    //mapping for CRUD functions
    @GetMapping("/home")
    public String home(){return "Hello";}
    @GetMapping("/Cards")
    public List<Card> getcards(){return this.cardService.getAllCards();}
    @GetMapping("/Cards/Draw")
    public String getRandomCard(){return this.cardService.getRandomCard();}
    @PostMapping("/Cards")
    public Card addCard(@RequestBody Card card){return this.cardService.addCard(card);}
    @PutMapping("/Cards")
    public Card updateCard(@RequestBody Card card) {return this.cardService.updateCard(card);}
    @DeleteMapping("/Cards/{index}")
    public String deleteCard(@PathVariable String index) {return this.cardService.deleteCard(Integer.parseInt(index));}
}
