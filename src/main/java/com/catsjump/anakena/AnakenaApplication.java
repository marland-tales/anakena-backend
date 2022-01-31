package com.catsjump.anakena;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.catsjump.anakena.domain.Category;
import com.catsjump.anakena.domain.City;
import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.domain.Endereco;
import com.catsjump.anakena.domain.Estado;
import com.catsjump.anakena.domain.Pagamento;
import com.catsjump.anakena.domain.PagamentoComBoleto;
import com.catsjump.anakena.domain.PagamentoComCartao;
import com.catsjump.anakena.domain.Pedido;
import com.catsjump.anakena.domain.Product;
import com.catsjump.anakena.domain.enums.EstadoPagamento;
import com.catsjump.anakena.domain.enums.TipoCliente;
import com.catsjump.anakena.repositories.CategoryRepository;
import com.catsjump.anakena.repositories.CityRepository;
import com.catsjump.anakena.repositories.CustomerRepository;
import com.catsjump.anakena.repositories.EnderecoRepository;
import com.catsjump.anakena.repositories.EstadoRepository;
import com.catsjump.anakena.repositories.PagamentoRepository;
import com.catsjump.anakena.repositories.PedidoRepository;
import com.catsjump.anakena.repositories.ProductRepository;

@SpringBootApplication
public class AnakenaApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CustomerRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AnakenaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//instanciando categorias
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
	
//instanciando products
		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

//instanciando Estado
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
//instanciando City		
		City c1 = new City(null, "Uberlandia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinhas", est2);
		
		est1.getCitys().addAll(Arrays.asList(c1));
		est2.getCitys().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Customer cli1 = new Customer(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

//instanciando Endereco
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("21/12/2021 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("23/12/2021 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
//pagamento do ped1 eh o pagto1:
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
//
	}
}