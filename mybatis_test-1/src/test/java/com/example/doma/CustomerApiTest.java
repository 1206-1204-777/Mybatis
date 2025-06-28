package com.example.doma;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBootTest(classes = MybatisTest1Application.class)
@AutoConfigureMockMvc
@TestExecutionListeners({
    DbUnitTestExecutionListener.class
})
@DbUnitConfiguration(dataSetLoader = XlsDataSetLoader.class)
public class CustomerApiTest {

	@BeforeEach
	void setup(WebApplicationContext context) {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("/customers.xlsx")
    void 名前で検索しアドレスと電話番号が正しいことを確認する() throws Exception {
        mockMvc.perform(get("/api/customers/search").param("name", "a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name=='taro')].address").value("japan"))
                .andExpect(jsonPath("$[?(@.name=='hanako')].phone").value("000-0002-000"));
    }
}
