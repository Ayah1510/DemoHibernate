package com.hibernate.DemoHibernate;

import java.util.List;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@ToString
@Getter
@Setter
public class AlienName {

	private String firstName;
	private String middleName;
	private String lastName;
	
	
	
}
