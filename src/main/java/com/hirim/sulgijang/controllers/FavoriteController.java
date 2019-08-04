package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.models.Favorite;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.FavoriteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/")
    @ApiOperation(value = "모임 즐겨찾기 추가")
    public CommonResponse insertFavoriteParty(HttpServletRequest request, @RequestBody Favorite favorite) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        favorite.setUserId(0/*userInfo.getUserId()*/);
        favoriteService.insertFavorite(favorite);

        return CommonResponse.success();
    }

    @DeleteMapping("/{favoriteId}")
    @ApiOperation(value = "모임 즐겨찾기 삭제")
    public CommonResponse deleteFavoriteParty(@PathVariable long favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return CommonResponse.success();
    }
}
