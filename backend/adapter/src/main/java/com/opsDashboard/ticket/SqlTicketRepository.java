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
    @Query(value = "SELECT *" +
            " FROM categories" +
            " JOIN sub_categories ON sub_categories.id = categories.sub_category_id" +
            " WHERE categories.name = :category AND sub_categories.name = :subCategory", nativeQuery = true)
    Optional<Ticket.Category> findCategoryByNameAndSubCategoryName(String category, String subCategory);
}
