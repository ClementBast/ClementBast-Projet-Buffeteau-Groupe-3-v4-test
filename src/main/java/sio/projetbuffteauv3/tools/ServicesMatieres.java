package sio.projetbuffteauv3.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sio.projetbuffteauv3.entities.Demande;
import sio.projetbuffteauv3.entities.Matiere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class ServicesMatieres {
    private Connection uneCnx ;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServicesMatieres(){
        uneCnx = ConnexionBDD.getCnx();
    }
    public ObservableList<String> getAllMatieres() throws SQLException{
        ObservableList<String> lesMatieres = FXCollections.observableArrayList();

        PreparedStatement ps = uneCnx.prepareStatement("SELECT designation FROM matiere ");
        rs = ps.executeQuery();

        while(rs.next())
        {
            String laMatiere = ((rs.getString(1)));
            lesMatieres.add(laMatiere);
        }
        return lesMatieres;
    }

    public ObservableList<Matiere> getAllSousMatieresByMatieres(String matiere) throws SQLException {
        ObservableList<Matiere> lesSousMatieres = FXCollections.observableArrayList();

        PreparedStatement ps = uneCnx.prepareStatement("SELECT designation, sous_matiere FROM matiere WHERE designation = ? ");
        ps.setString(1, matiere);
        rs = ps.executeQuery();

        while (rs.next()) {

            Matiere sousMatieres = new Matiere(rs.getString(1), rs.getString(2).split("#")[0]);
            lesSousMatieres.add(sousMatieres);
        }

        return lesSousMatieres;
    }

}
