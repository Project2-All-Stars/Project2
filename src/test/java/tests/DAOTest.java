package tests;

import com.rev.dao.*;
import com.rev.dao.spring.ExcuseSpringRepository;
import com.rev.dao.spring.SpringRepository;
import com.rev.model.Absence;
import com.rev.model.Excuse;
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

    @Test
    @Transactional
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
}
