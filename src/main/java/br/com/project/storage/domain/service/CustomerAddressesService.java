package br.com.project.storage.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.storage.domain.model.Customer;
import br.com.project.storage.domain.model.CustomerAddresses;
import br.com.project.storage.domain.repository.CustomerAddressesRepository;
import br.com.project.storage.domain.repository.CustomerRepository;
import br.com.project.storage.exceptions.BusinessException;

@Service
public class CustomerAddressesService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerAddressesRepository addressesRepository;

	public List<CustomerAddresses> listAddresses(String customerCPF) {
		List<CustomerAddresses> addressesList = new ArrayList<>();
		for (CustomerAddresses customerAddresses : addressesRepository.findByCustomerCpf(customerCPF)) {
			if (customerAddresses.getCustomer().getActive() == 1)
				addressesList.add(customerAddresses);
		}
		return addressesList;
	}

	public CustomerAddresses createAddress(String customerCPF, CustomerAddresses customerAddresses) {
		Customer customer = customerRepository.findByCpf(customerCPF);
		if (customer == null)
			throw new BusinessException("Cliente não encontrado.");

		customerAddresses.setCustomer(customer);
		return addressesRepository.save(customerAddresses);
	}

	public void changeMainAddresses(Long addressId) {
		Optional<CustomerAddresses> foundCustomerAddresses = addressesRepository.findById(addressId);
		if (!foundCustomerAddresses.isPresent())
			throw new BusinessException("Endereço não encontrado");

		CustomerAddresses customerAddresses = foundCustomerAddresses.get();
		if (customerAddresses.getMainAddress() == 1) {
			throw new BusinessException("Este endereço já está definido como principal para o cliente: "
					+ customerAddresses.getCustomer().getName());
		} else {
			customerAddresses.defineAsMainAddress();
			addressesRepository.save(customerAddresses);
		}
	}
}
