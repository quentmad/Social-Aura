package realmofmodifications.aura.spring.RealmOfModifications.requests;



public class CommentAddRequest {
    private int userId;
    private int postId;
    private String content;

    public CommentAddRequest() {
    }

    public CommentAddRequest(int userId, int postId, String content) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
