package com.opsDashboard.ticket;

import com.opsDashboard.ticket.dto.TicketWriteModel;
import com.opsDashboard.user.UserService;
import com.opsDashboard.vo.UserSource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
class TicketService
{
    private final TicketRepository ticketRepo;
    private final UserService userService;

    TicketService(final TicketRepository ticketRepo, UserService userService)
    {
        this.ticketRepo = ticketRepo;
        this.userService = userService;
    }

    void createTicket(final TicketWriteModel toWrite)
    {
        var number = getConsequentTicketNumber();
        var category = getCategoryByNameAndSubCategoryName(toWrite.getCategory(), toWrite.getSubCategory());
        var agent = this.userService.getAgentSourceToAssignTicketToByUserIds(category.getSubCategory().getAgentsSet().stream().map(UserSource::getId).toList());





        var ticket = new Ticket(
                number
                , toWrite.getCreatorMail()
                , toWrite.getTitle()
                , category
                , toWrite.getStockId()
                , LocalDate.now()
                , agent
        );

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
