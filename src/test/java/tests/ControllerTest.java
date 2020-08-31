package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rev.dao.ExcuseRepository;
import com.rev.model.Excuse;
import com.rev.model.Room;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    private final ObjectMapper om = new ObjectMapper();

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getAll(){
        try {
            mockMvc.perform(get("/excuse/get")).andExpect(status().isOk());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void postGet(){
        try {
            //post
            MvcResult result = mockMvc.perform(post("/excuse/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(new Excuse("Field Trip"))))
                    .andExpect(status().isCreated())
                    .andReturn();

            String id = result.getResponse().getContentAsString();

            //get
            mockMvc.perform(get("/excuse/get/"+id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void postPutGet(){
        try {
            //post
            MvcResult result = mockMvc.perform(post("/excuse/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(new Excuse("Deceased"))))
                    .andExpect(status().isCreated())
                    .andReturn();

            //put
            String id = result.getResponse().getContentAsString();
            Excuse updatedExcuse = new Excuse("Diseased");
            updatedExcuse.setId(Integer.parseInt(id));

            mockMvc.perform(put("/excuse/put")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(updatedExcuse)))
                    .andExpect(status().isNoContent());

            //get
            mockMvc.perform(get("/excuse/get/"+id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                    .andExpect(content().json("{'excuseType':'Diseased'}"));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void postDelete(){
        try {
            //post
            MvcResult result = mockMvc.perform(post("/excuse/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(new Excuse("qwerty"))))
                    .andExpect(status().isCreated())
                    .andReturn();

            String id = result.getResponse().getContentAsString();

            //delete
            mockMvc.perform(delete("/excuse/delete/"+id))
                    .andExpect(status().isNoContent());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void postDeleteAndTryWithId(){
        try {
            //make sure id slot is empty by creating then deleting
            MvcResult result = mockMvc.perform(post("/excuse/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(new Excuse("asdfg"))))
                    .andExpect(status().isCreated())
                    .andReturn();

            String id = result.getResponse().getContentAsString();

            mockMvc.perform(delete("/excuse/delete/"+id))
                    .andExpect(status().isNoContent());

            //fail get
            mockMvc.perform(get("/excuse/get/"+id))
                    .andExpect(status().isInternalServerError());

            //fail put
            Excuse updatedExcuse = new Excuse("zxcvb");
            updatedExcuse.setId(Integer.parseInt(id));

            String test = om.writeValueAsString(updatedExcuse);

            try{
                mockMvc.perform(put("/excuse/put")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updatedExcuse)));
                Assert.fail("Exception not thrown for missing id");
            }
            catch(Exception e){
                //were supposed to hit this
            }

            //fail delete
            mockMvc.perform(delete("/excuse/delete/"+id))
                    .andExpect(status().isInternalServerError());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    //tests @JsonIgnore
    @Test
    public void postGetRoom(){
        try {
            //post
            MvcResult result = mockMvc.perform(post("/room/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsString(new Room("Computer lab"))))
                    .andExpect(status().isCreated())
                    .andReturn();

            String id = result.getResponse().getContentAsString();

            //get
            mockMvc.perform(get("/room/get/"+id))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

}
