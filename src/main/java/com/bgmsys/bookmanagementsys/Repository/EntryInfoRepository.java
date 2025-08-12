package com.bgmsys.bookmanagementsys.Repository;

import com.bgmsys.bookmanagementsys.Model.EntryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntryInfoRepository extends JpaRepository<EntryInfo, Long> {
    Optional<EntryInfo> findByBook_BookIdAndMember_MemberIdAndReturndateIsNull(Long bookId, Long memberId);
}
