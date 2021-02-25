package com.ssc.model;

public class PlayingInfoDTO {
	private int p_num;
	private int m_num_fk;
	private int t_num_fk;
	private int r_num_fk;
	private String p_date;
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getM_num_fk() {
		return m_num_fk;
	}
	public void setM_num_fk(int m_num_fk) {
		this.m_num_fk = m_num_fk;
	}
	public int getT_num_fk() {
		return t_num_fk;
	}
	public void setT_num_fk(int t_num_fk) {
		this.t_num_fk = t_num_fk;
	}
	public int getR_num_fk() {
		return r_num_fk;
	}
	public void setR_num_fk(int r_num_fk) {
		this.r_num_fk = r_num_fk;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	
	
}
