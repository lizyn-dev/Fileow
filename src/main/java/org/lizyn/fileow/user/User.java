package org.lizyn.fileow.user;

import lombok.Getter;
import lombok.Setter;
import org.lizyn.fileow.common.AbstractEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class User extends AbstractEntity {
    private String username;

    private String password;

}
