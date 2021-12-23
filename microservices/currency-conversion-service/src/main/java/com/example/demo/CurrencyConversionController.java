package com.example.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.CurrencyConversionBean;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy serviceProxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversionBean> reponse = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);
		CurrencyConversionBean conversionBean = reponse.getBody();
		conversionBean.setQuantity(quantity);
		conversionBean.setValue(quantity.multiply(conversionBean.getConversionMultiple()));
		return conversionBean;
	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	private CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean conversionBean = serviceProxy.retrieveExchangeValue(from, to);
		conversionBean.setQuantity(quantity);
		conversionBean.setValue(quantity.multiply(conversionBean.getConversionMultiple()));
		return conversionBean;
	}
}
