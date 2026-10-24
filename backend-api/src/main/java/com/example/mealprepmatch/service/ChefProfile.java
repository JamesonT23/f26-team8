package com.example.mealprepmatch.service;

import com.example.mealprepmatch.entity.Chef;
import com.example.mealprepmatch.repository.ChefRepo;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class ChefProfile {

    private final ChefRepo chefRepo;

    public ChefProfile(ChefRepo chefRepo) {
        this.chefRepo = chefRepo;
    }

    public List<Chef> getAllChefs() {
        return chefRepo.findAll();
    }

    public Chef getChefById(Long id) {
        Optional<Chef> chef = chefRepo.findById(id);

        if (chef.isPresent()) {
            return chef.get();
        } else {
            throw new NotFoundException("Chef not found");
        }
    }

    public Chef createChef(Chef chef) {
        return chefRepo.save(chef);
    }

    public Chef updateChef(Long id, Chef updatedChef) {
        Chef chef = getChefById(id);

        chef.setName(updatedChef.getName());
        chef.setEmail(updatedChef.getEmail());
        chef.setPhoneNumber(updatedChef.getPhoneNumber());
        chef.setBio(updatedChef.getBio());

        return chefRepo.save(chef);
    }

    public void deleteChef(Long id) {
        Chef chef = getChefById(id);
        chefRepo.delete(chef);
    }
}