package org.zlycerqan.fileow.item.dto;

import lombok.Data;

@Data
public class AddTextItemRequest {
    private String channel;

    private String title;

    private String text;

}
