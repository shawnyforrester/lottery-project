package Model;


/**
 * This is a class that models a Ticket
 */
public class Ticket {




    public int ticket_id;

    public String numbers;


    /**
     * A default, no-args constructor, as well as correctly formatted getters and setters, are needed for
     * Jackson Objectmapper to work.
     */
    public Ticket() {
    }



    /**
     * When retrieving a ticket from the database, all fields will be needed. In that case, a constructor with all
     * fields is needed.
     *
     * @param ticket_id
     * @param numbers
     */
    public Ticket(int ticket_id, String numbers) {
        this.ticket_id = ticket_id;
        this.numbers = numbers;


    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @return ticket_id
     */
    public int getTicket_id() {
        return ticket_id;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param ticket_id
     */
    public void setTicket_id() {
        this.ticket_id = ticket_id;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @return powerball_numbers
     */
    public String getPowerball_numbers() {
        return numbers;
    }

    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     *
     * @param numbers
     */
    public void setPowerball_numbers(String numbers) {
        this.numbers = numbers;
    }




    /**
     * Overriding the default equals() method adds functionality to tell when two objects are identical, allowing
     * Assert.assertEquals and List.contains to function.
     *
     * @param o the other object.
     * @return true if o is equal to this object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticket_id == ticket.ticket_id && numbers.equals(ticket.numbers);
    }

    /**
     * Overriding the default toString() method allows for easy debugging.
     *
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
        return "Message{" +
                "ticket_id=" + ticket_id +
                ", powerball_numbers =" + numbers + "}";
    }

}

