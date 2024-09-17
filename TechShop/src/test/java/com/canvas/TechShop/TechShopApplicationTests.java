package com.canvas.TechShop;

import com.canvas.TechShop.models.Product;
import com.canvas.TechShop.models.Type;
import com.canvas.TechShop.repositories.ProductRepository;
import com.canvas.TechShop.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.List;

@SpringBootTest
class TechShopApplicationTests {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	@Test
	void contextLoads() {
	}

	@Test
	void addProductTest(){
		Product product = new Product();
		product.setModel("S24 Ultra");
		product.setBrand("Samsung");
		product.setPrice(550000);
		product.setAmount(54);
		product.setColor("grey");
		product.setCharacteristic("bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla");
		product.setDescription("bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla bla");
		Type type = new Type();
		type.setId(1L);
		product.setType(type);
		Product initialProduct = productService.addProduct(product,1L);
		List<Product> products = productRepository.findAll();
		Product productFinal = products.get(0);
		Assertions.assertEquals(initialProduct.getId(),productFinal.getId());
		Assertions.assertEquals(initialProduct.getModel(),productFinal.getModel());
		Assertions.assertEquals(initialProduct.getBrand(),productFinal.getBrand());
	}

}
