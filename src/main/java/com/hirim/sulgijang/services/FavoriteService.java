package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Favorite;
import com.hirim.sulgijang.repositories.FavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public void insertFavorite(Favorite favorite) {
        favoriteRepository.insertFavoriteParty(favorite);
    }
}
