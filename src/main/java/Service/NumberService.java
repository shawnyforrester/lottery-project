package Service;


import Model.Message;


import java.util.List;

import DAO.NumberDAO;
import Model.Number;

public class NumberService {
    public NumberDAO numberDAO;



    public NumberService() {
        numberDAO = new NumberDAO();
    }

    public NumberService(NumberDAO numberDAO){
        this.numberDAO = numberDAO;
    }


    public List<Number> getAllMessage(){
        return numberDAO.getAllMessage();
    }


    public Number getMessageById(int message_id){
        return numberDAO.getMessageById(message_id);
    }



    public Number addNumbers(Number number) {
        if( number.message_text!= "" && number.message_text.length()<256){
            return numberDAO.insertNumber(number);
        }
        return null;
    }
    public Number deleteMessageById(int message_id) {
        Number number = numberDAO.getMessageById(message_id);
        numberDAO.deleteMessageById(message_id);
        if (number==null){
            return null;
        }
        return number;
    }


    public List<Number> getAllMessagesByAccountId(int posted_by){
        return numberDAO.getAllMessagesByAccountId(posted_by);
    }

    public Number updateMessageById( Number number, int message_id){
        if(getMessageById(message_id) != null && number.message_text!="" && number.message_text.length()<256){
            numberDAO.updateMessageById(number, message_id);
            return numberDAO.getMessageById(message_id);
        }
        return null;
    }

}


