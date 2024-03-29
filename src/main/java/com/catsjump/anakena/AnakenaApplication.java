package com.catsjump.anakena;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.catsjump.anakena.domain.Address;
import com.catsjump.anakena.domain.CardPayment;
import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.domain.City;
import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.domain.CustomerOrder;
import com.catsjump.anakena.domain.OrderItem;
import com.catsjump.anakena.domain.Payment;
import com.catsjump.anakena.domain.Product;
import com.catsjump.anakena.domain.SlipPayment;
import com.catsjump.anakena.domain.State;
import com.catsjump.anakena.domain.enums.CustomerType;
import com.catsjump.anakena.domain.enums.PaymentStatus;
import com.catsjump.anakena.repositories.AddressRepository;
import com.catsjump.anakena.repositories.CategoryRepository;
import com.catsjump.anakena.repositories.CityRepository;
import com.catsjump.anakena.repositories.CustomerOrderRepository;
import com.catsjump.anakena.repositories.CustomerRepository;
import com.catsjump.anakena.repositories.OrderItemRepository;
import com.catsjump.anakena.repositories.PaymentRepository;
import com.catsjump.anakena.repositories.ProductRepository;
import com.catsjump.anakena.repositories.StateRepository;

@SpringBootApplication
public class AnakenaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(AnakenaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//instanciando categorias
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama, mesa e banho");
		Category cat4 = new Category(null, "Esportes");
		Category cat5 = new Category(null, "Eletronicos");
		Category cat6 = new Category(null, "Decorcacao");
		Category cat7 = new Category(null, "Jardinagem");
		Category cat8 = new Category(null, "Perfumaria");

//instanciando products
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "Toalha", 50.00);
		Product p6 = new Product(null, "Colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);
		

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));	

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

//instanciando State
		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

//instanciando City		
		City c1 = new City(null, "Uberlandia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinhas", est2);

		est1.getCitys().addAll(Arrays.asList(c1));
		est2.getCitys().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Customer cli1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", CustomerType.PESSOAFISICA);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

//instanciando Address
		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		customerRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");

		CustomerOrder ped1 = new CustomerOrder(null, sdf.parse("21/12/2021 10:32"), cli1, e1);
		CustomerOrder ped2 = new CustomerOrder(null, sdf.parse("23/12/2021 19:35"), cli1, e2);

		Payment pagto1 = new CardPayment(null, PaymentStatus.QUITADO, ped1, 6);
//payment do ped1 eh o pagto1:
		ped1.setPayment(pagto1);

		Payment pagto2 = new SlipPayment(null, PaymentStatus.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getCustomerOrder().addAll(Arrays.asList(ped1, ped2));

		customerOrderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
//

		OrderItem ip1 = new OrderItem(ped1, p1, 0.00, 1, 2000.00);
		OrderItem ip2 = new OrderItem(ped1, p3, 0.00, 2, 80.00);
		OrderItem ip3 = new OrderItem(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}