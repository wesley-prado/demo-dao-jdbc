package model.entities;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String email;
	private Date birthDate;
	private double baseSalary;

	private Department department;

	public Seller() {
	}

	public Seller(
			Integer id,
			String name,
			String email,
			Date birthDate,
			double baseSalary,
			Department department
	) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public Integer getId() {
		return id;
	}

	public Seller setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Seller setName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Seller setEmail(String email) {
		this.email = email;
		return this;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Seller setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public Seller setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
		return this;
	}

	public Department getDepartment() {
		return department;
	}

	public Seller setDepartment(Department department) {
		this.department = department;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) return true;
		if ( o == null || getClass() != o.getClass() ) return false;
		Seller seller = (Seller) o;
		return Objects.equals( getId(), seller.getId() );
	}

	@Override
	public int hashCode() {
		return Objects.hash( getId() );
	}

	@Override
	public String toString() {
		return "Seller{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", birthDate=" + birthDate +
				", baseSalary=" + baseSalary +
				", department=" + department +
				'}';
	}
}
