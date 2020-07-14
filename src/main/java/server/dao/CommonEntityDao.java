package server.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.config.DatabaseConfig;

import java.util.ArrayList;

public class CommonEntityDao<Bean> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final Class<Bean> typeParameterClass;

    public CommonEntityDao(Class<Bean> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public void delete(int id) {
        try {
            Session session = DatabaseConfig.getSessionFactory().openSession();
            session.beginTransaction();
            Bean del = session.get(typeParameterClass, id);
            session.delete(del);
            session.getTransaction().commit();
            if (session.isOpen()) session.close();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public ArrayList<Bean> getAll() {
        try {
            Session session = DatabaseConfig.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = String.format("form %s", typeParameterClass.getCanonicalName());
            Query SQLQuery = session.createQuery(hql);
            ArrayList<Bean> result = (ArrayList<Bean>) SQLQuery.list();
            session.getTransaction().commit();
            if (session.isOpen()) session.close();
            return result;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public Bean getById(int id) {
        try {
            Session session = DatabaseConfig.getSessionFactory().openSession();
            session.beginTransaction();
            Bean result = session.get(typeParameterClass, id);
            session.getTransaction().commit();
            if (session.isOpen()) session.close();
            return result;
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public void update(Bean object) {
        try {
            Session session = DatabaseConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void add(Bean object) {
        try {
            Session session = DatabaseConfig.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void clear() {
        try {
            Session session = DatabaseConfig.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = String.format("delete from %s", typeParameterClass.getCanonicalName());
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
            if (session.isOpen()) {
                session.close();
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
