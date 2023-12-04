package model;

public class Empleado {

    private int id;
    private String name, surname, mail;


    public Empleado() {
    }

    public Empleado(String name, String surname, String mail) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
    }

    public Empleado(int id, String name, String surname, String mail) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
