package com.ssc.model;

public class SeatDTO {

	private int r_num_fk;
	private int s_num;
	private String row_num;
	private String s_name;
	
	public String getRow_num() {
		return row_num;
	}
	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getR_num_fk() {
		return r_num_fk;
	}
	public void setR_num_fk(int r_num_fk) {
		this.r_num_fk = r_num_fk;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	
}
