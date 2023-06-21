package com.lipari.app.utils;

import com.lipari.app.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class RandomId {
    @Autowired
    private OrderRepository orderRepository;
    public String generateVarchar() {
        int varcharLength = 36;
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomVarchar = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < varcharLength; i++) {
            int index = random.nextInt(characters.length());
            randomVarchar.append(characters.charAt(index));
        }

        String string = randomVarchar.toString();
        while (orderRepository.existsById(string)){
            string = randomVarchar.toString();
        }
        return string;
    }
}
