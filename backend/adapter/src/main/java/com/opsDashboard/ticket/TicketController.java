package com.opsDashboard.ticket;

import com.opsDashboard.security.JwtService;
import com.opsDashboard.ticket.dto.TicketWriteModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
class TicketController
{
    private final TicketService ticketService;
    private final JwtService jwtService;

    TicketController(final TicketService ticketService, JwtService jwtService)
    {
        this.ticketService = ticketService;
        this.jwtService = jwtService;
    }

    @GetMapping("/pendingTickets")
    ResponseEntity<?> getPendingTickets()
    {
//        zawartość testowa
        var result = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            result.add("ticket" + i);

        return ResponseEntity.ok(result);
    }
//TODO poprawić link w angularze
    @PostMapping("/sendContactForm")
    ResponseEntity<?> createTicket(@RequestBody TicketWriteModel message)
    {
        this.ticketService.createTicket(message);

        return ResponseEntity.ok("Message successfully sent!");
    }

//    TODO
//    GET kategorie po rodzaju/grupie maila - klient/sales/gość/ops itd
//    GET subkategorie z danej kategorii / też wg grupy?
//    podpowiedzi stock id (np dla klienta)?


    @GetMapping(value = "/tickets", params = {"filter"})
    ResponseEntity<?> getTickets(HttpServletRequest request, @RequestParam(name = "filter") TicketFilter filter)
    {
//        filter has options - ALL/unresponded
        var tickets = this.ticketService.getTicketsByUserIdAndFilterAsDto(this.jwtService.getUserIdFromToken(request), filter);

        return ResponseEntity.ok(tickets);
    }
}
