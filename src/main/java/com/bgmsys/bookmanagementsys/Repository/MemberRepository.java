package com.bgmsys.bookmanagementsys.Repository;

import com.bgmsys.bookmanagementsys.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
