package com.example.mealprepmatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mealprepmatch.entity.Consumer;
import com.example.mealprepmatch.repository.ConsumerRepo;

@Service 
public class ConsumerService {

    private final ConsumerRepo consumerRepo;

    public ConsumerService(ConsumerRepo consumerRepo) {
        this.consumerRepo = consumerRepo;
    }

    public List<Consumer> getAllConsumers() {
        return consumerRepo.findAll();
    }

    public Consumer getConsumerById(Long id) {
        Optional<Consumer> consumer = consumerRepo.findById(id);

        if (consumer.isPresent()) {
            return consumer.get();
        } else {
            throw new NotFoundException("Consumer not found");
        }
    }

    public Consumer createConsumer(Consumer consumer) {
        return consumerRepo.save(consumer);
    }

    public Consumer updateConsumer(Long id, Consumer updatedConsumer) {
        Consumer consumer = getConsumerById(id);

        consumer.setName(updatedConsumer.getName());
        consumer.setEmail(updatedConsumer.getEmail());
        consumer.setPhoneNumber(updatedConsumer.getPhoneNumber());
        consumer.setMealPlanList(updatedConsumer.getMealPlanList());
        consumer.setPreferredTags(updatedConsumer.getPreferredTags());
        consumer.setBlacklistedTags(updatedConsumer.getBlacklistedTags());

        return consumerRepo.save(consumer);
    }

    public void deleteConsumer(Long id) {
        Consumer consumer = getConsumerById(id);
        consumerRepo.delete(consumer);
    }
}

