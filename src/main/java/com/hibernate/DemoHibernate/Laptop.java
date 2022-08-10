package com.hibernate.DemoHibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "laptops" )
@Getter
@Setter
public class Laptop {

	@Id
	private int laptopId;
	private String laptopName;
	@ManyToMany
	private List<Alien> alien = new ArrayList<Alien>();
	@Override
	public String toString() {
		return "Laptop [laptopId=" + laptopId + ", laptopName=" + laptopName +  "]";
	}
	
	
}
