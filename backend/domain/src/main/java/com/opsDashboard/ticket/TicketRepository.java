package com.opsDashboard.ticket;

import java.util.Optional;

interface TicketRepository
{
    Optional<Integer> findMaxTicketNumber();

    Optional<Ticket.Category> findCategoryByNameAndSubCategoryName(String category, String subCategory);
}
