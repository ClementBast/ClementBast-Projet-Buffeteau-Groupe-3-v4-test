package sio.projetbuffteauv3.entities;

public class Utilisateur {
    private String email;
    private String motDePasse;
    private int id;
    public Utilisateur(String email, String motDePasse, int id){
        this.email = email;
        this.motDePasse = motDePasse;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
