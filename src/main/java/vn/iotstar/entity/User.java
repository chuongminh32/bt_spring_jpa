package vn.iotstar.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")   // tên bảng trong DB
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "fullname", length = 100)
    private String fullName;

    private int phone;

    @Column(name = "passwd", length = 255)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "signup_date")
    private Date signupDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    private Date lastLogin;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    private String avatar;

    @Column(length = 50, unique = true)
    private String username;

    public User() {}

    public User(int id, String email, String fullName, Integer phone, String password,
                Date signupDate, Date lastLogin, Boolean isAdmin, String avatar) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
        this.signupDate = signupDate;
        this.lastLogin = lastLogin;
        this.isAdmin = isAdmin;
        this.avatar = avatar;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Integer getPhone() { return phone; }
    public void setPhone(Integer phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Date getSignupDate() { return signupDate; }
    public void setSignupDate(Date signupDate) { this.signupDate = signupDate; }

    public Date getLastLogin() { return lastLogin; }
    public void setLastLogin(Date lastLogin) { this.lastLogin = lastLogin; }

    public Boolean getIsAdmin() { return isAdmin; }
    public void setIsAdmin(Boolean isAdmin) { this.isAdmin = isAdmin; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return "User [id=" + id + 
               ", email=" + email + 
               ", fullName=" + fullName + 
               ", phone=" + phone + 
               ", password=" + password + 
               ", signupDate=" + signupDate + 
               ", lastLogin=" + lastLogin + 
               ", isAdmin=" + isAdmin + 
               ", avatar=" + avatar +
               ", username=" + username + "]";
    }
}
