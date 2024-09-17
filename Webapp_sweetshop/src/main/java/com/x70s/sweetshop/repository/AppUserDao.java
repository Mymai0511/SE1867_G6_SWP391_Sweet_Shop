package com.x70s.sweetshop.repository;

import com.x70s.sweetshop.entity.AppUser;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class AppUserDao extends OcosGenericDao <AppUser, Long> {

//    public List<AppUser> viewStaff(Long courseId) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            String hql = "SELECT s FROM app_user s JOIN s.courses c WHERE c.id = :courseId";
//            Query<Student> query = session.createQuery(hql, Student.class);
//            query.setParameter("courseId", courseId);
//            List<Student> students = query.list();
//            session.getTransaction().commit();
//            return students;
//        } catch (HibernateException e) {
//            throw new HibernateException("Error finding students by course ID", e);
//        }
//    }
}
