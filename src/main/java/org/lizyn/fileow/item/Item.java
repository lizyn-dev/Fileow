package org.lizyn.fileow.item;

import lombok.Getter;
import lombok.Setter;
import org.lizyn.fileow.common.AbstractEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private ItemType type;

    private String channel;

}
