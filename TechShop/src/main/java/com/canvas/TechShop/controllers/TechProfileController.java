package com.canvas.TechShop.controllers;

import com.canvas.TechShop.models.TechProfile;
import com.canvas.TechShop.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/my/profile")
public class TechProfileController {
    private final ProfileService profileService;

    @PutMapping("/change")
    public TechProfile changeProfile(@RequestBody TechProfile techProfile){
        return profileService.update(techProfile);
    }
    @GetMapping("/{id}")
    public TechProfile getProfile(@PathVariable Long id){
        return profileService.getById(id);
    }

}
