package com.example.controllers;

import com.example.entities.UserEntity;
import com.example.services.UserService;
import com.example.utils.URLParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.function.Supplier;

import static com.example.controllers.UsersController.Result.run;

@Controller
@RequestMapping(value = URLParams.USERS)
public class UsersController {

    @Autowired
    private UserService userService;

    public static final class Result<T> {
        public final T entity;
        public final String msg;
        public final Boolean success;

        public Result(T entity, String msg, Boolean success) {
            this.entity = entity;
            this.msg = msg;
            this.success = success;
        }

        public static <T> Result<T> run(final Supplier<T> function) {
            return new Result<>(function.get(), null, true);
        }
    }

    @RequestMapping(value = URLParams.GET_ALL, method = RequestMethod.GET)
    @ResponseBody
    public final Result<List<UserEntity>> getAll() {
        return run(() -> userService.getAll());
    }

    @RequestMapping(value = URLParams.UPDATE, method = RequestMethod.POST)
    @ResponseBody
    public final Result<Void> update(@RequestBody final UserEntity entity) {
        return run(() -> {
            userService.update(entity);
            return null;
        });
    }

    @RequestMapping(value = URLParams.CREATE, method = RequestMethod.POST)
    @ResponseBody
    public Result<UserEntity> create(@RequestBody final UserEntity entity) {
        return run(() -> userService.create(entity));
    }

    @RequestMapping(value = URLParams.GET, method = RequestMethod.GET)
    @ResponseBody
    public final Result<UserEntity> getByEmail(@RequestParam(value = "email") String email) {
        return run(() -> userService.get(email));
    }

    @RequestMapping(value = URLParams.DELETE, method = RequestMethod.GET)
    @ResponseBody
    public Result<Void> delete(@RequestParam(value = "id") Integer id) {
        return run(() -> {
            userService.delete(id);
            return null;
        });
    }


    @ControllerAdvice
    static class RestControllerAdvice {
        @ExceptionHandler(Exception.class)
        @ResponseBody
        protected final ResponseEntity<?> handle(final Exception exception) {
            HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            final ResponseStatus responseStatus =
                    AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class);
            if (responseStatus != null)
                statusCode = responseStatus.value();

            return new ResponseEntity<>(
                    new Result<>(null, exception.getMessage(), false), statusCode);
        }
    }
}