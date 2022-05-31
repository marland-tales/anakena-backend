package com.catsjump.anakena.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.catsjump.anakena.domain.SlipPayment;

@Service
public class SlipService {

	public void setSlipPayment(SlipPayment payment, Date CustomerOrderInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(CustomerOrderInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setDueDate(cal.getTime());
	}
}