package entitie;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "login")
    private String login;
    @Column(name = "role", columnDefinition = "user")
    private String role = "user";

    public User() {
    }

    public User(int id) {
        this.id = id;
        this.name = "defaultName";
        this.password = "defaultPassword";
        this.login = "defaultLogin";
        this.role = "user";
    }

    public User(String name, String password, String login, String role) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.role = role;
    }

    public User(int id, String name, String password, String login, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
