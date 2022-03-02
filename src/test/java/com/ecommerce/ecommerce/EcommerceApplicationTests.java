package com.ecommerce.ecommerce;

import com.ecommerce.ecommerce.model.AuthenticationToken;
import com.ecommerce.ecommerce.model.Category;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.AuthenticationTokenRepo;
import com.ecommerce.ecommerce.repository.CategoryRepo;
import com.ecommerce.ecommerce.repository.UserRepo;
import com.ecommerce.ecommerce.repository.WishlistRepo;
import com.ecommerce.ecommerce.service.AuthenticationTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
class EcommerceApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	AuthenticationTokenRepo authenticationTokenRepo;

	private User user = new User("abdulazeez", "alsharifi", "qq@gmail.com", "root");
	private Category category = new Category("Men", "Shop men products", "image.jpg");
	private AuthenticationToken authenticationToken = new AuthenticationToken(user);


	@Test
	public void saveUserTest(){
		userRepo.save(user);
		Assertions.assertNotNull(userRepo.findByEmail(user.getEmail()));
		userRepo.deleteById(user.getId());
	}

	@Test
	public void DeleteUserTest(){
		userRepo.save(user);
		userRepo.deleteById(user.getId());
		assertThat(userRepo.findById(user.getId())).isEmpty();
	}

	@Test
	public void saveCategoryTest(){
		categoryRepo.save(category);
		Assertions.assertNotNull(categoryRepo.getById(category.getId()));
		categoryRepo.deleteById(category.getId());
	}

	@Test
	public void DeleteCategoryTest(){
		categoryRepo.save(category);
		categoryRepo.deleteById(category.getId());
		assertThat(categoryRepo.findById(category.getId())).isEmpty();
	}

	@Test
	public void TokenTest(){
		Assertions.assertNull(authenticationTokenRepo.findByToken(authenticationToken.getToken()));
	}


}
