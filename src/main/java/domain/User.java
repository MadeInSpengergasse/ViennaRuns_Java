package domain;

public class User extends BaseModel<User, Long> {

    private String name;
    private String password;


    public User() {
        super();
    }

    public User(String name, String password) {
        this(-1L, -1, name, password);
    }

    public User(final Long id, final Integer version, String name, String password) {
        super(id, version);

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
        if (super._compareTo(o)==-1) return -1;
        else
        if(o.getName().equals(this.getName()) &&
                o.getPassword().equals(this.getPassword()))
            return 0;
        else
            return -1;
    }
}
