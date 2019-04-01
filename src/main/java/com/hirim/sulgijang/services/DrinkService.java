package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Drink;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.repositories.DrinkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public void insertDrinkList(User user, List<Drink> drinkList) {
        drinkList.stream()
                .peek(drink -> drink.setCreatedBy(0))
                .peek(drink -> drinkRepository.insertDrink(drink))
                .collect(Collectors.toList());
    }
}
