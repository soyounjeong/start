package com.koreait.day2.repository;

import com.koreait.day2.model.entity.AdminUser;
import com.koreait.day2.model.entity.DtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 제네릭에는 <연결시켜줄 클래스,기본이되는 자료형>
@Repository
// jpaRepository에 데이터를 담을 수 있는 클래스
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByUserid(String userid);

}
//DB가 연결되는지 확인하고 싶을때는 단위테스트 사용하는 것이 좋음
// test라는 폴더가 만들어져 있으면 jTest라는 것을 알 수 있음 여기서 단위테스트 사용 가능
