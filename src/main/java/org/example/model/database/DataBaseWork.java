package org.example.model.database;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.transaction.UserTransaction;
import org.example.data.entity.EDescription;
import org.example.data.entity.ELogin;
import org.example.data.entity.ETitle;
import org.example.data.mdata.DDescription;
import org.example.data.mdata.DTitle;
import org.example.data.mdata.DLogin;

import java.util.Objects;


public class DataBaseWork implements IDataBaseWork {

    //@PersistenceUnit(unitName = "LibraryJDBC_PersistenceUnit")
    private EntityManagerFactory EMF;

    @PostConstruct
    public void PersisInit(){
        EMF = Persistence.createEntityManagerFactory("LibraryJDBC_PersistenceUnit");
    }

    @Resource
    private UserTransaction Transaction;

    @Override
    public Object login(String MailLogin, String Password) {
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                return new DLogin("Error while Entity Manager initializing", -1, null);
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query = entityManager.createNativeQuery("Select * from users where (email = ? or login = ?) and password = ?", ELogin.class);

            query.setParameter(1, MailLogin)
                    .setParameter(2, MailLogin)
                    .setParameter(3, Password);


            DLogin dlogin = null;

            try {
                dlogin  = new DLogin((ELogin) query.getSingleResult());
            } catch (Exception ignored) {}

            Transaction.commit();
            entityManager.close();

            return Objects.requireNonNullElseGet(dlogin, () -> new DLogin("Invalid username / mail or password", -1, null));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.close();
            return new DLogin("Failed to connect to server", -1, null);
        }
    }

    @Override
    public String add_user(String Mail, String Login, String Password) {

        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                return "Error while Entity Manager initializing";
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query;

            int Count_users = 0;

            query = entityManager.createNativeQuery("Select * from users");
            query.setParameter(1, Mail);
            Count_users = query.getResultList().size();
            if (Count_users > 0) {

                query = entityManager.createNativeQuery("Select * from users where email = ?");
                query.setParameter(1, Mail);
                if (query.getResultList().size() != 0) {
                    entityManager.close();
                    return "The entered mail is already in use, please enter another mail";
                }

                query = entityManager.createNativeQuery("Select * from users where login = ?");
                query.setParameter(1, Login);
                if (query.getResultList().size() != 0) {
                    entityManager.close();
                    return "The login you entered is already in use, please enter another login";
                }
            }

            query = entityManager.createNativeQuery("Insert into users (email, login, password) values (?,?,?)");
            query.setParameter(1, Mail)
                    .setParameter(2, Login)
                    .setParameter(3, Password)
                    .executeUpdate();

            Transaction.commit();
            entityManager.close();

         return "Successful registration";

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.close();
            return "Failed to connect to server";
        }
    }

    @Override
    public String add_title(String userID, String title, StringBuilder Msg) {
        EntityManager entityManager = null;

        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                Msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();

            Query query;
            ETitle eTitle;
            query = entityManager.createNativeQuery("Select * from user_title where title = ? and userid = ?", ETitle.class);
            query.setParameter(1, title)
                    .setParameter(2, userID);

            if (query.getResultList().size() != 0) {
                eTitle = (ETitle) query.getSingleResult();

                Transaction.commit();
                entityManager.close();
                return eTitle.getTitle_ID().toString();
            }

            query = entityManager.createNativeQuery("Insert into user_title (title, userid) values (?,?)");
            query.setParameter(1, title)
                    .setParameter(2, userID)
                    .executeUpdate();

            eTitle = (ETitle) query.getSingleResult();

            Transaction.commit();
            entityManager.close();

            return eTitle.getTitle_ID().toString();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.close();
            Msg.append("Failed to connect to server: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String add_description(String UserID, DDescription description, StringBuilder Msg, String titleID) {
        EntityManager entityManager = null;
        try {
            try {
                entityManager = EMF.createEntityManager();
            } catch (Exception e) {
                Msg.append("Error while Entity Manager initializing");
                return null;
            }

            Transaction.begin();
            entityManager.joinTransaction();


            Query query;

            query = entityManager.createNativeQuery("Select * from description where name = ? and userid = ? and titleid = ?", EDescription.class);
            query.setParameter(1, description.getName())
                    .setParameter(2, UserID)
                    .setParameter(3, titleID);

            if (query.getResultList().size() != 0) {
                EDescription eDescription = (EDescription) query.getSingleResult();

                query = entityManager.createNativeQuery("Update description set name = ?, othername = ?, images = ?, text = ?, color = ?  where id = ?");
                query.setParameter(1,  description.getName())
                        .setParameter(2, description.getOtherName())
                        .setParameter(3, description.getImages())
                        .setParameter(4, description.getText())
                        .setParameter(5, description.getColor())
                        .setParameter(6, eDescription.getDesc_ID())
                        .executeUpdate();

                Transaction.commit();
                entityManager.close();
                return null;
            }

            query = entityManager.createNativeQuery("Insert into description (name, othername, images, text, color, userid, titleid) values (?,?,?,?,?,?,?)");
            query.setParameter(1, description.getName())
                    .setParameter(2, description.getOtherName())
                    .setParameter(3, description.getImages())
                    .setParameter(4, description.getText())
                    .setParameter(5, description.getColor())
                    .setParameter(6, UserID)
                    .setParameter(7, titleID)
                    .executeUpdate();

            Transaction.commit();
            entityManager.close();

            return null;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            entityManager.close();
            Msg.append("Failed to connect to server: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean ping(){
        try {
            EMF.createEntityManager();
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
