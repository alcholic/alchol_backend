package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.utils.UserSessionUtils;
import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DiaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "다이어리 저장")
    public CommonResponse saveDiary(HttpServletRequest request, @RequestBody Diary diary){
        User user = UserSessionUtils.UserBySession(request);
        diary.setCreatedBy(0);
        diaryService.insertDiary(diary);
        return CommonResponse.success();
    }

    @PostMapping("/content/save")
    @ApiOperation(value = "다이어리 내용 저장")
    public CommonResponse saveDiaryContent(HttpServletRequest request, @RequestBody DiaryContent diaryContent) {
        User user = UserSessionUtils.UserBySession(request);

        diaryContent.setCreatedBy(0);
        diaryService.insertDiaryContent(diaryContent);
        return CommonResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(value="다이어리 수정")
    public CommonResponse updateDiary(HttpServletRequest request, @RequestBody Diary diary) {
        User user = UserSessionUtils.UserBySession(request);

        diary.setUpdatedBy(0);
        diaryService.updateDiary(diary);
        return CommonResponse.success();
    }

    @PostMapping("/content/update")
    @ApiOperation(value="다이어리 컨텐츠 수정")
    public CommonResponse updateDiaryContent(HttpServletRequest request, @RequestBody DiaryContent diaryContent) {
        User user = UserSessionUtils.UserBySession(request);

        diaryContent.setUpdatedBy(0);
        diaryService.updateDiaryContent(diaryContent);
        return CommonResponse.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "모임별 다이어리 리스트")
    public CommonResponse searchDiaryList(@RequestParam(required = false, defaultValue = "0") long partyId, @RequestParam(required = false, defaultValue = "0") long diaryId) {
        return CommonResponse.successObject(diaryService.selectDiaryList(partyId, diaryId));
    }

    @GetMapping("/content/list")
    @ApiOperation(value="다이어리 내용 리스트")
    public CommonResponse searchDiaryList(@RequestParam long diaryId) {
        return CommonResponse.successObject(diaryService.selectDiaryContentList(diaryId));
    }
}
