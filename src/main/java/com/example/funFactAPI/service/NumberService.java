package com.example.funFactAPI.service;

import com.example.funFactAPI.model.NumberProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NumberService {
    private final RestTemplate restTemplate;

    public NumberService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NumberProperties classifyNumber(int number) {
        boolean isPrime = isPrime(number);
        boolean isPerfect = isPerfect(number);
        List<String> properties = new ArrayList<>();

        if (isArmstrong(number)) {
            properties.add("armstrong");
        }
        if (number % 2 == 0) {
            properties.add("even");
        } else {
            properties.add("odd");
        }

        int digitSum = calculateDigitSum(number);
        String funFact = getFunFact(number);

        return new NumberProperties(number, isPrime, isPerfect, properties, digitSum, funFact, false);
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private boolean isPerfect(int n) {
        if (n <= 1) return false;
        int sum = 1;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i * i != n) sum += n / i;
            }
        }
        return sum == n;
    }

    private boolean isArmstrong(int n) {
        String numStr = String.valueOf(n);
        int numDigits = numStr.length();
        int sum = 0;
        for (char c : numStr.toCharArray()) {
            sum += Math.pow(Character.getNumericValue(c), numDigits);
        }
        return sum == n;
    }

    private int calculateDigitSum(int n) {
        return String.valueOf(Math.abs(n))
                .chars()
                .map(Character::getNumericValue)
                .sum();
    }

    private String getFunFact(int number) {
        try {
            return restTemplate.getForObject(
                    "http://numbersapi.com/" + number + "/math",
                    String.class
            );
        } catch (Exception e) {
            return number + " is a number";
        }
    }
}