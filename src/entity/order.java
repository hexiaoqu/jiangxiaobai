package entity;

import java.math.BigDecimal;

public class order {
	private Integer id;
	private String name;
	private String dishMegs;
	private String times;
	private String address;
	private Integer states;
	private BigDecimal sumPrice;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDishMegs() {
		return dishMegs;
	}
	public void setDishMegs(String dishMegs) {
		this.dishMegs = dishMegs;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
	public order() {
		
	}
	public order(Integer id, String name, String dishMegs, String times, String address, Integer states,
			BigDecimal sumPrice) {
		super();
		this.id = id;
		this.name = name;
		this.dishMegs = dishMegs;
		this.times = times;
		this.address = address;
		this.states = states;
		this.sumPrice = sumPrice;
	}
	@Override
	public String toString() {
		return "order [id=" + id + ", name=" + name + ", dishMegs=" + dishMegs + ", times=" + times + ", address="
				+ address + ", states=" + states + ", sumPrice=" + sumPrice + "]";
	}
	
	
}
