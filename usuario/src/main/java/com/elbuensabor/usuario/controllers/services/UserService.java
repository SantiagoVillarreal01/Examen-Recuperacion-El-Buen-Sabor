package com.elbuensabor.usuario.controllers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.elbuensabor.usuario.controllers.data.entities.UserDTO;
import com.elbuensabor.usuario.controllers.rabbit.RabbitMessage;
import com.elbuensabor.usuario.logic.usercases.DeleteUserUseCase;
import com.elbuensabor.usuario.logic.usercases.EditUserUseCase;
import com.elbuensabor.usuario.logic.usercases.GetAllUsersUseCase;
import com.elbuensabor.usuario.logic.usercases.GetUserByIdUseCase;
import com.elbuensabor.usuario.logic.usercases.RegisterUserUseCase;

@RestController
@RequestMapping("/api/usuario")
public class UserService {

    @Autowired
    private RegisterUserUseCase registerUser;

    @Autowired
    private GetAllUsersUseCase getAllUsers;

    @Autowired
    private GetUserByIdUseCase getUserById;

    @Autowired
    private EditUserUseCase editUser;

    @Autowired
    private DeleteUserUseCase deleteUser;

    @Autowired
    private RabbitMessage rabbitMessage;

    @GetMapping("/register")
        public ResponseEntity<?> register(
                @RequestParam("name") String name,
                @RequestParam("lastName") String lastName,
                @RequestParam("email") String email,
                @RequestParam("password") String password) {

        rabbitMessage.sendLog("/api/usuario/register");

        // Pasamos los parámetros directamente al Use Case
        return registerUser.register(name, lastName, email, password).fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
        }

    @PutMapping("/update/{userId}?")
    public ResponseEntity<?> update(@PathVariable("userId") String userId,
            @RequestParam String name,
            @RequestParam String lastName,
            @RequestParam String email) {
        rabbitMessage.sendLog("/api/usuario/update/" + userId);
        return editUser.update(userId, name, lastName, email).fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        rabbitMessage.sendLog("/api/usuario/all");
        return getAllUsers.getAll().fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.internalServerError().body(ex.getMessage()));
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<?> findById(@PathVariable("userId") String userId) {
        rabbitMessage.sendLog("/api/usuario/search/" + userId);
        return getUserById.get(userId).fold(
                ResponseEntity::ok,
                ex -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") String userId) {
        rabbitMessage.sendLog("/api/usuario/delete/" + userId);
        return deleteUser.delete(userId).fold(
                val -> ResponseEntity.ok("Usuario eliminado correctamente"),
                ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
