package me.djamelkorei.projecttaskmanagement.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class JWToken implements Serializable {

    private String token;

}
