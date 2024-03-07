package com.opsDashboard.user;

import com.opsDashboard.security.JwtService;
import com.opsDashboard.user.dto.UserDTO;
import com.opsDashboard.utils.Country;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
class UserController
{
    private final UserRepository userRepo;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    UserController(final UserRepository userRepo, final UserService userService, final JwtService jwtService, final AuthenticationManager authManager)
    {
        this.userRepo = userRepo;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    @GetMapping("/dashboard")
    ResponseEntity<?> getDashboard()
    {
        var response = new Dashboard("Response from spring!");

//        var fullRefunds =

        var sa = new Dashboard("Special Accesses: Ongoing : " + "xx" + " // Pending: " + "yy");

        return ResponseEntity.ok(sa);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody User user)
    {
        return ResponseEntity.ok(this.userRepo.save(user));
    }

    @PostMapping("/authenticate")
    ResponseEntity<?> authenticateUser(@RequestBody UserDTO user)
    {
        var userDetails = this.authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (!userDetails.isAuthenticated())
            throw new UsernameNotFoundException("Invalid user credentials!");

        this.userService.startWorkday(user.getEmail(), user.getLocation());

        return ResponseEntity.ok(this.jwtService.generateToken(user.getEmail()));
    }

    @GetMapping("/users")
    ResponseEntity<?> getUsers()
    {
        return ResponseEntity.ok(this.userRepo.findAll());
    }

    @GetMapping("/workdays")
    ResponseEntity<?> getWorkdays(HttpServletRequest request)
    {
        return ResponseEntity.ok(this.userService.getUserByEmail(this.jwtService.getUserEmail(request)).getWorkdays());
    }

    @GetMapping("/stopWorkday")
    ResponseEntity<?> stopWorkday(HttpServletRequest request)
    {
        this.userService.stopWorkday(this.jwtService.getUserEmail(request));

        return ResponseEntity.ok("Workday stopped!");
    }

    @GetMapping(value = "/xxx", params = {"country", "stockId"})
    ResponseEntity<?> getUserFromDb(@RequestParam(name = "country") Country country, @RequestParam(name = "stockId") String stockId)
    {
        var user = this.userService.getUserWithPreviouslyAssignedStockId(country, stockId);

        return ResponseEntity.ok(user);
    }
}
