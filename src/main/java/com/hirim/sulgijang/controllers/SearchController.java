package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Diary;
import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.services.DiaryService;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.web.bind.annotation.*;

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
    public Party searchParty(@RequestParam(required = false) String partyName, @RequestParam(required = false, defaultValue= "0") long partyId) {
        return partyService.selectParty(partyId, partyName);
    }

    @GetMapping("/diary")
    public Diary searchDiary(@RequestParam(required = false) String diaryName, @RequestParam(required = false, defaultValue= "0") long diaryId) {
        return diaryService.selectDiary(diaryId, diaryName);
    }
}
