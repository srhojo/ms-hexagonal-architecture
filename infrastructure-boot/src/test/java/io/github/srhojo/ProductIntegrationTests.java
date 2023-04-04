package io.github.srhojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.srhojo.rest.apis.domain.ProductRequest;
import io.github.srhojo.rest.apis.domain.ProductResponse;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductIntegrationTests {
    JacksonTester<ProductRequest> jsonProductRequest;
    JacksonTester<ProductResponse> jsonProductResponse;
    @Autowired
    WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void verifyTestConfiguration() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertNotNull(webApplicationContext.getBean("productsController"));
    }

    @Test
    public void retrieveAllProducts() throws Exception {
        final MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/apis/v1/products"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content(). jsonPath("$.message").value("Hello World!!!"))
                .andReturn();

        assertEquals(APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

    }

    @Test
    public void addProduct() throws Exception {
        //Given
        ProductRequest request = new ProductRequest();
        request.setName("Test");
        request.setPrice(10.2);

        //When
        MockHttpServletResponse response = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/apis/v1/products")
                .contentType(APPLICATION_JSON_VALUE)
                .content(jsonProductRequest.write(request).getJson())
        ).andReturn().getResponse();

        // Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertNotNull(response.getContentAsString());
        ProductResponse responseBody = jsonProductResponse.parseObject(response.getContentAsString());
        assertNotNull(responseBody.getId());
        assertEquals(request.getName(), responseBody.getName());
        assertEquals(request.getPrice(), responseBody.getPrice());

    }
}