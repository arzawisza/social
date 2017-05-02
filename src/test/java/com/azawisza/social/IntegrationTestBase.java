package com.azawisza.social;

import com.azawisza.Application;
import com.azawisza.social.domain.user.CurrentDateSupplier;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public abstract class IntegrationTestBase {

    @Autowired
    protected WebApplicationContext context;
    protected MockMvc mockMvc;

    protected Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @MockBean
    public CurrentDateSupplier dateSupplier;

    protected <V> String json(V value) {
        return gson.toJson(value);
    }

}
