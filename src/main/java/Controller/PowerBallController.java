
package Controller;


import java.util.List;

import Model.Account;
import Service.AccountService;
import Service.NumberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class PowerBallController {

    NumberService numbers = new NumberService();
    private AccountService account = new AccountService();


    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     *
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::newUserHandler); //this endpoint handles new user registration
        app.post("/login", this::loginHandler);//handles login
        app.get("/accounts/{account_id}/ticket", this::getTicketbyAccountId);//retrieves ticket by accountid
        app.get("/account/{account_id}/all-tickets", this::getAllTickets);//retrieves all tickets on an account
        app.delete("/account/{account_id}/delete-account", this:::deleteUserAccount);//deletes a user's account
        app.delete("/account/ticketid", this:::deleteTicketUsingId ); //deletes a ticket using the ticketid
        return app;
    }
    
   /*This method handles creation of a new user account. Should receive a JSON object and then it
    should persist in the database.
    */

    private void newUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account createdAccount = accountService.AddAccount(account);
        if (createdAccount == null) {
            ctx.status(400);
        } else {
            ctx.json(om.writeValueAsString(createdAccount));
        }
    }

    /*This method will return an account object if the username and password given by the client exist in the database
    * under the same client */
    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        Account foundAccount = accountService.processUserLogin(account);
        if (foundAccount != null) {
            ctx.json(om.writeValueAsString(foundAccount));
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    }

    /*Please modify this method to retrieve a specific ticket from a single user's account
    e.g. a previous PowerBall ticket or a previous MegaMillions ticket. Ticket retrieval should done with
    with path parameter specified */

    public void getTicketByAccountId(Context ctx) throws JsonProcessingException {
        List<Message> messages = messageService.getAllMessage();
        ctx.json(messages);
    }

    /*Please modify this endpoint to get all tickets (as an ArrayList)for a single user using the
    account_id given as a parameter.
    * */

    public void getAllTickets(Context ctx) throws JsonProcessingException {
        int account_id_id = Integer.parseInt(ctx.pathParam("account_id"));
        Message updatedMessage = messageService.getMessageById(message_id);
        if (updatedMessage != null) {
            ctx.json(updatedMessage);
        } else {
            ctx.status(200);
        }
    }

    /*Please modify this endpoint to delete a specific user's account
    * The deleted account should be returned in JSON format*/
    private void deleteUserAccount(Context ctx) throws JsonProcessingException {
        int account_id_id = Integer.parseInt(ctx.pathParam("account_id"));
        Account accountForDeletion = AccountService.deleteAccountById(account_id);
        if (accountForDeletion != null) {
            ctx.json(accountForDeletion);
        } else {
            ctx.status(200);
        }
    }


    //modify method to delete a ticket from the database using the ticket_id;
    private void deleteTicketUsingId(Context ctx) throws JsonProcessingException {
        int ticket_id = Integer.parseInt(ctx.pathParam("ticket_id"));
        Ticket TicketForDeletion = TicketService.deleteAccountById(ticket_id_id);
        if (TicketForDeletion != null) {
            ctx.json(TicketForDeletion);
        } else {
            ctx.status(200);
        }
    }


}