package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.repositories.DiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void insertDiary(Diary diary) { diaryRepository.insertDiary(diary); }

    @Transactional
    public void insertDiaryContent(DiaryContent diaryContent) {
        /*List<DiaryContent> diaryContentList = diaryRepository.selectDiaryContentList(diaryContent.getDiaryId());
        long lastDepth = Optional.ofNullable(diaryContentList.stream().count()).orElse((long) 0);*/

        //long lastDepth = diaryRepository.selectDiaryContentList(diaryContent.getDiaryId()).stream().count();
        long lastDepth = diaryRepository.selectDiaryContentList(3).stream().count();

        diaryContent.setDepth(lastDepth+1);

        diaryRepository.insertDiaryContent(diaryContent);
    }

    public List<Diary> selectDiaryList(long diaryId) { return diaryRepository.selectDiaryList(diaryId); }

    public Diary selectDiary(long diaryId, String diaryName) { return diaryRepository.selectDiary(diaryId, diaryName); }
}
