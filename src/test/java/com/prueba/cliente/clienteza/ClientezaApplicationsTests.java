package com.prueba.cliente.clienteza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.cliente.clienteza.controller.PriceController;
import com.prueba.cliente.clienteza.service.PriceService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientezaApplicationsTests {

	private MockMvc mockMvc;

	@Mock
	private PriceService priceService;

	@Autowired
	private PriceController priceController;
	
	@BeforeEach
    public void setup() {
        // Creamos una instancia de MockMvc con el controlador mockeado
        this.mockMvc = MockMvcBuilders.standaloneSetup(priceController).build();
    }

    @Test
	void testGetPrice_Error_EndPoint_NotFound_400() throws Exception {
		// llamada
		MockHttpServletResponse response = mockMvc.perform(get("/badrequest")).andReturn().getResponse();

		// comprobamos
		assertEquals(response.getStatus(), HttpStatus.NOT_FOUND.value());
	}

	@Test
    public void testGetPrice_allPrices() throws Exception {
		mockMvc.perform(get("/api/prices/allprices"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray());
	}

	@Test
    public void testGetPriceAt_1000_D14() throws Exception {
		mockMvc.perform(get("/api/prices/price")
			.param("brandId", "1")
			.param("productId", "35455")
			.param("applicationDate", "2020-06-14-10.00.00")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").value(35455))
			.andExpect(jsonPath("$.brandId").value(1))
			.andExpect(jsonPath("$.priceList").value(1))			
			.andExpect(jsonPath("$.startDate[0]").value(2020)) 
			.andExpect(jsonPath("$.startDate[1]").value(6)) 
			.andExpect(jsonPath("$.startDate[2]").value(14)) 
			.andExpect(jsonPath("$.startDate[3]").value(0)) 
			.andExpect(jsonPath("$.startDate[4]").value(0)) 
			.andExpect(jsonPath("$.endDate[0]").value(2020))
			.andExpect(jsonPath("$.endDate[1]").value(12))
			.andExpect(jsonPath("$.endDate[2]").value(31))
			.andExpect(jsonPath("$.endDate[3]").value(23))
			.andExpect(jsonPath("$.endDate[4]").value(59));
	}

	@Test
    public void testGetPriceAt_1600_D14() throws Exception {
		mockMvc.perform(get("/api/prices/price")
			.param("brandId", "1")
			.param("productId", "35455")
			.param("applicationDate", "2020-06-14-16.00.00")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").value(35455))
			.andExpect(jsonPath("$.brandId").value(1))
			.andExpect(jsonPath("$.priceList").value(2))			
			.andExpect(jsonPath("$.startDate[0]").value(2020)) 
			.andExpect(jsonPath("$.startDate[1]").value(6)) 
			.andExpect(jsonPath("$.startDate[2]").value(14)) 
			.andExpect(jsonPath("$.startDate[3]").value(15)) 
			.andExpect(jsonPath("$.startDate[4]").value(0)) 
			.andExpect(jsonPath("$.endDate[0]").value(2020))
			.andExpect(jsonPath("$.endDate[1]").value(6))
			.andExpect(jsonPath("$.endDate[2]").value(14))
			.andExpect(jsonPath("$.endDate[3]").value(18))
			.andExpect(jsonPath("$.endDate[4]").value(30));
	}

	@Test
    public void testGetPriceAt_2100_D14() throws Exception {
		mockMvc.perform(get("/api/prices/price")
			.param("brandId", "1")
			.param("productId", "35455")
			.param("applicationDate", "2020-06-14-21.00.00")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").value(35455))
			.andExpect(jsonPath("$.brandId").value(1))
			.andExpect(jsonPath("$.priceList").value(1))			
			.andExpect(jsonPath("$.startDate[0]").value(2020)) 
			.andExpect(jsonPath("$.startDate[1]").value(6)) 
			.andExpect(jsonPath("$.startDate[2]").value(14)) 
			.andExpect(jsonPath("$.startDate[3]").value(0)) 
			.andExpect(jsonPath("$.startDate[4]").value(0)) 
			.andExpect(jsonPath("$.endDate[0]").value(2020))
			.andExpect(jsonPath("$.endDate[1]").value(12))
			.andExpect(jsonPath("$.endDate[2]").value(31))
			.andExpect(jsonPath("$.endDate[3]").value(23))
			.andExpect(jsonPath("$.endDate[4]").value(59));
	}

	@Test
    public void testGetPriceAt_1000_D15() throws Exception {
		mockMvc.perform(get("/api/prices/price")
			.param("brandId", "1")
			.param("productId", "35455")
			.param("applicationDate", "2020-06-15-10.00.00")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").value(35455))
			.andExpect(jsonPath("$.brandId").value(1))
			.andExpect(jsonPath("$.priceList").value(3))			
			.andExpect(jsonPath("$.startDate[0]").value(2020)) 
			.andExpect(jsonPath("$.startDate[1]").value(6)) 
			.andExpect(jsonPath("$.startDate[2]").value(15)) 
			.andExpect(jsonPath("$.startDate[3]").value(0)) 
			.andExpect(jsonPath("$.startDate[4]").value(0)) 
			.andExpect(jsonPath("$.endDate[0]").value(2020))
			.andExpect(jsonPath("$.endDate[1]").value(6))
			.andExpect(jsonPath("$.endDate[2]").value(15))
			.andExpect(jsonPath("$.endDate[3]").value(11))
			.andExpect(jsonPath("$.endDate[4]").value(0));
	}

	@Test
    public void testGetPriceAt_2100_D16() throws Exception {
		mockMvc.perform(get("/api/prices/price")
			.param("brandId", "1")
			.param("productId", "35455")
			.param("applicationDate", "2020-06-16-21.00.00")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").value(35455))
			.andExpect(jsonPath("$.brandId").value(1))
			.andExpect(jsonPath("$.priceList").value(4))			
			.andExpect(jsonPath("$.startDate[0]").value(2020)) 
			.andExpect(jsonPath("$.startDate[1]").value(6)) 
			.andExpect(jsonPath("$.startDate[2]").value(15)) 
			.andExpect(jsonPath("$.startDate[3]").value(16)) 
			.andExpect(jsonPath("$.startDate[4]").value(0)) 
			.andExpect(jsonPath("$.endDate[0]").value(2020))
			.andExpect(jsonPath("$.endDate[1]").value(12))
			.andExpect(jsonPath("$.endDate[2]").value(31))
			.andExpect(jsonPath("$.endDate[3]").value(23))
			.andExpect(jsonPath("$.endDate[4]").value(59));
	}
}
