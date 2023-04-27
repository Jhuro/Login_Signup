package co.edu.unipiloto.login;

public class Usuario {

    public int id;
    public String fullName;
    public String username;
    public String email;
    public String password;
    public Double latitud;
    public Double longitud;
    public int edad;
    public String rol;
    public int gender;

    public Usuario() {
    }

    public Usuario(int id, String fullName, String username, String email, String password, Double latitud, Double longitud, int edad, String rol, int gender) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.latitud = latitud;
        this.longitud = longitud;
        this.edad = edad;
        this.rol = rol;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", edad="+ edad +
                ", rol='" + rol + '\'' +
                ", gender=" + gender +
                '}';
    }
}
