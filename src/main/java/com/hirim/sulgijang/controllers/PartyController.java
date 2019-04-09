package com.hirim.sulgijang.controllers;

import com.hirim.sulgijang.common.UserSessionHelper;
import com.hirim.sulgijang.models.Party;
import com.hirim.sulgijang.models.PartyMember;
import com.hirim.sulgijang.models.UserInfo;
import com.hirim.sulgijang.models.response.CommonResponse;
import com.hirim.sulgijang.services.PartyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public CommonResponse saveParty(HttpServletRequest request, @RequestBody Party party) {


        partyService.insertParty(new Party(party.getPartyName(), 0 /*userInfo.getUserId()*/)); // user.getUserId()

        return CommonResponse.success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "모임 수정")
    public CommonResponse updateParty(HttpServletRequest request, @RequestBody Party party) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        party.setUpdatedBy(0/*userInfo.getUserId()*/);
        partyService.updateParty(party);

        return CommonResponse.success();
    }

    @DeleteMapping("/{partyId}")
    @ApiOperation(value = "모임 삭제")
    public CommonResponse deleteParty(@PathVariable long partyId) {
        partyService.deleteParty(partyId);

        return CommonResponse.success();
    }

    @PostMapping("/save/member")
    @ApiOperation(value="모임 인원 추가", notes="필수 : 모임ID, 추가 될 유저ID")
    public CommonResponse savePartyMember(HttpServletRequest request, @RequestBody PartyMember partyMember) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        partyMember.setCreatedBy(0 /*userInfo.getUserId()*/);
        partyService.insertPartyMember(partyMember.getPartyId(), partyMember.getUserList());

        return CommonResponse.success();
    }

    @GetMapping("/list")
    @ApiOperation(value="유저의 모임 리스트")
    public CommonResponse searchPartyList(HttpServletRequest request) {
        UserInfo userInfo = UserSessionHelper.getUserInfo(request);

        return CommonResponse.successObject(partyService.selectPartyByUser(0 /*userInfo.getUserId()*/));
    }

    @GetMapping("/member/list")
    @ApiOperation(value = "모임별 유저 리스트")
    public CommonResponse searchUserList(@RequestParam long partyId) {
        return CommonResponse.successObject(partyService.selectUserListByParty(partyId));
    }
}
