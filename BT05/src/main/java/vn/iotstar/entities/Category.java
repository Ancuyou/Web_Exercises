package vn.iotstar.entities;

import jakarta.persistence.*;

import java.io.Serializable;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findByUserId", query = "SELECT c FROM Category c WHERE c.user.id = :userId")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name ="categoryName", columnDefinition = "NVARCHAR(255)")
    private String categoryName;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String images;
    // --------- Thêm phần tham chiếu tới User ---------
    @ManyToOne(fetch = FetchType.LAZY) // Nhiều Category thuộc về 1 User
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category(){
    }
}
