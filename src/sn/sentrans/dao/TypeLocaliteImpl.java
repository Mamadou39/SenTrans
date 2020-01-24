package sn.sentrans.dao;

import sn.sentrans.entities.Bus;
import sn.sentrans.entities.Localite;
import sn.sentrans.entities.TypeLocalite;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeLocaliteImpl implements ITypeLocalite {

    private DB db = new DB();
    //POUR L'AJOUT DANS  TYPELOCALITE
    @Override
    public int add(TypeLocalite typeLocalite) {

        String sql = "INSERT INTO typelocalite VALUES (NULL, ?)";
        int ok = 0;
        try {
            db.init(sql);
            db.getPstm().setString(1,typeLocalite.getNom());
            ok = db.executeMaj();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }

    //POUR LA MAJ DANS TYPELOCALITE
    @Override
    public int update(TypeLocalite typeLocalite) {

        String sql = "UPDATE bus SET nom = ? WHERE idT = ?";
        int ok = 0;
        try {
            db.init(sql);
            db.getPstm().setString(1,typeLocalite.getNom());
            db.getPstm().setInt(1,typeLocalite.getIdT());

            ok = db.executeMaj();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;

    }

    //POUR LA SUSPRESSION DANS TYPE LOCALITE
    @Override
    public int delete(int id) {
        String sql = "DELETE FROM bus WHERE idT = ?";
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

    @Override
    public List<TypeLocalite> getAll() {

        String sql = "SELECT * FROM typelocalite ";
        List<TypeLocalite> typeLocaliteList = new ArrayList<>();
        try {
            db.init(sql);
            ResultSet rs =db.executeSelect();
            while (rs.next()) {
                TypeLocalite typeLocalite = new TypeLocalite();
                typeLocalite.setIdT(rs.getInt(1));
                typeLocalite.setNom(rs.getString(2));


                //Ajout dans la liste des bus
                typeLocaliteList.add(typeLocalite);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return typeLocaliteList;

    }

    @Override
    public TypeLocalite get(int id) {
        String sql = "SELECT * FROM typelocalite WHERE idT = ? ";
        TypeLocalite typeLocalite = null;
        try {
            db.init(sql);
            db.getPstm().setInt(1, id);
            ResultSet rs =db.executeSelect();
            if (rs.next()) {
                typeLocalite = new TypeLocalite();
                typeLocalite.setIdT(rs.getInt(1));
                typeLocalite.setNom(rs.getString(2));


            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return typeLocalite;
    }
}
