package br.com.caprica.showcase.pojo;

public class User {
	private int id;
	private String name;
	private String job;
	
	// Constructors
	
	public User() {}
	
	public User(int id, String name, String job) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
	}
	
	//Getters and Setters
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}
