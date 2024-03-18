package com.opsDashboard.full;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class FullRefundController
{
    @GetMapping("/fullRefunds")
    ResponseEntity<?> getFullRefunds()
    {
        return ResponseEntity.ok("full refunds!");
    }
}
