package sn.sentrans.dao;

import sn.sentrans.entities.Bus;
import sn.sentrans.entities.Localite;
import sn.sentrans.entities.TypeLocalite;
import sn.sentrans.entities.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LocaliteImpl implements ILocalite {

    private DB db = new DB();
        @Override
        public int add(Localite localite) {

            String sql = "INSERT INTO localite VALUES (NULL, ?, ?, ?, ?, ?)";
            int ok = 0;
            try {
                db.init(sql);
                db.getPstm().setString(1,localite.getNom());
                db.getPstm().setDouble(2,localite.getLatitude());
                db.getPstm().setDouble(3,localite.getLongitude());
                db.getPstm().setInt(4,localite.getTypeLocalite().getIdT());
                db.getPstm().setInt(5,localite.getUser().getId());

                ok = db.executeMaj();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            return ok;
    }
// Pour la MAJ
    @Override
    public int update(Localite localite) {
        String sql = "UPDATE localite SET nom = ?, latitude = ?, longitude =?, typelocalite =?, user = ? WHERE idL = ?";
        int ok = 0;
        try {
            db.init(sql);
            db.getPstm().setString(1,localite.getNom());
            db.getPstm().setDouble(2,localite.getLatitude());
            db.getPstm().setDouble(3,localite.getLongitude());
            db.getPstm().setInt(4,localite.getTypeLocalite().getIdT());
            db.getPstm().setInt(5,localite.getUser().getId());
            db.getPstm().setInt(6,localite.getIdL());

            ok = db.executeMaj();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok;
    }
//Pour la suspression
    @Override
    public int delete(int id) {
        String sql = "DELETE FROM localite WHERE idL = ?";
        int ok = 0;
        try {
            db.init(sql);
            db.getPstm().setInt(1,id);
            ok = db.executeMaj();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok;
    }

    //Pour l'affichage
    @Override
    public List<Localite> getAll() {
        String sql = "SELECT * FROM localite ";
        List<Localite> localiteList = new ArrayList<>();
        try {
            db.init(sql);
            ResultSet rs =db.executeSelect();
            while (rs.next()) {
                Localite localite = new Localite();
                localite.setIdL(rs.getInt(1));
                localite.setNom(rs.getString(2));
                localite.setLatitude(rs.getDouble(3));
                localite.setLongitude(rs.getDouble(4));
                //Gestion des types de  localite
                TypeLocalite typeLocalite = new TypeLocalite();
                typeLocalite.setIdT(rs.getInt(5));
                localite.setTypeLocalite(typeLocalite);
                //Gestion des users
                User user = new User();
                user.setId(rs.getInt(6));
                localite.setUser(user);
                //Ajout dans la liste des bus
                localiteList.add(localite);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return localiteList;
    }

    @Override
    public Localite get(int id) {

        String sql = "SELECT * FROM localite WHERE idL = ?";
        Localite localite = null;
        try {
            db.init(sql);
            db.getPstm().setInt(1, id);
            ResultSet rs =db.executeSelect();
            if (rs.next()) {
                localite = new Localite();
                localite.setIdL(rs.getInt(1));
                localite.setNom(rs.getString(2));
                localite.setLatitude(rs.getDouble(3));
                localite.setLongitude(rs.getDouble(4));
                //Gestion de la localite
                TypeLocalite typeLocalite = new TypeLocalite();
                typeLocalite.setIdT(rs.getInt(5));
                localite.setTypeLocalite(typeLocalite);
                //Gestion de la localite
                User user = new User();
                user.setId(rs.getInt(6));
                localite.setUser(user);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return localite;

    }
}
