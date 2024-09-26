package Interface;
import Model.User;
public interface AuthInterface {
    void registerUser(String username, String password, String contactInfo, boolean isDonor);
    User loginUser(String username, String password);
}

