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
            else if (account.getPassword().length() < 4){
                return null;
            }
            return accountDAO.insertAccount(account);
        }
    

    public Account NewUserLogin(Account account){
        if (account == null) {
            return null;
        }
        return accountDAO.processUsersLogin(account);

    }

    public Account deleteAccountById (int account_id){
        AccountDAO ac = new AccountDAO();
        Account useraccount = ac.getAccountbyId(account_id);

        if (useraccount == null){
            return null;
        } else{
            ac.deleteAccount(account_id);
            return useraccount;
        }


    }

}








    


