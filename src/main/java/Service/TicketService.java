package Service;


import java.util.List;

import DAO.TicketDAO;
import Model.Ticket;


public class TicketService {
    public TicketDAO ticketDAO;


    public TicketService() {
        ticketDAO = new TicketDAO();
    }

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public Ticket retrieveTicketbyId(int account_id) {

        TicketDAO ticket = new TicketDAO();
        ticket.getNewTicket(account_id);
        return ticket.retrieveTicket(account_id);

    }


    public Ticket deleteTicketById(int ticket_id) {
        TicketDAO ticket = new TicketDAO();
        Ticket ticketReturned = ticket.retrieveTicket(ticket_id);

        if (ticketReturned != null) {

            ticket.deleteTicketbyId(ticket_id);
            return ticketReturned;
        } return null;

    }


}


