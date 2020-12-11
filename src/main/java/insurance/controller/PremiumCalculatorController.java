package insurance.controller;

import insurance.model.Policy;
import insurance.service.PremiumCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/insurance")
public class PremiumCalculatorController {

    @Autowired
    PremiumCalculatorService premiumCalculatorService;

    @PostMapping("/calculate")
    public String calculate(@RequestBody Policy policy) {
        String total = premiumCalculatorService.calculate(policy);
        return total;
    }

}