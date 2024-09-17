package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.exceptions.UnknownCardTypeException;
import com.canvas.TechShop.models.Payment;
import com.canvas.TechShop.repositories.PaymentRepository;
import com.canvas.TechShop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment add(Payment payment) {
        return getPayment(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment update(Payment payment) {
        return getPayment(payment);
    }

    @Override
    public void removeById(Long id) {
        paymentRepository.deleteById(id);
    }

    private Payment getPayment(Payment payment) {
        if (payment.getDateValue().length() > 4 || payment.getCvv().length() > 3){
            throw new RuntimeException();
        }
        try{
            Long checkCardNumber = Long.parseLong(payment.getCardNumber());
            Long checkDateValue = Long.parseLong(payment.getDateValue());
            Long checkCvv = Long.parseLong(payment.getCvv());
        }catch (NumberFormatException e){
            throw new RuntimeException(e);
        }
        payment.setName(payment.getName().toUpperCase());
        String dateValue1 = payment.getDateValue().substring(0,2);
        String dateValue2 = payment.getDateValue().substring(2,4);
        payment.setDateValue(dateValue1+"/"+dateValue2);
        payment.setCvv(passwordEncoder.encode(payment.getCvv()));
        if (payment.getCardNumber().charAt(0) == '4'){
            payment.setType("VISA");
        }else if (payment.getCardNumber().charAt(0) == '5'){
            payment.setType("MasterCARD");
        }else {
            throw new UnknownCardTypeException("Unknown type!");
        }
        return paymentRepository.save(payment);
    }
}
