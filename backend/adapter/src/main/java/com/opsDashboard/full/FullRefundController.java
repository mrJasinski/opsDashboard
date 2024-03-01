package com.opsDashboard.full;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class FullRefundController
{
    @GetMapping("/fullRefunds")
    ResponseEntity<?> getFullRefunds()
    {
        var fr1 = new FullRefund("SK", "EN12345", "link", FullRefund.Status.MD_APPROVAL);
        var fr2 = new FullRefund("BA", "GX35789", "link", FullRefund.Status.TO_BE_SEND);
        var fr3 = new FullRefund("LT", "TY95173", "link", FullRefund.Status.HQ_PRICING);

        var result = List.of(fr1, fr2, fr3);

        return ResponseEntity.ok(result);
    }
}
