package account;

public class Commercial extends Personal{
    public String company;
    public Commercial(User user){
        super(user);
    }

    public Commercial(User user, String company){
        super(user);
        this.company = company;
    }
}
