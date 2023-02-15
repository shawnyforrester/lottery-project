package Service;

import Model.Account;
// not using this for the account*** this is for the message 
import DAO.AccountDAO;

public class AccountService {
     AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

        public Account AddAccount(Account account) {
            if (account.username == "" ) {
                 return null;
            }
            if (account.getPassword().length() < 4){
                return null;
            }
            return accountDAO.insertAccount(account);
        }
    

    public Account processUserLogin(Account account){
        if (account == null) {
            return null;
        }
        return accountDAO.processUsersLogin(account);

    }

}








    


