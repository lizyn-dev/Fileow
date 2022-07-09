package org.lizyn.fileow.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Setter
@Getter
@DiscriminatorValue("File")
public class FileItem extends Item {
    private String filename;

    private Long size;

}