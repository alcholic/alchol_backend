package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.PartyMember;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class PartyController {

    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    //@PostMapping("/group.api")
    @PostMapping("/group")
    public void saveParty(HttpSession session, @RequestBody Party party) {
        User user = (User) session.getAttribute("user");
        partyService.insert(new Party(party.getPartyName(), 0L));
    }

    @PostMapping("/member")
    public void savePartyMember(@RequestBody PartyMember partyMember) {
        partyService.insertPartyMember(partyMember.getPartyId(), partyMember.getUserList());
    }
}
