package br.com.project.storage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.storage.domain.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String>{

	Customer findByCpf(String cpf);
}
