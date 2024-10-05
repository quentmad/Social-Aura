package realmofmodifications.aura.spring.RealmOfModifications.requests;

public class FavoriteAddRequest {
    private int postId;
    private int userId;

    public FavoriteAddRequest(int postId, int userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
