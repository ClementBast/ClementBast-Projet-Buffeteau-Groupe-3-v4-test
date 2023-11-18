package sio.projetbuffteauv3.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import sio.projetbuffteauv3.entities.Competence;
import sio.projetbuffteauv3.entities.Demande;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServicesCompetences implements Initializable {
    private Connection uneCnx ;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesCompetences(){uneCnx = ConnexionBDD.getCnx();}
    public void initialize(URL url, ResourceBundle resourceBundle) {this.uneCnx = ConnexionBDD.getCnx();}
    public ObservableList<Competence> GetMatiereCompetences(int id_user) throws SQLException {
        ObservableList<Competence> lesMatCompetences = FXCollections.observableArrayList();
        PreparedStatement ps = uneCnx.prepareStatement("SELECT matiere.designation, competence.sous_matiere FROM competence JOIN matiere ON matiere.id = competence.id_matiere WHERE id_user = ?");
        ps.setInt(1, id_user);

        rs = ps.executeQuery();

        while(rs.next()) {
            Competence uneCompetence = new Competence(rs.getString(1), rs.getString(2), id_user);
            lesMatCompetences.add(uneCompetence);
        }
        return lesMatCompetences;
    }

    public ObservableList<Competence> GetSousMatiereCompetences(int id_user, String matiere) throws SQLException {
        ObservableList<Competence> lesSousMatCompetences = FXCollections.observableArrayList();
        PreparedStatement ps = uneCnx.prepareStatement("SELECT competence.sous_matiere FROM competence JOIN matiere ON matiere.id = competence.id_matiere \n" +
                "WHERE matiere.designation = ? AND id_user = ?;");
        ps.setString(1, matiere);
        ps.setInt(2, id_user);
        rs = ps.executeQuery();

        while(rs.next()) {
            Competence uneCompetence = new Competence(matiere, rs.getString(1), id_user);
            lesSousMatCompetences.add(uneCompetence);
        }
        return lesSousMatCompetences;
    }

}