package org.lizyn.fileow.user;

import lombok.RequiredArgsConstructor;
import org.lizyn.fileow.common.Result;
import org.lizyn.fileow.user.dto.AddUserRequest;
import org.lizyn.fileow.user.dto.VerifyRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserRequest request) {
        return Result.ok(userService.addUser(request.getUsername(), request.getPassword()).getId());
    }

    @PostMapping("/verify")
    public Result verify(@RequestBody VerifyRequest request) {
        return Result.ok(userService.verify(request.getUsername(), request.getPassword()));
    }

}
