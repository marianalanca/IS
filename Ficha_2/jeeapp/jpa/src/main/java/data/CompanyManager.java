package data;

import org.hibernate.annotations.ColumnTransformer;

import java.util.List;
import java.util.Date;
import javax.persistence.*;

@Entity
public class CompanyManager {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String email;
    @ColumnTransformer(read = "pgp_sym_decrypt(password::bytea, 'mySecretKey')", write = "pgp_sym_encrypt(?, 'mySecretKey')")
    private String password;

    public CompanyManager() {}

    public CompanyManager(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
