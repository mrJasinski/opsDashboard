package com.opsDashboard.user;

import com.opsDashboard.security.JwtService;
import com.opsDashboard.user.dto.Dashboard;
import com.opsDashboard.user.dto.UserDTO;
import com.opsDashboard.user.dto.UserLoggedDTO;
import com.opsDashboard.utils.Country;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
class UserController
{
    private final UserRepository userRepo;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final DashboardService dashboardService;

    UserController(UserRepository userRepo
            , UserService userService
            , JwtService jwtService
            , AuthenticationManager authManager
            , DashboardService dashboardService)
    {
        this.userRepo = userRepo;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    ResponseEntity<?> getDashboard(HttpServletRequest request)
    {
        return ResponseEntity.ok(this.dashboardService.getDashboardByUserId(this.jwtService.getUserIdFromToken(request)));
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

        var timeLeft = this.userService.startWorkday(user.getEmail(), user.getLocation());

        var logged = new UserLoggedDTO(user.getEmail(), this.jwtService.generateToken(user.getEmail()), timeLeft);

        return ResponseEntity.ok(logged);
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
//        TODO test
        System.out.println("stop");

        this.userService.stopWorkday(this.jwtService.getUserEmail(request));

        return ResponseEntity.ok("Workday stopped!");
    }

    @GetMapping(value = "/xxx", params = {"country", "stockId"})
    ResponseEntity<?> getUserFromDb(@RequestParam(name = "country") Country country, @RequestParam(name = "stockId") String stockId)
    {
        var user = this.userService.getUserWithPreviouslyAssignedStockId(country, stockId);

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/lowest", params = {"country", "assignDate"})
    ResponseEntity<?> getLowest(@RequestParam(name = "country") Country country, @RequestParam(name = "assignDate") LocalDate assignDate)
    {
        var user = this.userService.getUserWithLowestDailyAssignedClaimsByCountryAndDate(country, assignDate);

        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/claimsByUser", params = {"assignDate"})
    ResponseEntity<?> getClaimsByUser(@RequestParam(name = "assignDate") LocalDate assignDate)
    {
        return ResponseEntity.ok(this.userRepo.findLowest(assignDate));
    }
}
