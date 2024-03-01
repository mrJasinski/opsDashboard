package com.opsDashboard.ticket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class TicketController
{
    @GetMapping("/pendingTickets")
    ResponseEntity<?> getPendingTickets()
    {
        var result = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            result.add("ticket" + i);

        return ResponseEntity.ok(result);
    }
}
