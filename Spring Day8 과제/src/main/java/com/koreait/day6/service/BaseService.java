package com.koreait.day6.service;

import com.koreait.day6.ifs.CrudInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
// api하고 동일
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;
    // 이쪽이 기본이 되서 api하고 연결할 수 있도록 선언
    // 따로 빼서 선언해 상속을 받아 중복되는 내용을 동시에 같이 사용할 수 있는.
}
