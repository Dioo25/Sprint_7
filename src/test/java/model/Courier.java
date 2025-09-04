package model;

import java.util.UUID;

public class Courier {
    private String login;
    private String password;
    private String firstName;

    public Courier() { }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }

    // фабричный метод — создаёт курьера со случайными данными
    public static Courier random() {
        String unique = UUID.randomUUID().toString().substring(0, 6);
        return new Courier("user_" + unique, "pass_" + unique, "First_" + unique);
    }
}