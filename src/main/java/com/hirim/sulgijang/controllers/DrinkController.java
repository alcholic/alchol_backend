package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.utils.UserSessionUtils;
import com.hirim.sulgijang.models.Drink;
import com.hirim.sulgijang.models.DrinkParam;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DrinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/drink")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping("/save")
    @ApiOperation(value="다이어리 컨텐트 내 술 저장")
    public CommonResponse updateDrinkList(HttpServletRequest request, @RequestBody DrinkParam drinkParam) {
        User user = UserSessionUtils.UserBySession(request);

        drinkService.insertDrinkList(user, drinkParam);
        return CommonResponse.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "다이어리 컨텐트 별 술 리스트")
    public CommonResponse searchDrinkList(@RequestParam long diaryContentId) {
        return CommonResponse.successObject(drinkService.selectDrinkList(diaryContentId));
    }
}
