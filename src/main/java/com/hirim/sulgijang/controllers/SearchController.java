package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private final PartyService partyService;

    public SearchController(PartyService partyService) {
        this.partyService = partyService;
    }

    @PostMapping("search/party")
    @ResponseBody
    public Party searchParty(Party party) {
        return partyService.selectParty(party);
    }
}
