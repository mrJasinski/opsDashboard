package com.opsDashboard.user;

import com.opsDashboard.security.JwtResponse;
import com.opsDashboard.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class UserController
{
    private final UserRepository userRepo;
    private final JwtService jwtService;

    UserController(final UserRepository userRepo, JwtService jwtService)
    {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @GetMapping("/dashboard")
    ResponseEntity<?> getDashboard()
    {
        var response = new Dashboard("Response from spring!");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user)
    {
        return ResponseEntity.ok(this.userRepo.save(user));
    }

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticateUser(@RequestBody User user)
    {
        var users = this.userRepo.findAll();

        String response = null;

        for (User u : users)
        {
            if ((u.getEmail()).equals(user.getEmail()) && (u.getPassword()).equals(user.getPassword()))
            {
                response = this.jwtService.generateToken(user.getEmail());
                break;
            }
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    ResponseEntity<?> getUsers()
    {
        return ResponseEntity.ok(this.userRepo.findAll());
    }
}
