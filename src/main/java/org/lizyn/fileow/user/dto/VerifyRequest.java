package org.lizyn.fileow.user.dto;

import lombok.Data;

@Data
public class VerifyRequest {
    private String username;

    private String password;

}
