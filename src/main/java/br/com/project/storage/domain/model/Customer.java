package br.com.project.storage.domain.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.project.storage.exceptions.InvalidFormatException;

@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	@Id
	private String cpf;

	private String name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "birth_Date")
	private Calendar birthDate;

	private String phone;

	@Enumerated(EnumType.STRING)
	private CustomerGenderEnum gender;

	private Byte active = 1;
	
	@Deprecated
	public Customer() {
		
	}

	public Customer(String cpf, String name, Calendar birthDate, CustomerGenderEnum gender,
			Byte active) {
		super();	
		this.cpf = cpf;
		this.name = name;
		this.birthDate = birthDate;
		
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
