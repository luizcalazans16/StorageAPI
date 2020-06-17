package br.com.project.storage.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CustomerAddresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	private String addressLine;

	private Integer building;

	private Integer complement;

	private String postalCode;

	private String district;

	private String city;

	private String stateProvince;

	private String country;

	@ManyToOne
	@JoinColumn(name = "customer_cpf")
	@JsonIgnore
	private Customer customer;

	private byte mainAddress = 0;

	public CustomerAddresses() {

	}

	public CustomerAddresses(String addressLine, Integer building, Integer complement, String postalCode,
			String district, String city, String stateProvince, String country) {

		this.addressLine = addressLine;
		this.building = building;
		this.complement = complement;
		this.postalCode = postalCode;
		this.district = district;
		this.city = city;
		this.stateProvince = stateProvince;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getComplement() {
		return complement;
	}

	public void setComplement(Integer complement) {
		this.complement = complement;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public byte getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(byte mainAddress) {
		this.mainAddress = mainAddress;
	}
	
	public void defineAsMainAddress() {
		setMainAddress((byte) 1);
	}

}
