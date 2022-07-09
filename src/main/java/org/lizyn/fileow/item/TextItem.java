package org.lizyn.fileow.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@Getter
@Setter
@DiscriminatorValue("Text")
public class TextItem extends Item {
    private String title;

    @Lob
    private String text;

}
