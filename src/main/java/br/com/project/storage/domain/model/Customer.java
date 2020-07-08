package br.com.project.storage.domain.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.project.storage.exceptions.InvalidFormatException;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@Id
	private String cpf;

	private String name;

	@Email
	private String email;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "birth_Date")
	private Calendar birthDate;

	private String phone;

	@Enumerated(EnumType.STRING)
	private CustomerGenderEnum gender;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<CustomerAddresses> addresses;

	private Byte active = 1;

	@Deprecated
	public Customer() {

	}

	public Customer(String cpf, String name, String email, Calendar birthDate, CustomerGenderEnum gender,
			List<CustomerAddresses> addresses, Byte active) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.addresses = addresses;
		this.gender = gender;
		this.active = active;
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

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public void setBirthDate(String birthDate) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(birthDate));
			String validationDate = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
			if (!validationDate.matches(birthDate)) {
				throw new InvalidFormatException("Formato inválido! Deve-se utilizar o padrão \"dd/MM/yyyy\".");
			}
			this.birthDate = calendar;
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public List<CustomerAddresses> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<CustomerAddresses> addresses) {
		this.addresses = addresses;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

	public void inactivate() {
		setActive((byte) 0);
	}

	public void reactivate() {
		setActive((byte) 1);
	}

}
