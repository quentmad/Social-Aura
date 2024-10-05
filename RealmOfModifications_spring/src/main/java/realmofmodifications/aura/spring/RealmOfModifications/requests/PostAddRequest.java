package realmofmodifications.aura.spring.RealmOfModifications.requests;

public class PostAddRequest {
    private int userId;
    private String content;

    public PostAddRequest(int userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
