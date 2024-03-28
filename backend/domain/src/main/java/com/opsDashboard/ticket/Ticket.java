package com.opsDashboard.ticket;

import com.opsDashboard.vo.UserSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

class Ticket
{
    private int id;
    private int number;
    private String creatorEmail;
    private String title;
    private Category category;
    private String stockId;
    private LocalDate assignedDate;
    private UserSource assignedAgent;

    static class Message
    {
        private int id;
        private LocalDateTime timestamp;
        private String content;
        private String senderMail;
        private String receiverMail;
        private Set<String> ccMails;

    }

    static class Category
    {
        private int id;
        private String name;
        private SubCategory subCategory;

        int getId()
        {
            return this.id;
        }

        String getName()
        {
            return this.name;
        }

        SubCategory getSubCategory()
        {
            return this.subCategory;
        }

        static class SubCategory
        {
            private int id;
            private String name;
            private Set<UserSource> agents;

            int getId()
            {
                return this.id;
            }

            String getName()
            {
                return this.name;
            }

            Set<UserSource> getAgents()
            {
                return this.agents;
            }
        }

    }
}
