package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.DiaryContent;
import com.hirim.sulgijang.models.Image;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DiaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/")
    @ApiOperation(value = "모임별 다이어리 리스트", notes = "partyId, diaryId, privateYn 모두 required false")
    public CommonResponse searchDiaryList(@RequestParam(required = false, defaultValue = "0") long partyId,
                                          @RequestParam(required = false) String privateYn) {
        return CommonResponse.successObject(diaryService.selectDiaryListByParty(partyId, privateYn));
    }

    @PostMapping("/")
    @ApiOperation(value = "다이어리 저장")
    public CommonResponse saveDiary(HttpServletRequest request, @RequestBody Diary diary){
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        diary.setCreatedBy(0/*userInfo.getUserId()*/);
        diaryService.insertDiary(diary);
        return CommonResponse.success();
    }

    @PutMapping("/")
    @ApiOperation(value="다이어리 수정")
    public CommonResponse updateDiary(HttpServletRequest request, @RequestBody Diary diary) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        diary.setUpdatedBy(0/*userInfo.getUserId()*/);
        diaryService.updateDiary(diary);
        return CommonResponse.success();
    }

    @DeleteMapping("/{diaryId}")
    @ApiOperation(value="다이어리삭제")
    public CommonResponse deleteDiary(@PathVariable long diaryId) {
        diaryService.deleteDiary(diaryId);
        return CommonResponse.success();
    }

    @PostMapping("/contents")
    @ApiOperation(value = "다이어리 내용 저장")
    public CommonResponse saveDiaryContent(HttpServletRequest request, @RequestBody DiaryContent diaryContent) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        diaryContent.setCreatedBy(0/*userInfo.getUserId()*/);
        diaryService.insertDiaryContent(diaryContent);
        return CommonResponse.success();
    }

    @PutMapping("/contents")
    @ApiOperation(value="술자리 탭 수정")
    public CommonResponse updateDiaryContent(HttpServletRequest request, @RequestBody DiaryContent diaryContent) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        diaryContent.setUpdatedBy(0/*userInfo.getUserId()*/);
        diaryService.updateDiaryContent(diaryContent);
        return CommonResponse.success();
    }

    @DeleteMapping("/contents/{diaryContentId}")
    @ApiOperation(value = "술자리 탭 삭제")
    public CommonResponse deleteDiaryContent(@PathVariable long diaryContentId) {
        diaryService.deleteDiaryContent(diaryContentId);
        return CommonResponse.success();
    }


    @GetMapping("/contents")
    @ApiOperation(value="술자리 탭 리스트")
    public CommonResponse searchDiaryContentList(@RequestParam(required = false, defaultValue = "0") long diaryId, @RequestParam(required = false, defaultValue = "0") long diaryContentId) {
        return CommonResponse.successObject(diaryService.selectDiaryContentList(diaryId, diaryContentId));
    }
}
