package com.catsjump.anakena.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.catsjump.anakena.domain.Customer;
import com.catsjump.anakena.dto.CustomerDTO;
import com.catsjump.anakena.repositories.CustomerRepository;
import com.catsjump.anakena.resources.exceptions.FieldMessage;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {
	
	@Autowired
	private HttpServletRequest request ;
//injetar o objeto HttpServeletRequest para usar a funcao que permite obter o parametro da URI - objeto padrao do JAVA EE
		
	@Autowired 
	private CustomerRepository repo;
	
	@Override
	public void initialize(CustomerUpdate ann) {
	}

	@Override
	public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {
//metodo para validar o tipo (objeto NewCustomerDTO recebido como argumento) = retornara true ou false (boolean)
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
/*estrutura de dados, colecao de pares (chave-valor)
pegando o objeto "request" chamando a funcao "getAttribute" passando o nome dos atributos "HandlerMapping"
fazendo casting "Map<String, String>" convertendo o objeto generico "URI_TEMPLATE..." para o tipo que preciso 
*/		Integer uriId = Integer.parseInt(map.get("id"));
//pegando o id do objeto que foi obtido do map acima
		
		List<FieldMessage> list = new ArrayList<>();

		Customer aux = repo.findByEmail(objDto.getEmail());
// "customer aux" recebe  "repo.findByEmail" (metodo da CustomerRepository) pegando o objeto que esta sendo inserido no banco "objDto.getEmail" 
		
		if (aux != null && !aux .getId().equals(uriId)) {
//consiste se "aux" (que eh o objDto) esta diferente nulo e se eh diferente do que esta entrando para atualizar
			list.add(new FieldMessage("email", "Email ja existente"));
//adicionando erro na lista de erros do framework			 
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
//este for eh o tratamento de erros do framework, entao a lista de erros customizada (list) eh percorrida 
//e para cada objeto da lista de erros sera adicionado um erro na lista do framework

		return list.isEmpty();
	}
}