package com.ssc.model;

public class RoomDTO {

	private int r_num;
	private int t_num_fk;
	private String r_name;
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public int getT_num_fk() {
		return t_num_fk;
	}
	public void setT_num_fk(int t_num) {
		this.t_num_fk = t_num;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	
}
