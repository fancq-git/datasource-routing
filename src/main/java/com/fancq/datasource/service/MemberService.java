package com.fancq.datasource.service;

import com.fancq.datasource.entity.Member;

import java.util.List;

public interface MemberService {
    int insert(Member member);

    int save(Member member);

    List<Member> selectAll();

}
