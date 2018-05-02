package model;

import role.Role;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User() {}

    public User(int id) {
        this.id = id;
        this.name = "defaultName";
        this.password = "defaultPassword";
        this.login = "defaultLogin";

    }

    public User(int id, String name, String password, String login) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
    }

    public User(String name, String password, String login, Set<Role> roles) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.roles = roles;
    }

    public User(String name, String password, String login, String[] roles) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.setRolesFromStringArray(roles);
    }

    public User(int id, String name, String password, String login, String[] roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.setRolesFromStringArray(roles);
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


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private void setRolesFromStringArray(String[] array) {
//        Role role;
//        Set<Role> roles = new HashSet<>();
//        for (String str : array) {
//            role = new Role();
//            if (str.equalsIgnoreCase("admin")) {
//                role.setRole("admin");
//            } else {
//                role.setRole("user");
//            }
//            roles.add(role);
//        }
//        this.setRoles(roles);

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                ", roles=" + Arrays.toString(roles.toArray()) +
                '}';
    }
}
