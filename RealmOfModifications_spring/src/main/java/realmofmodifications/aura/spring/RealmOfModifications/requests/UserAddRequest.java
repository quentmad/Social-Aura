package realmofmodifications.aura.spring.RealmOfModifications.requests;

public class UserAddRequest {

    private String name;
    private String lastName;
    private int birth;
    private String email;
    private String password;

    public UserAddRequest(String name, String lastName, int birth, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.birth = birth;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
