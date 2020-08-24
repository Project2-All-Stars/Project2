package tests.com.rev.service;


import com.rev.dao.GenericRepository;
import com.rev.model.Absence;
import com.rev.service.AbsenceController;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
//@PropertySource("classpath:/WEB-INF/application.properties")
@WebAppConfiguration
public class AbsenceControllerTest {

    @Autowired
    WebApplicationContext wac;

//    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AbsenceController absenceController;

    @InjectMocks
    private GenericRepository<Absence> repo;

//    private List<Absence> absenceList;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

//    @BeforeEach
//    public void setupList(){
//        this.absenceList  = new ArrayList<Absence>();
//        absenceList.add(new Absence(1, new Date(System.currentTimeMillis()), 1));
//        absenceList.add(new Absence(2, new Date(System.currentTimeMillis()), 2));
//        absenceList.add(new Absence(3,new Date(System.currentTimeMillis()), 1));
//    }

    @Test
    public void testAllAbsences() throws Exception {
//        given(repo.findAll()).willReturn(absenceList);
        MvcResult result = mockMvc.perform(get("/absences"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getResponse().getContentAsString().length() > 0);
//        Assert.assertTrue(true);
    }
}
