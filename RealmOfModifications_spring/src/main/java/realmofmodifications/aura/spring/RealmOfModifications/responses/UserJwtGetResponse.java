package realmofmodifications.aura.spring.RealmOfModifications.responses;

public class UserJwtGetResponse {
    private int id;
    private String fullName;
    private String email;

    public UserJwtGetResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
