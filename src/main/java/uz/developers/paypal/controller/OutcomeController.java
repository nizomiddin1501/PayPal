package uz.developers.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.paypal.entity.Outcome;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.OutcomeDto;
import uz.developers.paypal.service.OutcomeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {


    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public ResponseEntity<List<Outcome>> getOutcomes(HttpServletRequest request) {
        List<Outcome> answers = outcomeService.getOutcomes(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answers);
    }

    @GetMapping("/{id}")
    public Outcome getOutcome(@PathVariable Integer id,HttpServletRequest request) {
        return outcomeService.getOutcome(id,request);
    }

    @PostMapping
    public ResponseEntity<ApiResponce> addOutcome(@RequestBody OutcomeDto outcomeDto,HttpServletRequest request) {
        ApiResponce apiResponce = outcomeService.addOutcome(outcomeDto,request);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);

    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponce> editOutcome(@PathVariable Integer id, @RequestBody OutcomeDto outcomeDto) {
//        ApiResponce apiResponce = outcomeService.editOutcome(id,outcomeDto);
//        if (apiResponce.isSuccess()) {
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOutcome(@PathVariable Integer id, HttpServletRequest request) {
        ApiResponce apiResponce = outcomeService.deleteOutcome(id,request);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }



}
