package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Drink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DrinkRepository {
    void insertDrink(Drink drink);
    void deleteDrinkList(long diaryContentId);
    List<Drink> selectDrinkList(long diaryContentId);
}
