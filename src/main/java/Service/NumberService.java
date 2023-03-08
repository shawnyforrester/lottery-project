package Service;


import Model.Message;


import java.util.List;

import DAO.NumberDAO;

public class NumberService {
    public NumberDAO numberDAO;



    public NumberService() {
        numberDAO = new NumberDAO();
    }

    public NumberService(NumberDAO numberDAO){
        this.numberDAO = numberDAO;
    }


    public List<Message> getAllMessage(){
        return numberDAO.getAllMessage();
    }


    public Message getMessageById(int message_id){
        return numberDAO.getMessageById(message_id);
    }



    public Message addMessage(Message message) {
        if( message.message_text!= "" && message.message_text.length()<256){
            return numberDAO.insertMessage(message);
        }
        return null;
    }
    public Message deleteMessageById(int message_id) {
        Number number = numberDAO.getMessageById(message_id);
        numberDAO.deleteMessageById(message_id);
        if (number==null){
            return null;
        }
        return number;
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


