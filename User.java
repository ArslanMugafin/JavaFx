
package fx;


public class User {
    private String login="Arslan";
    private String pass="privet";
    
    public User(){
    }
    
    public User(String login, String pass){
        this.login=login;
        this.pass=pass;
    }
    
    
    public String getLogin() {
		return login;
	}

    public void setLogin(String a) {
		login = a;
	}
    
    public String getPass() {
		return pass;
	}

    public void setPass(String a) {
		pass = a;
	}
}