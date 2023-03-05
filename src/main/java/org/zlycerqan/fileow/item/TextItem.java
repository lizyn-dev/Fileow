package org.zlycerqan.fileow.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@DiscriminatorValue("Text")
public class TextItem extends Item {
    private String title;

    @Lob
    private String text;

}
