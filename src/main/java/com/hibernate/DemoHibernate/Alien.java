package com.hibernate.DemoHibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "aliens" )

@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Alien implements Serializable{

	@Id
	@Column(name="alienId", nullable = false)
	private int alienId;
	@Column(name="alienName", length= 50)
	private AlienName alienName;
	@Column(name="alienColor", length = 30)
	private String color;
	
	
	@ManyToMany(mappedBy="alien")
	private List <Laptop> laptop = new ArrayList<Laptop>();


	@Override
	public String toString() {
		return "Alien [alienId=" + alienId + ", alienName=" + alienName + ", color=" + color + ", laptop=" + laptop
				+ "]";
	}
	
	
}
