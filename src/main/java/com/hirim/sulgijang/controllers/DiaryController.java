package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DiaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "다이어리 저장")
    public CommonResponse saveDiary(@RequestBody Diary diary){
        diaryService.insertDiary(diary);
        return CommonResponse.success();
    }

    @PostMapping("/content/save")
    @ApiOperation(value = "다이어리 내용 저장")
    public CommonResponse saveDiaryContent(@RequestBody DiaryContent diaryContent) {
        diaryService.insertDiaryContent(diaryContent);
        return CommonResponse.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "모임별 다이어리 리스트")
    public List<Diary> searchDiaryList(@RequestParam(required = false, defaultValue = "0") long partyId, @RequestParam(required = false, defaultValue = "0") long diaryId) {
        return diaryService.selectDiaryList(partyId, diaryId);
    }
}
