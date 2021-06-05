package org.example.boilerplate.model;

import org.javawebstack.orm.Model;
import org.javawebstack.orm.annotation.Column;
import org.javawebstack.orm.annotation.Dates;
import org.javawebstack.orm.annotation.SoftDelete;
import org.javawebstack.webutils.crypt.BCrypt;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Dates
@SoftDelete
public class User extends Model {
    @Column
    public int id;

    @Column
    public String name;

    @Column
    public String eMail;

    @Column
    private String password;

    @Column
    public Timestamp createdAt;
    @Column
    public Timestamp updatedAt;
    @Column
    public Timestamp deletedAt;

    public void setPassword(String password) {
        this.password = BCrypt.hash(password);
    }

    public boolean checkPassword(String password){
        return BCrypt.check(this.password, password.getBytes(StandardCharsets.UTF_8));
    }
}
