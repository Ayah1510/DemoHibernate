package com.hibernate.DemoHibernate;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ex {

	@Id
	private int id;
	private String name;
	
	
}
