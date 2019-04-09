package com.hirim.sulgijang.models.param;

import com.hirim.sulgijang.models.Drink;
import lombok.Data;

import java.util.List;

@Data
public class DrinkParam {
    private long drinkId;
    private long diaryContentId;
    private List<Drink> drinkList;
}
