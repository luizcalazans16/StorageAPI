package br.com.project.storage.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CNPJ
	private String cpnj;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	private OffsetDateTime updatedAt;
	
	private Byte active;
	
	public Supplier() {
		
	}

	public Supplier(String cpnj, String name, Branch branch, OffsetDateTime updatedAt, Byte active) {
		
		this.cpnj = cpnj;
		this.name = name;
		this.branch = branch;
		this.updatedAt = updatedAt;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpnj() {
		return cpnj;
	}

	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}
	
	
	
}
