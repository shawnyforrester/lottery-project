
package Controller;


import java.util.List;

import Service.NumberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import Model.Account;
import Model.Message;
import io.javalin.Javalin;
import io.javalin.http.Context;
import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    private AccountService accountService = new AccountService();
    private MessageService messageService = new MessageService();

    private NumberService numberService = new NumberService();
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("hello", ctx -> {ctx.json("hello");});
        app.post("/register", this:: newUserHandler);
        app.post("/login", this:: loginHandler);
        app.post("/messages", this ::postMessageHandler);
        app.get("/messages", this::allMessagesHandler);
        app.get("/messages/{message_id}", this ::messageByIdHandler);
        app.delete("/messages/{message_id}", this ::deleteMessageHandler);
        app.patch("/messages/{message_id}", this ::updateMessagesUserIdHandler);
        app.get("/accounts/{account_id}/messages", this :: getAccountsandIdHandler);
    
        return app;
    }
    
    
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
    
    
      private void postMessageHandler(Context ctx) throws JsonProcessingException{
         ObjectMapper om = new ObjectMapper();
         Message message = om.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
         if (addedMessage != null) {
             ctx.json(om.writeValueAsString(addedMessage));
         } else {
             ctx.status(400);
         }
     }
    
      public void allMessagesHandler(Context ctx) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessage();
            ctx.json(messages);
      }

      public void messageByIdHandler(Context ctx) throws JsonProcessingException{
          int message_id = Integer.parseInt(ctx.pathParam("message_id"));
          Message updatedMessage = messageService.getMessageById(message_id);
          if (updatedMessage != null) {
              ctx.json(updatedMessage);
          } else {
              ctx.status(200);
          }
      }
        
      private void deleteMessageHandler(Context ctx) throws JsonProcessingException{
                int message_id = Integer.parseInt(ctx.pathParam("message_id"));
                Message updatedMessage = messageService.deleteMessageById(message_id);
                if (updatedMessage != null) {
                    ctx.json(updatedMessage);
                } else {
                    ctx.status(200);
                }
            }
        
        

    
        
     private void updateMessagesUserIdHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMessageById( message, message_id);
        if (updatedMessage != null) {
            ctx.json(mapper.writeValueAsString(updatedMessage));
        } else {
            ctx.status(400);
        }
    }

      

    private void getAccountsandIdHandler(Context ctx) {
         int posted_by = Integer.parseInt(ctx.pathParam("account_id"));
         List<Message> updatedMessages = messageService.getAllMessagesByAccountId(posted_by);
             ctx.json(updatedMessages);
      }
    
     }