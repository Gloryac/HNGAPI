package com.example.funFactAPI.controller;

import com.example.funFactAPI.model.NumberProperties;
import com.example.funFactAPI.service.NumberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class NumberController {
    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @GetMapping("/classify-number")
    public ResponseEntity<?> classifyNumber(@RequestParam(value = "number") String numStr) {
        try {
            int number = Integer.parseInt(numStr);
            NumberProperties props = numberService.classifyNumber(number);

            // Use LinkedHashMap to preserve order
            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            response.put("number", props.getNumber());
            response.put("is_prime", props.isPrime());
            response.put("is_perfect", props.isPerfect());
            response.put("properties", props.getProperties());
            response.put("digit_sum", props.getDigitSum());
            response.put("fun_fact", props.getFunFact());

            return ResponseEntity.ok(response); // Return the LinkedHashMap

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "number", numStr,
                    "error", true
            ));
        }
    }
}