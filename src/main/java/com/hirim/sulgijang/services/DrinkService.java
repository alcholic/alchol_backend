package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Drink;
import com.hirim.sulgijang.models.DrinkParam;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.repositories.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Transactional
    public void insertDrinkList(User user, DrinkParam drinkParam) {
        drinkRepository.deleteDrinkList(drinkParam.getDiaryContentId());

        drinkParam.getDrinkList().stream()
                .peek(drink -> drink.setCreatedBy(0))
                .peek(drink -> drinkRepository.insertDrink(drink))
                .collect(Collectors.toList());
    }

    public List<Drink> selectDrinkList(long diaryContentList) {
        return drinkRepository.selectDrinkList(diaryContentList);
    }
}
