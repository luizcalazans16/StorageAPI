package br.com.project.storage.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.storage.domain.model.CustomerAddresses;
import br.com.project.storage.domain.service.CustomerAddressesService;

@RestController
@RequestMapping("/customer-addresses")
public class CustomerAddressesController {

	@Autowired
	private CustomerAddressesService addressesService;

	@GetMapping("/{customerCPF}/addresses")
	public ResponseEntity<?> listAddresses(@PathVariable String customerCPF){
		List<CustomerAddresses> addressesList = addressesService.listAddresses(customerCPF);
		return new ResponseEntity<Iterable<CustomerAddresses>>(addressesList, HttpStatus.OK);
	}
	
	@PostMapping("/{customerCPF}")
	public CustomerAddresses addAddress(@PathVariable String customerCPF, @RequestBody CustomerAddresses customerAddress) {
		return addressesService.createAddress(customerCPF, customerAddress);
	}
	

	@PutMapping("/mainAddress/{addressId}")
	public ResponseEntity<?> changeMainAddress(@PathVariable Long addressId){
		addressesService.changeMainAddresses(addressId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
}
