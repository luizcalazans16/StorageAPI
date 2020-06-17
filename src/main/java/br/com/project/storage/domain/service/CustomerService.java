package br.com.project.storage.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.storage.api.model.CustomerModel;
import br.com.project.storage.domain.model.Customer;
import br.com.project.storage.domain.repository.CustomerRepository;
import br.com.project.storage.exceptions.BusinessException;
import br.com.project.storage.exceptions.ExistingCustomerException;
import br.com.project.storage.exceptions.InactiveCustomerException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	public CustomerModel register(Customer customer) {
		if (!existingCustomer(customer.getCpf()))
			customerRepository.save(customer);
		return new CustomerModel(customer);
	}

	public Customer findCustomerByCPF(String customerCPF) {
		return customerRepository.findByCpf(customerCPF);
	}

	public CustomerModel findCustomer(String customerCPF) {
		Customer customer = customerRepository.findByCpf(customerCPF);
		if (customer == null) {
			throw new BusinessException("Inválido");
		}
		if (customer.getActive() == 0) {
			throw new InactiveCustomerException();
		}
		return new CustomerModel(customer);
	}

	public boolean existingCustomer(String customerCPF) {
		Customer existingCustomer = customerRepository.findByCpf(customerCPF);

		if (existingCustomer != null)
			throw new ExistingCustomerException();
		return false;

	}

	public List<CustomerModel> listCustomers() {
		List<CustomerModel> customersList = new ArrayList<>();
		for (Customer customer : customerRepository.findAll()) {
			if (customer.getActive() == 1)
				customersList.add(toCustomerModel(customer));
		}
		if (noResults(customersList))
			throw new BusinessException("Nenhum cliente encontrado");
		return customersList;
	}

	public CustomerModel updateCustomer(String customerCPF, Customer customer) {
		Customer customerToBeUpdated = customerRepository.findByCpf(customerCPF);
		if (customerToBeUpdated == null || customerToBeUpdated.getActive() == 0) {
			throw new BusinessException("Um cliente válido deve ser informado.");
		}
		if (customer.getCpf() != null) {
			throw new BusinessException("O CPF não pode ser alterado.");
		}
		customerRepository.save(customerToBeUpdated);
		return new CustomerModel(customerToBeUpdated);
	}

	public void inactivateCustomer(String customerCPF) {
		Customer customerToBeUpdated = customerRepository.findByCpf(customerCPF);
		if (customerToBeUpdated == null) {
			throw new BusinessException("Cliente não cadastrado no sistema");
		} else if (customerToBeUpdated.getActive() == 0) {
			throw new BusinessException("Cliente já encontra-se inativo no sistema.");
		}

		customerToBeUpdated.inactivate();
		customerRepository.save(customerToBeUpdated);

	}

	public void reactivateCustomer(String customerCPF) {
		Customer customerToBeUpdated = customerRepository.findByCpf(customerCPF);
		if (customerToBeUpdated == null) {
			throw new BusinessException("Cliente não cadastrado no sistema");
		} else if (customerToBeUpdated.getActive() == 1) {
			throw new BusinessException("Cliente já encontra-se ativo no sistema");
		}

		customerToBeUpdated.reactivate();
		customerRepository.save(customerToBeUpdated);

	}

	private CustomerModel toCustomerModel(Customer customer) {
		return modelMapper.map(customer, CustomerModel.class);
	}

	private boolean noResults(List<CustomerModel> customerModelList) {
		return customerModelList.isEmpty();
	}

}
