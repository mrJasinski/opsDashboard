package com.opsDashboard.ticket;

import com.opsDashboard.ticket.dto.TicketWriteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
class TicketController
{
    private final TicketService ticketService;

    TicketController(final TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    @GetMapping("/pendingTickets")
    ResponseEntity<?> getPendingTickets()
    {
        var result = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            result.add("ticket" + i);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/sendMessage")
    ResponseEntity<?> createTicket(@RequestBody TicketWriteModel message)
    {
        this.ticketService.createTicket(message);

        return ResponseEntity.ok("Message successfully sent!");
    }
}
