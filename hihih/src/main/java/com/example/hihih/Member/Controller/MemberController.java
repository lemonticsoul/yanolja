package com.example.hihih.Member.Controller;


import com.example.hihih.Member.Dto.MemberResponse;
import com.example.hihih.Member.Dto.MemberSignInDto;
import com.example.hihih.Member.Dto.MemberSignupDto;
import com.example.hihih.Member.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody MemberSignupDto memberSignUpDto) throws Exception{
        System.out.println(1);
        int memid=memberService.createMenber(memberSignUpDto);
        System.out.println("123");
        return ResponseEntity.status(200).body("회원가입 완료"+memid);

    }
    @PostMapping("/sign-in")
    public ResponseEntity<MemberResponse> siginin(@RequestBody MemberSignInDto memberSignInDto){
        return ResponseEntity.ok(MemberResponse.ok(""));
    }
}
