package org.zlycerqan.fileow.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.zlycerqan.fileow.common.AbstractEntity;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private ItemType type;

    private String channel;

}
