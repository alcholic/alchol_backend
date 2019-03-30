package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Diary;
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
    @ResponseBody
    public CommonResponse saveDiary(@RequestBody Diary diary){
        System.out.println("ddd");
        diaryService.insertDiary(diary);

        return CommonResponse.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "모임별 다이어리 리스트")
    @ResponseBody
    public List<Diary> searchDiaryList(@RequestParam long partyId) {
        return diaryService.selectDiaryList(partyId);
    }
}
