package domains;

/**
 * Written by Luca Weiss (z3ntu)
 * https://github.com/z3ntu
 */

public abstract class User<DOMAIN_TYPE extends User, PK_TYPE extends String> implements Comparable<DOMAIN_TYPE> {
    private PK_TYPE id;

    private Integer version;


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
