package com.raven.demo;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;
import com.raven.entity.Review;
import com.raven.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) throws Exception {
        System.out.println(">>>>>>> Welcome Many-To-Many - Delete Student Demo!!! <<<<<<<<");
        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                    .addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // get a course
            int id = 2;
            Student student = session.get(Student.class, id);
            System.out.println("Student Details :: " + student);

            // delete the course
            session.delete(student);

            session.getTransaction().commit();
            System.out.println(">>> Done <<<");
        } catch (Exception exception) {
            // exception.printStackTrace();
            System.out.println(">>> ERROR >>> " + exception.getLocalizedMessage());
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
