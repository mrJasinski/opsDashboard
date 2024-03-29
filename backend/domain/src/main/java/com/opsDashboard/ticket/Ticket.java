package com.opsDashboard.ticket;

import com.opsDashboard.vo.UserSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private List<Message> messages;

    Ticket()
    {
    }

    Ticket(
            final int number
            , final String creatorEmail
            , final String title
            , final Category category
            , final String stockId
            , final LocalDate assignedDate
            , final UserSource assignedAgent
            )
    {
        this.number = number;
        this.creatorEmail = creatorEmail;
        this.title = title;
        this.category = category;
        this.stockId = stockId;
        this.assignedDate = assignedDate;
        this.assignedAgent = assignedAgent;
    }

    void addMessage(Message message)
    {
        this.messages.add(message);
    }

    static class Message
    {
        private int id;
        private LocalDateTime timestamp;
        private String content;
        private String senderMail;
        private String receiverMail;
        private String ccMails;

        Message()
        {
        }

        Message(final LocalDateTime timestamp
                , final String content
                , final String senderMail
                , final String receiverMail
                , final Set<String> ccMails)
        {
            this.timestamp = timestamp;
            this.content = content;
            this.senderMail = senderMail;
            this.receiverMail = receiverMail;
            this.ccMails = wrapCcMails(ccMails);
        }

        String wrapCcMails(Set<String> ccMails)
        {
            var result = new StringBuilder();

            for (final String mail : ccMails)
                result.append(mail).append(" ");

            return result.toString();
        }

        Set<String> unwrapCcMails(String ccMails)
        {
            var mailsSplit = ccMails.split(" ");

            var mails = new String[mailsSplit.length];

            System.arraycopy(mailsSplit, 0, mails, 0, mailsSplit.length);

            return Set.of(mails);
        }

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
            private String agents;

            int getId()
            {
                return this.id;
            }

            String getName()
            {
                return this.name;
            }

            Set<UserSource> getAgentsSet()
            {
                return unwrapAgents(this.agents);
            }

            String wrapAgents(Set<UserSource> agents)
            {
                var result = new StringBuilder();

                for (final UserSource agent : agents)
                    result.append(agent).append(" ");

                return result.toString();
            }

            Set<UserSource> unwrapAgents(String agents)
            {
                var agentsSplit = agents.split(" ");

                var sources = new UserSource[agentsSplit.length];

                for (int i = 0; i < agentsSplit.length; i++)
                    sources[i] = new UserSource(Integer.parseInt(agentsSplit[i]));

                return Set.of(sources);
            }
        }

    }
}
