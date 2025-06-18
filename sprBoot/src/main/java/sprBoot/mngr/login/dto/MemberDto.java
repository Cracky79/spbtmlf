package sprBoot.mngr.login.dto;
import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 멤버 정보 dto 
 * 25.06.13
 * 25.06.18 ( 테이블 정보 변경으로 수정 ) : tb_mber_bass_info
 * @author cracky
 *
 */
@Data
@Alias("mberDto")
public class MemberDto {
	
	private String sn;			/** 일련번호 pk */
	private String mber_no;		/** 회원 번호 */
	private String mber_id;		/** 회원 아이디 */
	private String mber_pwd;		/** 회원 비밀번호 */
	private String mber_nm;		/** 회원 이름 */
	private String mber_email;	/** 회원 이메일 */
	private String mber_sttus;	/** 회원 상태 */
	private String mber_grad;	/** 회원 권한  등급 */
	
	private String zip;			/** 우편번호 */
	private String adres;		/** 주소 */
	private String adres_detail;	/** 주소상세 */
	
	private String sbscrb_dt;	/** 회원 가입일자 */
	private String change_reason;		/** 회원 상태 변경 사유 */
	private String last_login_time;		/** 최종 로그인 시간 */
	
	private String group_code;	/** 소속 그룹 코드 ( 업체 코드 ) - 관리자 화면을 업체별로 사용 하게 할 경우 */
	private String author_code;		/** 관리자 권한 코드   F ( 사용자  ) */
	private String mber_img;		/** 경로 + 이미지  */
	
	private String regist_dt;
	private String update_dt;
	private String use_at;			/** 회원 사용 여부 */
	

	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
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
	
	public String getMber_pwd() {
		return mber_pwd;
	}
	public void setMber_pwd(String mber_pwd) {
		this.mber_pwd = mber_pwd;
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
	
	public String getMber_sttus() {
		return mber_sttus;
	}
	public void setMber_sttus(String mber_sttus) {
		this.mber_sttus = mber_sttus;
	}
	
	public String getMber_grad() {
		return mber_grad;
	}
	public void setMber_grad(String mber_grad) {
		this.mber_grad = mber_grad;
	}
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	
	public String getAdres_detail() {
		return adres_detail;
	}
	public void setAdres_detail(String adres_detail) {
		this.adres_detail = adres_detail;
	}
	
	public String getSbscrb_dt() {
		return sbscrb_dt;
	}
	public void setSbscrb_dt(String sbscrb_dt) {
		this.sbscrb_dt = sbscrb_dt;
	}
	
	public String getChange_reason() {
		return change_reason;
	}
	public void setChange_reason(String change_reason) {
		this.change_reason = change_reason;
	}
	
	public String getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(String last_login_time) {
		this.last_login_time = last_login_time;
	}
	
	public String getGroup_code() {
		return group_code;
	}
	public void setGroup_code(String group_code) {
		this.group_code = group_code;
	}
	
	public String getAuthor_code() {
		return author_code;
	}
	public void setAuthor_code(String author_code) {
		this.author_code = author_code;
	}
	
	public String getMber_img() {
		return mber_img;
	}
	public void setMber_img(String mber_img) {
		this.mber_img = mber_img;
	}
	
	public String getUse_at() {
		return use_at;
	}
	public void setUse_at(String use_at) {
		this.use_at = use_at;
	}
	
	public String getRegist_dt() {
		return regist_dt;
	}
	public void setRegist_dt(String regist_dt) {
		this.regist_dt = regist_dt;
	}
	
	public String getUpdate_dt() {
		return update_dt;
	}
	public void setUpdate_dt(String update_dt) {
		this.update_dt = update_dt;
	}
}
