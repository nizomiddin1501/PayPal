package uz.developers.paypal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.developers.paypal.entity.Income;
import uz.developers.paypal.payload.ApiResponce;
import uz.developers.paypal.payload.IncomeDto;
import uz.developers.paypal.service.IncomeService;

import java.util.List;

@RestController
@RequestMapping("/api/income")
public class IncomeController {


    @Autowired
    IncomeService incomeService;

    @GetMapping
    public ResponseEntity<List<Income>> getIncomes() {
        List<Income> answers = incomeService.getIncomes();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(answers);
    }

    @GetMapping("/{id}")
    public Income getIncome(@PathVariable Integer id) {
        return incomeService.getIncome(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponce> addIncome(@RequestBody IncomeDto incomeDto) {
        ApiResponce apiResponce = incomeService.addIncome(incomeDto);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponce> editIncome(@PathVariable Integer id, @RequestBody IncomeDto incomeDto) {
        ApiResponce apiResponce = incomeService.editIncome(id,incomeDto);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIncome(@PathVariable Integer id) {
        ApiResponce apiResponce = incomeService.deleteIncome(id);
        if (apiResponce.isSuccess()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponce);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponce);
    }


}
