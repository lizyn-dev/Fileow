package org.zlycerqan.fileow.item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zlycerqan.fileow.common.Result;
import org.zlycerqan.fileow.item.dto.AddTextItemRequest;
import org.zlycerqan.fileow.item.dto.GetDownloadLinkRequest;
import org.zlycerqan.fileow.item.dto.GetListRequest;
import org.zlycerqan.fileow.item.dto.RemoveItemRequest;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/getList")
    Result getList(@RequestBody GetListRequest request) {
        return Result.ok(itemService.getListByChannel(request.getChannel()));
    }

    @PostMapping("/addTextItem")
    Result addTextItem(@RequestBody AddTextItemRequest request) {
        return Result.ok(itemService.addText(request.getChannel(), request.getTitle(), request.getText()).getId());
    }

    @PostMapping("/addFileItem")
    Result addFileItem(@RequestParam("channel") String channel, @RequestParam("file") MultipartFile file) {
        Item item = itemService.addFile(channel, file);
        if (item == null) {
            return new Result().setCode(500).setMessage("Upload Error");
        } else {
            return Result.ok(item.getId());
        }
    }

    @PostMapping("/getDownloadLink")
    Result getDownloadLink(@RequestBody GetDownloadLinkRequest request) {
        String url = itemService.getDownloadLink(request.getId());
        if (url == null) {
            return new Result().setCode(500).setMessage("Download Error");
        } else {
            return Result.ok(url);
        }
    }

    @PostMapping("/removeItem")
    Result removeItem(@RequestBody RemoveItemRequest request) {
        if (itemService.removeItem(request.getId())) {
            return Result.ok(true);
        } else {
            return new Result().setCode(500).setMessage("Remove Error").setData(false);
        }
    }
}
