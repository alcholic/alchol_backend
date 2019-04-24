package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.repositories.DiaryRepository;
import com.hirim.sulgijang.repositories.DrinkRepository;
import com.hirim.sulgijang.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DrinkRepository drinkRepository;
    private final FileRepository fileRepository;

    public DiaryService(DiaryRepository diaryRepository, DrinkRepository drinkRepository, FileRepository fileRepository) {
        this.diaryRepository = diaryRepository;
        this.drinkRepository = drinkRepository;
        this.fileRepository = fileRepository;
    }

    @Transactional
    public void insertDiary(Diary diary) {
        diaryRepository.insertDiary(diary);

        Optional.ofNullable(diary.getImage())
                    .ifPresent(img -> {
                        img.setDiaryId(diary.getDiaryId());
                        fileRepository.insertFile(img);
                    });
    }

    public void insertDiaryContent(DiaryContent diaryContent) {
        long lastDepth = diaryRepository.selectDiaryContentList(diaryContent.getDiaryId(), 0).stream().count();
        diaryContent.setDepth(lastDepth+1);

        diaryRepository.insertDiaryContent(diaryContent);
    }

    public void updateDiary(Diary diary){ diaryRepository.updateDiary(diary);}

    public void updateDiaryContent(DiaryContent diaryContent){ diaryRepository.updateDiaryContent(diaryContent); }

    @Transactional
    public void deleteDiary(long diaryId) {
        diaryRepository.deleteDiary(diaryId);
        diaryRepository.selectDiaryContentList(diaryId, 0).stream()
                .forEach(i -> {
                    diaryRepository.deleteDiaryContent(i.getDiaryContentId());
                    drinkRepository.deleteDrinkList(i.getDiaryContentId());
                });
    }

    public void deleteDiaryContent(long diaryContentId) {
        diaryRepository.deleteDiaryContent(diaryContentId);
    }

    public List<Diary> selectDiaryList(long diaryId, long partyId, String privateYn) { return diaryRepository.selectDiaryList(diaryId, partyId, privateYn); }

    public List<DiaryContent> selectDiaryContentList(long diaryId, long diaryContentId) {
        return diaryRepository.selectDiaryContentList(diaryId, diaryContentId);
    }

    public Diary selectDiary(long diaryId, String diaryName) { return diaryRepository.selectDiary(diaryId, diaryName); }
}
