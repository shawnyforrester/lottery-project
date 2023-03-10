package Controller;

import Model.Account;
import Model.Ticket;
import Service.AccountService;
import Service.TicketService;
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

    private TicketService ticket = new TicketService();
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
        app.get("/accounts/{account_id}/ ticket", this::getTicketByAccountId);
        app.delete("/accounts/{account_id}/delete-account", this:: deleteUserAccount);
        app.delete("/account/ticket-id", this:: deleteTicketUsingId ); 
        return app;
    }
    
   /*This method handles creation of a new user account. Should receive a JSON object and then it
    should persist in the database.
    */

    private void newUserHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Account account = om.readValue(ctx.body(), Account.class);
        AccountService ac = new AccountService();
        Account createdAccount = ac.AddAccount(account);
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
        AccountService ac = new AccountService();
        Account foundAccount = ac.NewUserLogin(account);
        if (foundAccount != null) {
            ctx.json(om.writeValueAsString(foundAccount));
            ctx.status(200);
        } else {
            ctx.status(401);
        }
    }

    /*This method to retrieve a specific ticket from a single user's account
    e.g. a previous PowerBall ticket. Ticket retrieval should done with
    with path parameter specified */

    public void getTicketByAccountId(Context ctx) throws JsonProcessingException {
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        TicketService ts = new TicketService();
        Ticket userTicket = ts.retrieveTicketbyId(account_id);
        ctx.json(userTicket);
    }


    /*Please modify this endpoint to delete a specific user's account
    * The deleted account should be returned in JSON format*/
    private void deleteUserAccount(Context ctx) throws JsonProcessingException {
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        AccountService as = new AccountService();
        Account accountForDeletion = as.deleteAccountById(account_id);
        if (accountForDeletion != null) {
            ctx.json(accountForDeletion);
        } else {
            ctx.status(200);
        }
    }


    //modify method to delete a ticket from the database using the ticket_id;
    private void deleteTicketUsingId(Context ctx) throws JsonProcessingException {
        int ticket_id = Integer.parseInt(ctx.pathParam("ticket_id"));
        TicketService ts = new TicketService();
        Ticket TicketForDeletion = ts.retrieveTicketbyId(ticket_id);
        if (TicketForDeletion != null) {
            ctx.json(TicketForDeletion);
        } else {
            ctx.status(200);
        }
    }


}