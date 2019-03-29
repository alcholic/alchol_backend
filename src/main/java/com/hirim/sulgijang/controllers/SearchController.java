package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
public class SearchController {

    private final PartyService partyService;

    public SearchController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping("/party")
    @ResponseBody
    public Party searchParty(@RequestParam(required = false) String partyName, @RequestParam(required = false, defaultValue= "0") long partyId) {
        return partyService.selectParty(partyId, partyName);
    }
}
