package com.example.mealprepmatch.mvc;

import java.util.EnumSet;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mealprepmatch.entity.BlacklistTags;   
import com.example.mealprepmatch.entity.Consumer;
import com.example.mealprepmatch.entity.DietTags;       
import com.example.mealprepmatch.service.ConsumerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Controller
@RequestMapping("/ui/consumers")
public class ConsumerUiController {

    private static final String SESSION_KEY = "consumerId";

    private final ConsumerService consumerService;

    public ConsumerUiController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("form", new ConsumerForm());
        addFormOptions(model, "Register", "/ui/consumers/register", true);
        return "consumer/ConsumerRegister";
    }

        @PostMapping("/register")
    public String register(@Valid @ModelAttribute("form") ConsumerForm form,
                           BindingResult result,
                           Model model,
                           HttpSession session,
                           RedirectAttributes flash) {

        if (form.getPassword() == null || form.getPassword().isBlank()) {
            result.rejectValue("password", "password.required", "Password is required");
        }
        
        // ADD THIS BLOCK to see validation errors
        if (result.hasErrors()) {
            System.out.println("❌ VALIDATION FAILED: " + result.getAllErrors());
            addFormOptions(model, "Register", "/ui/consumers/register", true);
            return "consumer/ConsumerRegister";
        }

        Consumer consumer = new Consumer();
        copyFormToConsumer(form, consumer);
        consumer.setPassword(form.getPassword());   
        consumer.setAccountStatus("ACTIVE"); 

        try {
            Consumer saved = consumerService.createConsumer(consumer);
            session.setAttribute(SESSION_KEY, saved.getId());
            System.out.println("✅ SUCCESS: Saved consumer with ID " + saved.getId()); // ADD THIS
        } catch (DataIntegrityViolationException e) {
            // ADD THIS to see the real database error
            System.out.println("❌ DB ERROR: " + e.getMostSpecificCause().getMessage());
            
            result.rejectValue("email", "email.taken", "That email is already registered");
            addFormOptions(model, "Register", "/ui/consumers/register", true);
            return "consumer/ConsumerRegister";
        }

        flash.addFlashAttribute("message", "Welcome! Your account has been created.");
        return "redirect:/ui/consumers/profile";
    }


    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Long id = currentConsumerId(session);
        if (id == null) {
            return "redirect:/ui/consumers/register";
        }
        model.addAttribute("consumer", consumerService.getConsumerById(id));
        return "consumer/ConsumerDashboard";
    }

    @GetMapping("/profile/edit")
    public String editForm(HttpSession session, Model model) {
        Long id = currentConsumerId(session);
        if (id == null) {
            return "redirect:/ui/consumers/register";
        }
        Consumer consumer = consumerService.getConsumerById(id);

        ConsumerForm form = new ConsumerForm();
        form.setName(consumer.getName());
        form.setEmail(consumer.getEmail());
        form.setPhoneNumber(consumer.getPhoneNumber());
        form.setPreferredTags(EnumSet.noneOf(DietTags.class));
        form.getPreferredTags().addAll(consumer.getPreferredTags());
        form.setBlacklistedTags(EnumSet.noneOf(BlacklistTags.class));
        form.getBlacklistedTags().addAll(consumer.getBlacklistedTags());

        model.addAttribute("form", form);
        addFormOptions(model, "Edit Profile", "/ui/consumers/profile", false);
        return "consumer/ConsumerRegister";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("form") ConsumerForm form,
                                BindingResult result,
                                HttpSession session,
                                Model model,
                                RedirectAttributes flash) {
        Long id = currentConsumerId(session);
        if (id == null) {
            return "redirect:/ui/consumers/register";
        }
        if (result.hasErrors()) {
            addFormOptions(model, "Edit Profile", "/ui/consumers/profile", false);
            return "consumer/ConsumerDashboard";
        }

        Consumer changes = new Consumer();
        copyFormToConsumer(form, changes);

        try {
            consumerService.updateConsumer(id, changes);
        } catch (DataIntegrityViolationException e) {
            result.rejectValue("email", "email.taken", "That email is already registered");
            addFormOptions(model, "Edit Profile", "/ui/consumers/profile", false);
            return "consumer/ConsumerDashboard";
        }

        flash.addFlashAttribute("message", "Profile updated.");
        return "redirect:/ui/consumers/profile";
    }

    @PostMapping("/profile/delete")
    public String deleteAccount(HttpSession session, RedirectAttributes flash) {
        Long id = currentConsumerId(session);
        if (id != null) {
            consumerService.deleteConsumer(id);
        }
        session.invalidate();
        return "redirect:/ui/consumers/register";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.html";
    }

    private Long currentConsumerId(HttpSession session) {
        return (Long) session.getAttribute(SESSION_KEY);
    }

    private void copyFormToConsumer(ConsumerForm form, Consumer target) {
        target.setName(form.getName());
        target.setEmail(form.getEmail());
        target.setPhoneNumber(form.getPhoneNumber());
        target.setPreferredTags(form.getPreferredTags());
        target.setBlacklistedTags(form.getBlacklistedTags());
    }

    private void addFormOptions(Model model, String title, String action, boolean registering) {
        model.addAttribute("formTitle", title);
        model.addAttribute("formAction", action);
        model.addAttribute("registering", registering);
        model.addAttribute("allDietTags", DietTags.values());
        model.addAttribute("allBlacklistTags", BlacklistTags.values());
    }

    public static class ConsumerForm {

        @NotBlank
        @Size(max = 100)
        private String name;

        @NotBlank
        @Email
        @Size(max = 254)
        private String email;

        @Size(max = 20)
        private String phoneNumber;

        @Size(min = 8, max = 255, message = "Password must be at least 8 characters")
        private String password;

        private Set<DietTags> preferredTags = EnumSet.noneOf(DietTags.class);
        private Set<BlacklistTags> blacklistedTags = EnumSet.noneOf(BlacklistTags.class);

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhoneNumber() { return phoneNumber; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public Set<DietTags> getPreferredTags() { return preferredTags; }
        public void setPreferredTags(Set<DietTags> preferredTags) { this.preferredTags = preferredTags; }

        public Set<BlacklistTags> getBlacklistedTags() { return blacklistedTags; }
        public void setBlacklistedTags(Set<BlacklistTags> blacklistedTags) { this.blacklistedTags = blacklistedTags; }
    }
}