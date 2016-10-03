package domains;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */
public class User {
    private String name;
    private String password;

    public User(String name, String password) {
        setName(name);
        setPassword(password);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
