package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.*;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanExample myBeanExample;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;

	public FundamentosApplication(@Qualifier("componenetToImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanExample myBeanExample, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanExample = myBeanExample;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {

		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();

	}

	private void getInformationJpqlFromUser(){
		LOGGER.info("Usuario con el metodo findByUserEmail" +
				userRepository.findByUserEmail("jhon@domain.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));

		userRepository.findAndSort("user", Sort.by("id").descending()).
				stream().
				forEach(user-> LOGGER.info( "Usuario con metodo Sort" + user));
	}

	private void saveUsersInDataBase() {
		User user1 = new User("Jhon", "jhon@domain.com", LocalDate.of(2021, 03, 21));
		User user2 = new User("Maria", "maria@domain.com", LocalDate.of(2021, 05, 27));
		User user3 = new User("Sol", "sol@domain.com", LocalDate.of(2021, 06, 15));
		User user4 = new User("Juan", "juan@domain.com", LocalDate.of(2021, 05, 11));
		User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 8, 07));
		User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 01, 9));
		User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 02, 18));
		User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 07, 14));
		User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 10, 25));
		User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 9, 18));
		User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021, 11, 03));
		User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021, 12, 22));
		List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);
		list.stream().forEach(userRepository::save);
	}
	private void ejemplosAnteriores()  {
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printhWithDependency();
		myBeanExample.printBeanExample();
		System.out.println(myBeanWithProperties.funtion());
		System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
		System.out.println(userPojo.getEmail()+ "-" + userPojo.getAge());

		try {
			int value=10/0;
			LOGGER.debug("Mi valor:"+value);
		}
		catch (Exception e){
			LOGGER.error("Esto es un error al dividir por cero" +e.getMessage());
		}
	}
}
