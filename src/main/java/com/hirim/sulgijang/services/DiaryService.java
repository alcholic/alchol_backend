package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.repositories.DiaryRepository;
import com.hirim.sulgijang.repositories.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DrinkRepository drinkRepository;

    public DiaryService(DiaryRepository diaryRepository, DrinkRepository drinkRepository) {
        this.diaryRepository = diaryRepository;
        this.drinkRepository = drinkRepository;
    }

    public void insertDiary(Diary diary) { diaryRepository.insertDiary(diary); }

    public void insertDiaryContent(DiaryContent diaryContent) {
        long lastDepth = diaryRepository.selectDiaryContentList(diaryContent.getDiaryId()).stream().count();
        diaryContent.setDepth(lastDepth+1);

        diaryRepository.insertDiaryContent(diaryContent);
    }

    public void updateDiary(Diary diary){ diaryRepository.updateDiary(diary);}

    public void updateDiaryContent(DiaryContent diaryContent){ diaryRepository.updateDiaryContent(diaryContent); }

    @Transactional
    public void deleteDiary(long diaryId) {
        diaryRepository.deleteDiary(diaryId);
        diaryRepository.selectDiaryContentList(diaryId).stream()
                .forEach(i -> {
                    diaryRepository.deleteDiaryContent(i.getDiaryContentId());
                    drinkRepository.deleteDrinkList(i.getDiaryContentId());
                });
    }

    public void deleteDiaryContent(long diaryContentId) {
        diaryRepository.deleteDiaryContent(diaryContentId);
    }

    public List<Diary> selectDiaryList(long diaryId, long partyId, String privateYn) { return diaryRepository.selectDiaryList(diaryId, partyId, privateYn); }

    public List<DiaryContent> selectDiaryContentList(long diaryId) {
        return diaryRepository.selectDiaryContentList(diaryId);
    }

    public Diary selectDiary(long diaryId, String diaryName) { return diaryRepository.selectDiary(diaryId, diaryName); }
}
