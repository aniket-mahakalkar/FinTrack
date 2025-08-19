package com.aniket.FinTrack.dto;

import com.microsoft.schemas.office.office.STInsetMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthDTO {

    private String email;
    private String password;
    private String token;
}
