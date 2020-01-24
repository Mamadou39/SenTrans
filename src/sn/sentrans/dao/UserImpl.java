package sn.sentrans.dao;

import sn.sentrans.entities.TypeLocalite;
import sn.sentrans.entities.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserImpl  implements IUser{

    private DB db = new DB();

    //Pour L'AJOUT DANS USER
    @Override
    public int add(User user) {

        String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?, ?)";
        int ok = 0;
        try {
            db.init(sql);
            db.getPstm().setString(1,user.getNom());
            db.getPstm().setString(2,user.getPrenom());
            db.getPstm().setString(3,user.getEmail());
            db.getPstm().setString(4,user.getPassword());


            ok = db.executeMaj();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }

    //Pour L'AFFICHAGE DANS USER
    @Override
    public List<User> getAll() {

        String sql = "SELECT * FROM user ";
        List<User> userList = new ArrayList<>();
        try {
            db.init(sql);
            ResultSet rs =db.executeSelect();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));


                //Ajout dans la liste des bus
                userList.add(user);
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return userList;
    }

    //Pour LA MAJ DANS USER
    @Override
    public int update(User user) {
        String sql = "UPDATE user SET nom = ?, prenom = ?, email =?, password =? WHERE id = ?";
        int ok = 0;
        try {
            db.init(sql);
            db.getPstm().setString(1,user.getNom());
            db.getPstm().setString(2,user.getPrenom());
            db.getPstm().setString(3,user.getEmail());
            db.getPstm().setString(4,user.getPassword());

            ok = db.executeMaj();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return ok;
    }

    //Pour LA SUSPRESSION DANS USER
    @Override
    public int delete(int id) {


        String sql = "DELETE FROM user WHERE id = ?";
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
    public User get(int id) {
        return null;
    }

    @Override
    public User getLogin(String email, String password) {
        User user = null;
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";

        try{
            db.init(sql);
            db.getPstm().setString(1, email);
            db.getPstm().setString(2, password);
            ResultSet rs = db.executeSelect();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt(1));
                user.setNom(rs.getString(2));
                user.setPrenom(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setPassword(rs.getString(5));
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}
