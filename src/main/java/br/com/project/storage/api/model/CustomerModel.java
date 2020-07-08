package br.com.project.storage.api.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.project.storage.domain.model.Customer;
import br.com.project.storage.domain.model.CustomerAddresses;
import br.com.project.storage.domain.model.CustomerGenderEnum;

public class CustomerModel {

	private String cpf;

	private String name;

	private String email;

	private String phone;
	@Enumerated(EnumType.STRING)
	private CustomerGenderEnum gender;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Calendar birthDate;

	private List<CustomerAddresses> addresses;

	public CustomerModel(Customer customer) {
		setCpf(customer.getCpf());
		setName(customer.getName());
		setEmail(customer.getEmail());
		setPhone(customer.getPhone());
		setGender(customer.getGender());
		setAddresses(customer.getAddresses());
		setBirthDate(customer.getBirthDate());
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CustomerGenderEnum getGender() {
		return gender;
	}

	public void setGender(CustomerGenderEnum gender) {
		this.gender = gender;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public List<CustomerAddresses> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CustomerAddresses> addresses) {
		this.addresses = addresses;
	}

}
