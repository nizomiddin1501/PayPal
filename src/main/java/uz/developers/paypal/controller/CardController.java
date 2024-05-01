package uz.developers.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.paypal.entity.Card;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.CardDto;
import uz.developers.paypal.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {


    @Autowired
    CardService cardService;

    @GetMapping
    public ResponseEntity<?> getCards(HttpServletRequest request) {
        List<Card> answers = cardService.getCards(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answers);
    }

    @GetMapping("/{id}")
    public Card getCard(@PathVariable Integer id,HttpServletRequest request) {
        return cardService.getCard(id,request);
    }

    @PostMapping
    public ResponseEntity<ApiResponce> addCard(@RequestBody CardDto cardDto,HttpServletRequest request) {
        ApiResponce apiResponce = cardService.addCard(cardDto,request);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce> editCard(@PathVariable Integer id, @RequestBody CardDto cardDto,HttpServletRequest request) {
        ApiResponce apiResponce = cardService.editCard(id,cardDto,request);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Integer id,HttpServletRequest request) {
        ApiResponce apiResponce = cardService.deleteCard(id,request);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }

}
