package tests;

import com.rev.dao.*;
import com.rev.dao.spring.ExcuseSpringRepository;
import com.rev.dao.spring.SpringRepository;
import com.rev.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author Trevor
 */
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ExcuseRepository excuseDao;
    @Autowired
    private StudentRepository studentDao;
    @Autowired
    private TeacherRepository teacherDao;
    @Autowired
    private RoomRepository roomDao;

    @Test
    @Transactional(readOnly = true)
    public void connectionTest(){
        Session s = sessionFactory.getCurrentSession();
        Assert.assertTrue(s.isConnected());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createExcuse(){
        Session s = sessionFactory.getCurrentSession();
        s.save(new Excuse("Doctor's appointment"));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createExcuseAndGetById(){
        Serializable id = excuseDao.save(new Excuse("Funeral"));
        Assert.assertEquals("Funeral", excuseDao.findById(id).getExcuseType());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createExcuseAndUpdate(){
        Serializable id = excuseDao.save(new Excuse("Dental appointment"));
        Excuse excuseToUpdate = excuseDao.findById(id);
        excuseToUpdate.setExcuseType("Dentist appointment");
        excuseDao.update(excuseToUpdate);
        Assert.assertEquals("Dentist appointment", excuseDao.findById(id).getExcuseType());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createExcuseAndDelete(){
        Serializable id = excuseDao.save(new Excuse("Slept in"));
        excuseDao.deleteById(id);
        Assert.assertEquals(null, excuseDao.findById(id));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void EnsureNonExistentIDErrorHandled(){
        //create and delete entity to ensure id is empty
        Serializable id = excuseDao.save(new Excuse("Slept in"));
        Excuse excuseToUpdate = excuseDao.findById(id);
        excuseToUpdate.setExcuseType("Sick");
        excuseDao.deleteById(id);

        Assert.assertEquals(null, excuseDao.findById(id));
        Assert.assertFalse(excuseDao.update(excuseToUpdate));
        Assert.assertFalse(excuseDao.deleteById(id));
    }

    /*
    Commenting this test out and leaving it here because all though this will work in practice
    it wont work in this test and its important to know why. The objects being saved are being
    cached in the persistent state and thus their foreign key values would not get populated
     */
    //tests foreign keys, oneToOne, and oneToMany
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void CreateStudentsTeacherRoomAndGetNumStudents(){
//        Room room = new Room("Chem lab");
//        Serializable rid = roomDao.save(room);
//        Teacher teacher = new Teacher("Tea", "Cher", roomDao.findById(rid));
//        Serializable tid = teacherDao.save(teacher);
//        Student student1 = new Student("John", "Doe", teacherDao.findById(tid), 9);
//        Student student2 = new Student("Jane", "Doe", teacherDao.findById(tid), 10);
//        studentDao.save(student1);
//        studentDao.save(student2);
//
//        Assert.assertNotNull(teacherDao.findById(tid).getStudents());
//        Assert.assertEquals(2, roomDao.findById(rid).getNumSeats());
//    }
}
