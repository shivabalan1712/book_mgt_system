package com.bgmsys.bookmanagementsys.Mapper;

import com.bgmsys.bookmanagementsys.DTO.MemberRequestDTO;
import com.bgmsys.bookmanagementsys.DTO.MemberResponseDTO;
import com.bgmsys.bookmanagementsys.Model.Member;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MemberMapperDTO {

    public static Member toEntity(MemberRequestDTO memberRequestDTO) {
        Member member = new Member();
        member.setUsername(memberRequestDTO.getUsername());
        member.setPassword(memberRequestDTO.getPassword());
        member.setEmail(memberRequestDTO.getEmail());
        return member;
    }

    public static MemberResponseDTO toDTO(Member member) {
        MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
        return new MemberResponseDTO(
                member.getMemberId(),
                member.getUsername(),
                member.getPassword(),
                member.getEmail()
        );
    }
}

