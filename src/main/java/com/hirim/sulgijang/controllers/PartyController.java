package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.utils.UserSessionUtils;
import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.PartyMember;
import com.hirim.sulgijang.models.User;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.PartyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/party")
public class PartyController {
    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    //@PostMapping("/party.api")
    @PostMapping("/save")
    @ApiOperation(value="모임 추가", notes="필수 : 모임명, 모임을 생성하는 유저ID")
    public CommonResponse saveParty(HttpSession session, @RequestBody Party party) {
        User user = (User) session.getAttribute("user");
        partyService.insert(new Party(party.getPartyName(), 1L)); // user.getUserId()

        return CommonResponse.success();
    }

    @PostMapping("/save/member")
    @ApiOperation(value="모임 인원 추가", notes="필수 : 모임ID, 추가 될 유저ID")
    public CommonResponse savePartyMember(@RequestBody PartyMember partyMember) {
        partyService.insertPartyMember(partyMember.getPartyId(), partyMember.getUserList());

        return CommonResponse.success();
    }

    @GetMapping("/list")
    @ApiOperation(value="유저의 모임 리스트")
    public List<Party> showParty(HttpServletRequest request) {
        User user = UserSessionUtils.UserBySession(request);
        return partyService.selectPartyByUser(1L);
    }
}
