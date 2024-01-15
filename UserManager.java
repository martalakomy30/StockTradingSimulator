import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public final class UserManager {

    private static UserManager user_Manager;
    private static HashMap<String, User> users;

    public static UserManager getInstance(){
        if (user_Manager == null) user_Manager = new UserManager();
        return user_Manager;
    }
    private UserManager(){
       // this.users = new HashMap<String, User>();
    }
    public static void init(String[] userIn) throws BadParameterException {
        if(users == null) users = new HashMap<String, User>();
        for (int i = 0; i < userIn.length; i++){
            users.put(userIn[i], new User(userIn[i]));
        }
    }
    public static User getUser(String id){
        return users.get(id);
    }
    public User getRandomUser(){
        if(users.isEmpty()) return null;
        int rand = (int) Math.floor(Math.random() * (users.values().size()-1));
        if (users.values().toArray()[rand] == null) {return this.getRandomUser();}
        else{return (User) users.values().toArray()[rand];}
    }
    public void addToUser(String userId, OrderDTO o) throws BadParameterException {
        users.get(userId).addOrder(o);
    }

    @Override
    public String toString() {
        String s = "";
        Iterator<User> i = this.users.values().iterator();
        while (i.hasNext()){
            User res = i.next();
            s = s.concat("\n" + res.toString());
        }
        return s;
    }
}
