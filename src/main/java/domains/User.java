package domains;


/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */

public class User extends BaseModel<User, Long> {

    private String name;
    private String password;


    public User() {
        super();
    }

    public User(final Long id, final Integer version, String name, String password) {
        super(id, version);
        setId(id);
        setVersion(version);
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


    @Override
    public int compareTo(User o) {
        return 0;
    }
}
