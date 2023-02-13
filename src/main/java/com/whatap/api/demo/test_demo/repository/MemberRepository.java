package com.whatap.api.demo.test_demo.repository;

import com.whatap.api.demo.test_demo.domain.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> {

}
