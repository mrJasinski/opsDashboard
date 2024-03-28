package com.opsDashboard.ticket;

import com.opsDashboard.ticket.dto.TicketWriteModel;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
class TicketService
{
    private final TicketRepository ticketRepo;

    TicketService(final TicketRepository ticketRepo)
    {
        this.ticketRepo = ticketRepo;
    }

    void createTicket(final TicketWriteModel toWrite)
    {
        var number = getConsequentTicketNumber();
        var category = getCategoryByNameAndSubCategoryName(toWrite.getCategory(), toWrite.getSubCategory());
        var agent =

        var ticket = new Ticket(
                number
                , toWrite.getCreatorMail()
                , toWrite.getTitle()
                , category
                , toWrite.getStockId()
        );



//    private int number;
//    private String creatorEmail;
//    private String title;
//    private Category category;
//    private String stockId;
//    private UserSource assignedAgent;
    }

    private Ticket.Category getCategoryByNameAndSubCategoryName(final String category, final String subCategory)
    {
        return this.ticketRepo.findCategoryByNameAndSubCategoryName(category, subCategory)
                .orElseThrow(() -> new NoSuchElementException("Category with given name and subcategory not found!"));
    }

    private int getConsequentTicketNumber()
    {
        var number = this.ticketRepo.findMaxTicketNumber()
                .orElse(1000);

        return number + 1;
    }
}
