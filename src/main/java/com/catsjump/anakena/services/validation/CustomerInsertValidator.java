package com.catsjump.anakena.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.catsjump.anakena.domain.enums.CustomerType;
import com.catsjump.anakena.dto.NewCustomerDTO;
import com.catsjump.anakena.resources.exceptions.FieldMessage;
import com.catsjump.anakena.services.validation.utils.BR;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, NewCustomerDTO> {
	@Override
	public void initialize(CustomerInsert ann) {
	}

	@Override
	public boolean isValid(NewCustomerDTO objDto, ConstraintValidatorContext context) {
//metodo para validar o tipo (objeto NewCustomerDTO recebido como argumento) = retornara true ou false (boolean)
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getTipo() == null) {
			list.add(new FieldMessage("tipo", "Tipo não pode ser nulo"));
		}	
		
		if (objDto.getTipo().equals(CustomerType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}	
		
		if (objDto.getTipo().equals(CustomerType.PESSOAJURIDICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}	

//lista de validacoes
			
			
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