import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> users = new HashMap<>();
    private static int currentId = 1;

    public static User createUser(String name, String email) {
        User user = new User(currentId++, name, email);
        users.put(user.getId(), user);
        return user;
    }

    public static User getUser(int id) {
        return users.get(id);
    }

    public static User updateUser(int id, String name, String email) {
        User user = users.get(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
        }
        return user;
    }

    public static User deleteuser(int id) {
        return users.remove(id);
    }

    public static Map<Integer, User> getAllUsers() {
        return users;
    }
}