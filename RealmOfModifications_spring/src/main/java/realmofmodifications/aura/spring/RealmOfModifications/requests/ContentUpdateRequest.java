package realmofmodifications.aura.spring.RealmOfModifications.requests;


public class ContentUpdateRequest {
    private String content;

    public ContentUpdateRequest() {
    }

    public ContentUpdateRequest(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
