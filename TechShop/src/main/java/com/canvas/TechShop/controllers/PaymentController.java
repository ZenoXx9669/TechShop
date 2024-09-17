package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.Payment;
import com.canvas.TechShop.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPaymentMethod(@RequestBody Payment payment){
        return paymentService.add(payment);
    }
    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable Long id){
        paymentService.removeById(id);
    }
}
