package com.canvas.TechShop.auth.services;


import com.canvas.TechShop.auth.Role;
import com.canvas.TechShop.auth.User;
import com.canvas.TechShop.auth.UserRepository;
import com.canvas.TechShop.code.CodeForConfirm;
import com.canvas.TechShop.exceptions.EmailExistException;
import com.canvas.TechShop.exceptions.PasswordsNotMatchException;
import com.canvas.TechShop.models.Owner;
import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.models.TechProfile;
import com.canvas.TechShop.repositories.OwnerRepository;
import com.canvas.TechShop.repositories.ProductRepository;
import com.canvas.TechShop.service.Impl.OwnerServiceImpl;
import com.canvas.TechShop.service.OwnerService;
import com.canvas.TechShop.service.ProfileService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OwnerRepository ownerRepository;

    private final JavaMailSender javaMailSender;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final OwnerService ownerService;

    private final ProfileService profileService;
    private final OwnerServiceImpl ownerServiceImpl;


    private CodeForConfirm globalConfirm;
    private User globalUser;
    private TechProfile globalProfile;
    private Owner globalOwner;

    @Override
    public void register(User user) {
        TechProfile techProfile = new TechProfile();
        String dateOfCreated = LocalDateTime.now().toString().substring(0, 10);

        if (!user.getEmail().isEmpty()) {
            techProfile.setEmail(user.getEmail());
            techProfile.setConfirmEmail(true);
        }
        techProfile.setDateOfCreated(dateOfCreated);

        if (!userRepository.existsByEmail(user.getEmail())) {
            if (user.getPassword().equals(user.getRePassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                List<Role> roles = new ArrayList<>();
                Role role = new Role();
                role.setId(1L);
                roles.add(role);
                user.setRoles(roles);


                CodeForConfirm confirmCode = new CodeForConfirm();
                sendVerificationEmail(user.getEmail(), String.valueOf(confirmCode.getCode()));
                globalConfirm = confirmCode;
                globalUser = user;
                globalProfile = techProfile;
            } else {
                throw new PasswordsNotMatchException("Passwords not match, please repeat!");
            }
        } else {
            throw new EmailExistException("Email exists!");
        }
    }


    public User checkCode(short code) {
        User user = globalUser;
        TechProfile techProfile = globalProfile;
        CodeForConfirm checkConfirm = globalConfirm;
        if (compareToCode(checkConfirm, code)) {
            profileService.add(techProfile);
            User toDb = userRepository.save(user);
            Owner owner = new Owner();
            owner.setActivated(false);
            owner.setUser(user);
            user.setOwner(owner);
            ownerService.add(owner);
            return toDb;
        }
        return null;
    }

    @Override
    public User login(String email, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            return (User) authentication.getPrincipal();
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Неверный email или пароль");
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByEmail(username);
        if (user == null){
            throw new UsernameNotFoundException("username not found");
        }
        return user;
    }

//    @Override
//    public List<Product> getAllUserProducts(Long userId){
//        User user = userRepository.findById(userId).orElse(null);
//        return productRepository.findAllByUser(user);
//    }


    //PRIVATE METHODS
    private boolean compareToCode(CodeForConfirm confirm, short equalsCode) {
        return confirm.getCode() == equalsCode;
    }

    private void sendVerificationEmail(String to, String code) {
        String subject = "Ваш код подтверждения от " + "Название Компании";
        String text = "Спасибо, что выбрали наш университет. Ваш код подтверждения: " + code;

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
