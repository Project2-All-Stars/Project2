package tests.com.rev.service;


import com.rev.dao.GenericRepository;
import com.rev.model.Absence;
import com.rev.service.AbsenceController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
//	private Gson gson = new Gson().newBuilder().enableComplexMapKeySerialization().create();

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private AbsenceController absenceController;

    @InjectMocks
    private GenericRepository<Absence> repo;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testAllAbsences() throws Exception {
        MvcResult result = mockMvc.perform(get("/absences"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result);
        Assert.assertTrue(result.getResponse().getContentAsString().length() > 0);
    }

    @Test
    public void testAbsenceById() throws Exception {
        int id = 1;
        MvcResult result = mockMvc.perform(get("/absences/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertNotNull(result);
    }
}
