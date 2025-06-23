package sprBoot.conf.comm.constant;

import java.util.HashMap;
import java.util.Map;

import sprBoot.conf.comm.util.Validate;

/**
 * 25.06.23 
 * 관리자 , 사용자 세션 구분 값 
 * @author cracky
 *
 */
public enum SessionEnums {
	
	MNGR ( "mngr" , Constants.MNGE_SESSION_KEY , "/mngr/log/login.do" ) ,		/** 관리자 세션 정보 */
	USER ( "user" , Constants.USER_SESSION_KEY , "/user/log/login.do" ) ;		/** 사용자 세션 정보 */

	private String sessionSe;
	private String sessionKey;
	private String retUrl;
	
	SessionEnums( String sessionSe , String sessionKey, String retUrl ) {
		this.sessionSe = sessionSe;
		this.sessionKey = sessionKey;
		this.retUrl = retUrl;
	}
	
	/**
	 * 세션 정보 반환
	 * 
	 * key : 관리자 혹은 사용자 로그인 세션 키 
	 * url : 세션이 없는 경우 처리될 url 정보 
	 * 
	 * @param sessionSe
	 * @return
	 * @throws Exception
	 */
	public static Map <String,String> isValidSessionInfo ( String sessionSe ) throws Exception {
		
		Map <String,String> sessionInfo = null;
		
		if ( Validate.isNotEmpty(sessionSe) ) {
			SessionEnums s_nums []  = SessionEnums.values();
			for (SessionEnums s_num: s_nums) {
				if ( s_num.sessionSe.equals(sessionSe) ) {
					sessionInfo = new HashMap();
					sessionInfo.put("key", s_num.sessionKey);
					sessionInfo.put("url", s_num.retUrl);
				}
		    }
		}
		return sessionInfo;
	}
}
