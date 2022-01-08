package com.raven.demo;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;
import com.raven.entity.Review;
import com.raven.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(">>>>>>> Welcome Many-To-Many - Create Course And Students Demo!!! <<<<<<<<");
        SessionFactory sessionFactory = null;
        Session session = null;

        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
                    .addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            // create course
            Course course = new Course("Pacman - How to score one million point.");

            // add review
            course.addReview(new Review("Greate course...loved it!"));
            course.addReview(new Review("Cool course, job well done!"));
            course.addReview(new Review("What a dumb course, it can be better."));

            // save course
            // this will also save all the reviews
            session.save(course);

            // create student
            Student student1 = new Student("John", "Doe", "johm@gmail.com");
            Student student2 = new Student("Mary", "Public", "mary@gmail.com");

            // add students to the course
            course.addStudent(student1);
            course.addStudent(student2);

            // save the student
            session.save(student1);
            session.save(student2);

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
