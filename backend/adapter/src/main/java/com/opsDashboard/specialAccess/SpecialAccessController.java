package com.opsDashboard.specialAccess;

import com.opsDashboard.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class SpecialAccessController
{
    private final SpecialAccessService sAService;
    private final JwtService jwtService;

    SpecialAccessController(final SpecialAccessService sAService, final JwtService jwtService)
    {
        this.sAService = sAService;
        this.jwtService = jwtService;
    }

    @GetMapping("/pendingSpecialAccess")
    ResponseEntity<?> getPendingSpecialAccess(HttpServletRequest request)
    {
        var specials = this.sAService.getPendingSAByUserId(this.jwtService.getUserIdFromToken(request));

        return ResponseEntity.ok(specials);
    }

    @GetMapping(value = "/SAStatusChange", params = {"isApproved", "stockId"} )
    ResponseEntity<?> changeSAStatus(@RequestParam(name = "isApproved") boolean isApproved, @RequestParam(name = "stockId") String stockId, HttpServletRequest request)
    {
        this.sAService.changeSAStatusByStockId(stockId, isApproved);

        var specials = this.sAService.getPendingSAByUserId(this.jwtService.getUserIdFromToken(request));

        return ResponseEntity.ok(specials);
    }
}
