package com.hirim.sulgijang.services;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.repositories.DiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void insertDiary(Diary diary) { diaryRepository.insertDiary(diary); }

    public void insertDiaryContent(DiaryContent diaryContent) {
        long lastDepth = diaryRepository.selectDiaryContentList(diaryContent.getDiaryId()).stream().count();
        diaryContent.setDepth(lastDepth+1);

        diaryRepository.insertDiaryContent(diaryContent);
    }

    @Transactional
    public void deleteDiary(long diaryId) {
        diaryRepository.deleteDiary(diaryId);
        diaryRepository.selectDiaryContentList(diaryId).stream()
                .peek(i -> diaryRepository.deleteDiaryContent(i.getDiaryContentId()))
                .collect(Collectors.toList());
    }

    public List<Diary> selectDiaryList(long diaryId, long partyId) {return diaryRepository.selectDiaryList(diaryId, partyId); }

    public Diary selectDiary(long diaryId, String diaryName) { return diaryRepository.selectDiary(diaryId, diaryName); }
}
