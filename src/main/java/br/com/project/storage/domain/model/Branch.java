package br.com.project.storage.domain.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Branch {

	@Id
	@Size(max = 7)
	@Size(min = 7)
	private String cnae;

	private String name;

	private OffsetDateTime updateAt;

	private Byte active;

	@OneToMany
	private List<Supplier> supplierList;

	public Branch(String name, OffsetDateTime updateAt, Byte active) {
		this.name = name;
		this.updateAt = updateAt;
		this.active = active;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OffsetDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(OffsetDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public Byte getActive() {
		return active;
	}

	public void setActive(Byte active) {
		this.active = active;
	}

}
