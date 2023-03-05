package org.zlycerqan.fileow.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("File")
public class FileItem extends Item {
    private String filename;

    private Long size;

}