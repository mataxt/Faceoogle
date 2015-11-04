package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_TABLE")

public class Log {
	
	private int id;
	private String txt;
	
	public Log() {
		
	}
}
