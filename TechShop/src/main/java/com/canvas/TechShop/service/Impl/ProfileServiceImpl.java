package com.canvas.TechShop.service.Impl;

import com.canvas.TechShop.models.TechProfile;
import com.canvas.TechShop.repositories.ProductRepository;
import com.canvas.TechShop.repositories.ProfileRepository;
import com.canvas.TechShop.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public List<TechProfile> getAll() {
        return List.of();
    }

    @Override
    public TechProfile add(TechProfile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public TechProfile getById(Long id) {
        return profileRepository.findById(id).orElse(null);
    }

    @Override
    public TechProfile update(TechProfile updProfile) {
        TechProfile techProfile = getById(updProfile.getTechShopId());
        updProfile.setConfirmEmail(techProfile.isConfirmEmail());
        updProfile.setEmail(techProfile.getEmail());
        updProfile.setDateOfCreated(techProfile.getDateOfCreated());
        return profileRepository.save(updProfile);
    }

    @Override
    public void removeById(Long id) {

    }
}
