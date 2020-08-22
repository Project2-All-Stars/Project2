package tests;

import com.rev.dao.*;
import com.rev.model.Absence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DAOTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    public void connectionTest(){
        Session s = sessionFactory.getCurrentSession();
        Assert.assertTrue(s.isConnected());
    }
}
