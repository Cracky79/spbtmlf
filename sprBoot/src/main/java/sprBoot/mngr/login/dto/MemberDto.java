package sprBoot.mngr.login.dto;
import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 멤버 정보 dto 
 * 24.06.13
 * @author cracky
 *
 */
@Data
@Alias("mberDto")
public class MemberDto {
	
	private String mber_no;		/** 회원 번호 */
	private String mber_id;		/** 회원 아이디 */
	private String mber_pw;		/** 회원 비밀번호 */
	private String mber_nm;		/** 회원 이름 */
	private String mber_email;	/** 회원 이메일 */
	private String mber_grad;	/** 회원 권한  등급 */
	private String sbscrb_dt;	/** 회원 가입일자 */
	private String sbscrb_confm_dt;		/** 회원 승인 일자  */
	private String last_login_time;		/** 최종 로그인 시간 */
	private String use_at;		/** 회원 사용 여부 */
	
	public String getMber_no() {
		return mber_no;
	}
	public void setMber_no(String mber_no) {
		this.mber_no = mber_no;
	}
	public String getMber_id() {
		return mber_id;
	}
	public void setMber_id(String mber_id) {
		this.mber_id = mber_id;
	}
	public String getMber_pw() {
		return mber_pw;
	}
	public void setMber_pw(String mber_pw) {
		this.mber_pw = mber_pw;
	}
	public String getMber_nm() {
		return mber_nm;
	}
	public void setMber_nm(String mber_nm) {
		this.mber_nm = mber_nm;
	}
	public String getMber_email() {
		return mber_email;
	}
	public void setMber_email(String mber_email) {
		this.mber_email = mber_email;
	}
	public String getMber_grad() {
		return mber_grad;
	}
	public void setMber_grad(String mber_grad) {
		this.mber_grad = mber_grad;
	}
	public String getSbscrb_dt() {
		return sbscrb_dt;
	}
	public void setSbscrb_dt(String sbscrb_dt) {
		this.sbscrb_dt = sbscrb_dt;
	}
	public String getSbscrb_confm_dt() {
		return sbscrb_confm_dt;
	}
	public void setSbscrb_confm_dt(String sbscrb_confm_dt) {
		this.sbscrb_confm_dt = sbscrb_confm_dt;
	}
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}
}
