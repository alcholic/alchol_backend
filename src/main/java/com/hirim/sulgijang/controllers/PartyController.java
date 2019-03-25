package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void saveGroup(HttpSession session, String partyName) {
        User user = (User) session.getAttribute("user");
        partyService.insert(new Party(partyName, 0L));
    }
}
