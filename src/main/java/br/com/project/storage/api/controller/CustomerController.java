package br.com.project.storage.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.storage.api.model.CustomerModel;
import br.com.project.storage.domain.model.Customer;
import br.com.project.storage.domain.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

//	@Autowired
//	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<?> listCustomers() {
		List<CustomerModel> customersList = customerService.listCustomers();
		return new ResponseEntity<Iterable<CustomerModel>>(customersList, HttpStatus.OK);
	}

	@GetMapping("/findByCpf/{customerCPF}")
	public ResponseEntity<CustomerModel> findCustomerByCPF(@PathVariable String customerCPF) {
		CustomerModel foundCustomer = customerService.findCustomer(customerCPF);
		return new ResponseEntity<CustomerModel>(foundCustomer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody Customer customer) {
		CustomerModel registeredCustomer = customerService.register(customer);
		return new ResponseEntity<CustomerModel>(registeredCustomer, HttpStatus.CREATED);
	}

	@PutMapping("/reactivate/{customerCPF}")
	public ResponseEntity<Void> reactivateCustomer(@PathVariable String customerCPF) {
		customerService.reactivateCustomer(customerCPF);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/inactivate/{customerCPF}")
	public ResponseEntity<Void> inactivateCustomer(@PathVariable String customerCPF) {
		customerService.inactivateCustomer(customerCPF);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
