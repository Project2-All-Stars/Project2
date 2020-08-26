package tests;

import com.rev.dao.ExcuseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Trevor
 */
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    WebApplicationContext wac;

    @Autowired
    ExcuseRepository dao;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getAllNonGeneric(){
        try {
            mockMvc.perform(get("/room")).andExpect(status().isOk());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void getAllGeneric(){
        try {
            mockMvc.perform(get("/excuse")).andExpect(status().isOk());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

}
