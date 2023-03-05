package org.zlycerqan.fileow.user;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.zlycerqan.fileow.common.AbstractEntity;

@Entity
@Getter
@Setter
public class User extends AbstractEntity {
    private String username;

    private String password;

}
