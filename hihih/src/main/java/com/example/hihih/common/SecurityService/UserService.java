package com.example.hihih.common.SecurityService;


import com.example.hihih.Member.Exception.NotFoundMemberByUsernameException;
import com.example.hihih.Member.Repository.MemberRepository;
import com.example.hihih.Member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundMemberByUsernameException(username));

        return MemberDetails.builder()
                .member(member)
                .build();
    }




}
