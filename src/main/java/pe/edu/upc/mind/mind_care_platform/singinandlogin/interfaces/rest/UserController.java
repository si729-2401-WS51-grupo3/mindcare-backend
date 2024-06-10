package pe.edu.upc.mind.mind_care_platform.singinandlogin.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.aplication.internal.commandservices.UserService;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.aplication.internal.queryservices.QServices;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands.CreateUserCommand;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.commands.UserRegisterCommand;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.domain.model.entities.User;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.interfaces.rest.resources.RUser;
import pe.edu.upc.mind.mind_care_platform.singinandlogin.interfaces.rest.resources.UserResourceAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private QServices userQueryService;

    @Autowired
    private UserResourceAssembler assembler;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            return ResponseEntity.ok("Usuario autenticado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PutMapping("/{id}/settings")
    public ResponseEntity<RUser> updateUserSettings(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUserSettings(id, userDetails);
        return ResponseEntity.ok(assembler.toResource(updatedUser));
    }

    @GetMapping
    public List<RUser> getAllUsers() {
        return userQueryService.findAllUsers()
                .stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RUser> getUserById(@PathVariable Long id) {
        Optional<User> user = userQueryService.findUserById(id);
        return user.map(value -> ResponseEntity.ok(assembler.toResource(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<RUser> registerUser(@RequestBody UserRegisterCommand command) {
        User user = userService.registerUser(command);
        return ResponseEntity.ok(assembler.toResource(user));
    }

    @PostMapping
    public RUser createUser(@RequestBody CreateUserCommand command) {
        User user = userService.createUser(command);
        return assembler.toResource(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RUser> updateUser(@PathVariable Long id, @RequestBody UpdateUserCommand command) {
        User user = userService.updateUser(id, command);
        if (user != null) {
            return ResponseEntity.ok(assembler.toResource(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RUser> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(assembler.toResource(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}