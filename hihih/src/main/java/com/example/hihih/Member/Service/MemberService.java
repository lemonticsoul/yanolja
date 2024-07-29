package com.example.hihih.Member.Service;


import com.example.hihih.Member.Exception.DuplicationMemberNicknameException;
import com.example.hihih.Member.Exception.DuplicationMemberUsernameException;
import com.example.hihih.Member.Repository.MemberRepository;
import com.example.hihih.Member.domain.Member;
import com.example.hihih.Member.Dto.MemberSignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;

    public int createMenber(MemberSignupDto memberSignUpDto) {


        if (memberRepository.existsByUsername(memberSignUpDto.getUsername()))
            throw new DuplicationMemberUsernameException(memberSignUpDto.getUsername());
        if (memberRepository.existsByNickname(memberSignUpDto.getNickname()))
            throw new DuplicationMemberNicknameException(memberSignUpDto.getNickname());

        Member member =memberSignUpDto.toEntity();
        member.passwordEncode(encoder);

        memberRepository.save(member);

        return member.getId();

    }
}
