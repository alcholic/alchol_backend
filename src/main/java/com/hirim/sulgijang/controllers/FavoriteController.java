package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.models.Favorite;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.FavoriteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/")
    public CommonResponse insertFavoriteParty(HttpServletRequest request, @RequestBody Favorite favorite) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        favorite.setUserId(0/*userInfo.getUserId()*/);
        favoriteService.insertFavorite(favorite);

        return CommonResponse.success();
    }
}
