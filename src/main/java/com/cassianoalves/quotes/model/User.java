package com.cassianoalves.quotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

import java.util.Map;

public class User {
    @Id
    private String id;
    private String name;
    private String email;
    @JsonIgnore // Nunca retornar ou mostrar senhas ou hashs de senha
    private String password;
    private Status status;

    public User() {
        // default contructor
    }

    public User(Map<String, Object> userMap) {
        this.id = (String) userMap.get("id");
        this.name = (String) userMap.get("name");
        this.email = (String) userMap.get("email");
        this.password = (String) userMap.get("password");
    }

    public enum Status {
        NOT_CONFIRMED,
        LOCKED,
        ACTIVE
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore // Nunca retornar ou mostrar senhas ou hashs de senha
    public String getPassword() {
        return password;
    }

    @JsonProperty // Para receber a senha quando da criação do usuário
    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("email", email)
                .append("password", "****")
                .append("status", status)
                .toString();
    }
}
