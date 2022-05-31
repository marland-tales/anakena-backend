package com.catsjump.anakena.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catsjump.anakena.domain.CustomerOrder;
import com.catsjump.anakena.domain.OrderItem;
import com.catsjump.anakena.domain.SlipPayment;
import com.catsjump.anakena.domain.enums.PaymentStatus;
import com.catsjump.anakena.repositories.CustomerOrderRepository;
import com.catsjump.anakena.repositories.OrderItemRepository;
import com.catsjump.anakena.repositories.PaymentRepository;
import com.catsjump.anakena.services.exceptions.ObjectNotFoundException;

@Service
public class CustomerOrderService {

	@Autowired
//anotacao para instanciar automaticamente as instancias declaradas, por injecao de dependencia ou inversao de controle
	private CustomerOrderRepository repo;

	@Autowired
	private SlipService slipService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ProductService productService;
	
	public CustomerOrder find(Integer id) {
		Optional<CustomerOrder> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + CustomerOrder.class.getName()));
	}

	@Transactional
	public CustomerOrder insert(CustomerOrder obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus (PaymentStatus.PENDENTE);
		obj.getPayment().setCustomerOrder(obj);
		if (obj.getPayment() instanceof SlipPayment) {
			SlipPayment payment = (SlipPayment) obj.getPayment();
			slipService.setSlipPayment(payment, obj.getInstant());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi : obj.getItens()) {
			oi.setDiscount(0.0);
//			oi.setPrice(oi.getProduct().getPrice());
			oi.setPrice(productService.find(oi.getProduct().getId()).getPrice());
			oi.setCustomerOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItens());
		return obj;
	}
}