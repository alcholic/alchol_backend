package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FavoriteRepository {
    void insertFavoriteParty(Favorite favorite);
    void deleteFavoriteParty(long favoriteId);
}
