package com.whatap.api.demo.test_demo.service;


import com.whatap.api.demo.test_demo.domain.Members;
import com.whatap.api.demo.test_demo.repository.MemberRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Members register(Members members) {
        memberRepository.save(members);
        return members;
    }

    public List<Members> getMembers() {
        return memberRepository.findAll();
    }

    public Members getMember(Long idx) {
        return memberRepository.findById(idx).orElseThrow();
    }

    @Transactional
    public Members modifier(Long idx, Members modifierMembers) {
        Members members = memberRepository.findById(idx).orElseThrow();
        members.updateMembers(modifierMembers.getUserName(), modifierMembers.getPassword());
        memberRepository.save(members);
        return members;
    }

    @Transactional
    public Long deleter(Long idx) {
        memberRepository.deleteById(idx);
        return idx;
    }

}
