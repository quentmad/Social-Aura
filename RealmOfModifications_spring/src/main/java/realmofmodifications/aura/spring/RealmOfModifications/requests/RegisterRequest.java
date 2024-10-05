package realmofmodifications.aura.spring.RealmOfModifications.requests;

public class RegisterRequest {

    private String email;
    private String password;
    private String name;
    private String lastName;
    private int birthDate;//DDMMYYYY

    public RegisterRequest(String email, String password, String name, String lastName, int birthDate) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
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

    public int getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }
}
