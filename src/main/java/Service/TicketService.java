package Service;


import java.util.List;

import DAO.NumberDAO;
import Model.Tickets;

public class TicketService {
    public TicketDAO ticketDAO;



    public TicketService () {
        ticketDAO = new TicketDAO();
    }

    public TicketService (TicketDAO ticketDAO){
        this.ticketDAO = ticketDAO;
    }


    public List<Tickets> getAllTickets(int account_id){
        return ticketDAO.getAllTickets(account_id);
    }


    public Message getTicketById(int ticket_id){
        return ticketDAO.getMessageById(ticket_id);
    }


    public Ticket deleteTicketById(int ticket_id) {
        Ticket ticket = ticketDAO.getTicketById(ticket_id);
        ticketDAO.deleteTicketById(ticket_id);
        if (ticket ==null){
            return null;
        }
        return ticket;
    }


}


