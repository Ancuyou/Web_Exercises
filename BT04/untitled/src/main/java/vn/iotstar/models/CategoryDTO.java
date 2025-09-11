package vn.iotstar.models;

public class CategoryDTO {
    private int id;
    private String categoryName;
    private String images;

    // Thông tin user sở hữu category
    private int userId;
    private String username;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
