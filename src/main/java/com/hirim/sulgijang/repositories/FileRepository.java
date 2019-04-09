package com.hirim.sulgijang.repositories;

import com.hirim.sulgijang.models.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FileRepository {
    void insertFile(Photo photo);
}
