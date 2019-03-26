package com.hirim.sulgijang.controllers;

import com.google.common.collect.ImmutableList;
import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.PartyMember;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        System.out.println("dd");
        partyService.insertPartyMember(partyMember.getPartyId(), partyMember.getUserList());
    }
}
