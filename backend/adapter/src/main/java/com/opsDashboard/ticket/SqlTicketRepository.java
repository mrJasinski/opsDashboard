package com.opsDashboard.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlTicketRepository extends TicketRepository, JpaRepository<Ticket, Integer>
{
    @Override
    @Query("SELECT MAX(t.number) FROM Ticket t")
    Optional<Integer> findMaxTicketNumber();

    @Override
    @Query("FROM Category c WHERE c.name = :category AND c.subCategory.name = :subCategory")
    Optional<Ticket.Category> findCategoryByNameAndSubCategoryName(String category, String subCategory);
}
