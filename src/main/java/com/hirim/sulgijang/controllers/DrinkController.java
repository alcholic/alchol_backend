package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.param.DrinkParam;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DrinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @PostMapping("/")
    @ApiOperation(value="술 탭 내 술 저장")
    public CommonResponse updateDrinkList(HttpServletRequest request, @RequestBody DrinkParam drinkParam) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        drinkService.insertDrinkList(0 /*userInfo.getUserId()*/, drinkParam);
        return CommonResponse.success();
    }

    @GetMapping("/")
    @ApiOperation(value = "술 탭 내 술 리스트")
    public CommonResponse searchDrinkList(@RequestParam long diaryContentId) {
        return CommonResponse.successObject(drinkService.selectDrinkList(diaryContentId));
    }
}
