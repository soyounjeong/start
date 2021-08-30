package com.koreait.day6.repository;

import com.koreait.day6.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DtUserRepository extends JpaRepository<DtUser, Long> {
    // select * from users where userid=?
    Optional<DtUser> findByUserid(String userid);

    // select * from users where userid=? and userpw=?
    Optional<DtUser> findByUseridAndUserpw(String userid, String userpw);

    // select * from users where rownum <= 1 order by id desc
    DtUser findFirstByHpOrderByIdDesc(String hp);
}
