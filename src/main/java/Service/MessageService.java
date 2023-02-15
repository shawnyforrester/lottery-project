package Service;


import Model.Message;


import java.util.List;

import DAO.MessageDAO;

public class MessageService {
    public MessageDAO messageDAO;



public MessageService() { 
    messageDAO = new MessageDAO(); 
}

public MessageService(MessageDAO messageDAO){
    this.messageDAO = messageDAO;
}


public List<Message> getAllMessage(){
    return messageDAO.getAllMessage();   
}


public Message getMessageById(int message_id){
   return messageDAO.getMessageById(message_id);
}



public Message addMessage(Message message) {
    if( message.message_text!= "" && message.message_text.length()<256){
        return messageDAO.insertMessage(message);
    }
    return null;
}
public Message deleteMessageById(int message_id) {
    Message message = messageDAO.getMessageById(message_id);
    messageDAO.deleteMessageById(message_id);
    if (message==null){
        return null;
    } 
    return message;
 }


public List<Message> getAllMessagesByAccountId(int posted_by){
    return messageDAO.getAllMessagesByAccountId(posted_by);
}

public Message updateMessageById( Message message, int message_id){
    if(getMessageById(message_id) != null && message.message_text!="" && message.message_text.length()<256){
        messageDAO.updateMessageById(message, message_id);
        return messageDAO.getMessageById(message_id);
    }
    return null;
}

}

