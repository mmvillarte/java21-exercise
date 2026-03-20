package org.exercise.user.api.controller;

import org.exercise.user.api.model.UserResult;
import org.exercise.user.application.query.model.UserDTO;
import org.exercise.user.application.query.service.UserQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserQueryController {
    private static final Logger log = LoggerFactory.getLogger(UserQueryController.class);

    private final UserQueryService userQueryService;

    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping
    public ResponseEntity<UserResult<UserDTO>> findById(@RequestParam UUID id) {
        log.info("Finding user with uuid: {}", id);
        UserDTO userDTO = userQueryService.findById(id);

        if(userDTO != null) {
            return ResponseEntity.ok(new UserResult<>(200, "User Found for provided id", userDTO));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new UserResult<>(404, "User Not Found for provided id", null));
        }
    }
}
