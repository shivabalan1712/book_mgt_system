package com.bgmsys.bookmanagementsys.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {

    private long memberId;
    private String username;
    private String password;
    private String email;
}
