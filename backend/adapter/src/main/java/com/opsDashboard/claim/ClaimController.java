package com.opsDashboard.claim;

import com.opsDashboard.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class ClaimController
{
    private final ClaimService claimService;
    private final JwtService jwtService;

    ClaimController(final ClaimService claimService, final JwtService jwtService)
    {
        this.claimService = claimService;
        this.jwtService = jwtService;
    }

    @GetMapping("/pendingClaims")
    ResponseEntity<?> getPendingClaims()
    {
        var result = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            result.add("claim" + i);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/claims")
    ResponseEntity<?> getClaims(HttpServletRequest request)
    {
//        var claims = this.claimService.getClaimByUserIdAsDto(this.jwtService.getUserIdFromToken(request));
        var claims = this.claimService.getAllClaims();

        return ResponseEntity.ok(claims);
    }
}
