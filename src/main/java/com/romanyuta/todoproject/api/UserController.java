package com.romanyuta.todoproject.api;

import com.romanyuta.todoproject.exception.ApiRequestException;
import com.romanyuta.todoproject.model.Users;
import com.romanyuta.todoproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/user")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями ")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/ex")
    public List<Users> getAllUsers(){
        throw new ApiRequestException("cannot get all users");
    }


    @Operation(
            summary = "Список всех пользователей",
            description = "Позволяет посмотреть список всех пользователей"
    )
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Users> getUsers() {

        List<Users> result;
        result = userService.getUsers();
        if (result == null){
            throw new ApiRequestException("cannot get all users");
        }else {
            return result;
        }

    }

    @Operation(
            summary = "Регистрация пользователя",
            description = "Позволяет зарегестрировать пользователя"
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus registerNewUser(@RequestBody Users user){
        if (user == null){
            throw new ApiRequestException("cannot add user");
        }else {
            userService.addNewUser(user);
            return HttpStatus.OK;
        }
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Позволяет удалить пользователя по id"
    )
    @DeleteMapping(path = "{userId}")
    public HttpStatus deleteUser(@PathVariable("userId") @Parameter(description = "Идентификатор") Long userId){
        if(userId == null){
            throw new ApiRequestException("cannot delete user");
        }else{
            userService.deleteUser(userId);
            return HttpStatus.OK;
        }
    }

    @Operation(
            summary = "Изменение пользователей",
            description = "Позволяет обновить информацию о пользователе"
    )
    @PutMapping(path = "{userId}", produces = APPLICATION_JSON_VALUE)
    public HttpStatus updateUser(
            @PathVariable("userId") @Parameter(description = "Идентификатор") Long userId,
            @RequestParam(required = false) @Parameter(description = "Новое имя пользователя") String name,
            @RequestParam(required = false) @Parameter(description = "Новая роль пользователя") String role
    ){
        userService.updateUser(userId,name,role);
        return HttpStatus.OK;
    }


}
