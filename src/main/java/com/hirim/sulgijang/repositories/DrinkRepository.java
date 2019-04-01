package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Drink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DrinkRepository {
    void insertDrink(Drink drinkList);
}
