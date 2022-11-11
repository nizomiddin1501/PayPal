package uz.developers.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.paypal.entity.Outcome;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.OutcomeDto;
import uz.developers.paypal.service.OutcomeService;

import java.util.List;

@RestController
@RequestMapping("/api/outcome")
public class OutcomeController {


    @Autowired
    OutcomeService outcomeService;

    @GetMapping
    public ResponseEntity<List<Outcome>> getOutcomes() {
        List<Outcome> answers = outcomeService.getOutcomes();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answers);
    }

    @GetMapping("/{id}")
    public Outcome getOutcome(@PathVariable Integer id) {
        return outcomeService.getOutcome(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponce> addOutcome(@RequestBody OutcomeDto outcomeDto) {
        ApiResponce apiResponce = outcomeService.addOutcome(outcomeDto);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce> editOutcome(@PathVariable Integer id, @RequestBody OutcomeDto outcomeDto) {
        ApiResponce apiResponce = outcomeService.editOutcome(id,outcomeDto);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOutcome(@PathVariable Integer id) {
        ApiResponce apiResponce = outcomeService.deleteOutcome(id);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }



}
