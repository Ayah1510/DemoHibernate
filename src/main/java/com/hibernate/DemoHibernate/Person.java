package com.hibernate.DemoHibernate;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table( name = "people" )
@Getter
@Setter
@ToString
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person {

	@Id
	private int id;
	private String name;
	private int mark;
}
