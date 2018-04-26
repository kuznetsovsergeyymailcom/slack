package role;

import model.User;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_desc")
    private String role;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_USER_ROLE", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;


    public Role(){}

    public Role(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id &&
                Objects.equals(role, role1.role) &&
                Objects.equals(users, role1.users);
    }

    @Override
    public int hashCode() {

        return Objects.hash(role);
    }
}
