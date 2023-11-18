package sio.projetbuffteauv3.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceUtilisateur {
    private Connection uneCnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServiceUtilisateur()
    {
        uneCnx = ConnexionBDD.getCnx();
    }

    public int getIdUtilisateurByMail(String email) throws SQLException {
        ps = uneCnx.prepareStatement("SELECT user.id FROM user WHERE email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        rs.next();
        int unId = rs.getInt(1);
        return unId;
    }


}
