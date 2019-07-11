package com.fancq.datasource.service.impl;

import com.fancq.datasource.annotation.Master;
import com.fancq.datasource.entity.Member;
import com.fancq.datasource.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {


    @Override
    public int insert(Member member) {
        return 0;
    }

    @Master
    @Override
    public int save(Member member) {
        return 0;
    }

    @Override
    public List<Member> selectAll() {
        return null;
    }
}
