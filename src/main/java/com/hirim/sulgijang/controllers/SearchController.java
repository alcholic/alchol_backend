package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.DiaryService;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {

    private final PartyService partyService;
    private final DiaryService diaryService;

    public SearchController(PartyService partyService, DiaryService diaryService) {
        this.partyService = partyService;
        this.diaryService = diaryService;
    }

    @GetMapping("/party")
    public CommonResponse searchParty(@RequestParam(required = false) String partyName) {
        return CommonResponse.successObject(partyService.selectParty(partyName));
    }
}
