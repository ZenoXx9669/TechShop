package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.auth.Role;
import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.auth.UserRepository;
import com.canvas.TechShop.code.CodeForConfirm;
import com.canvas.TechShop.models.Owner;
import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.repositories.OwnerRepository;
import com.canvas.TechShop.service.OwnerService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    private CodeForConfirm globalConfirm;
    private Owner globalOwner;
    @Override
    public List<Product> getUserProductsInOwner(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Owner owner = ownerRepository.findByUser(user).orElseThrow(() -> new RuntimeException("Favorite not found"));
        return owner.getProducts();
    }

    @Override
    public void toBecomeSalesman(Owner owner) {
        Owner fromDb = ownerRepository.findById(owner.getId()).orElse(null);
        assert fromDb != null;
        owner.setUser(fromDb.getUser());
        List<Role> roles = owner.getUser().getRoles();
        Role role = new Role();
        role.setId(2L);
        roles.add(role);
        User user = owner.getUser();
        user.setRoles(roles);
        owner.setUser(user);
        CodeForConfirm confirmCode = new CodeForConfirm();
        sendVerificationEmail(user.getEmail(), String.valueOf(confirmCode.getCode()));
        globalConfirm = confirmCode;
        globalOwner = owner;
        ownerRepository.save(owner);
    }
    @Override
    public Owner checkCode(short code) {
        Owner owner = globalOwner;
        CodeForConfirm checkConfirm = globalConfirm;
        if (compareToCode(checkConfirm, code)) {
            return ownerRepository.save(owner);
        }
        return null;
    }

    @Override
    public Owner add(Owner owner) {
        return ownerRepository.save(owner);
    }

    private boolean compareToCode(CodeForConfirm confirm, short equalsCode) {
        return confirm.getCode() == equalsCode;
    }

    private void sendVerificationEmail(String to, String code) {
        String subject = "Ваш код подтверждения от " + "Название Компании";
        String text = "Спасибо, что выбрали наш компанию. Ваш код подтверждения: " + code;

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
