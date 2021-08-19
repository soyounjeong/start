package com.koreait.day2.repository;

import com.koreait.day2.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DtUserRepository extends JpaRepository<DtUser, Long> {
    // 메소드명만 여기서 만듬
    // 이걸 쓰게 되면 select * from DtUser where userid=?

    Optional<DtUser> findByUserid(String userid);

    // select * from DtUser where userid=? and userpw=?
    // 메소드명은 이름을 동일하게 맞춰주는게 맞음 (순서랑 이름 맞추기)
    // 예외처리로 인한 자유로움
    Optional<DtUser> findByUseridAndUserpw(String userid, String userpw);

    // select * from DtUser where rownum <= 1 order by Id desc;
    DtUser findFirstByHpOrderByIdDesc(String hp);

}
