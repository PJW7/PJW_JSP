package com.ssc.model;

public class MovieReserReceiptDTO {
	private int m_num;
	private String m_name;
	private String m_image;
	private int re_num; //예매 번호
	private String p_date; //상영일자 정보
	private int u_num_fk; //사용자 정보
	private String r_name; //상영관 정보
	private int s_num;	//좌석 정보
	private String t_name; //극장 이름
	private String s_name;
	
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getM_num() {
		return m_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_image() {
		return m_image;
	}
	public void setM_image(String m_image) {
		this.m_image = m_image;
	}
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public int getU_num_fk() {
		return u_num_fk;
	}
	public void setU_num_fk(int u_num_fk) {
		this.u_num_fk = u_num_fk;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	
	
}
