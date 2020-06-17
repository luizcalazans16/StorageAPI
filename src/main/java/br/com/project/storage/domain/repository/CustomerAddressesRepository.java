package br.com.project.storage.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.storage.domain.model.CustomerAddresses;

@Repository
public interface CustomerAddressesRepository extends JpaRepository<CustomerAddresses, Long> {

	List<CustomerAddresses> findByCustomerCpf(String customerCPF);
}
