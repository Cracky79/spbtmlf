package sprBoot.conf.comm.util;

import java.util.Map;
import javax.servlet.http.HttpSession;
import joy.logsynk.util.ResourceUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 세션 처리 유틸 
 * @version 1.0
 * @since 2025.06.16
 *
 */
public class SessionUtil {
	
	/**
	 * 세션 셋팅
	 * @param sessionName
	 * @param object
	 */
	public static void setAttribute(String sessionName, Object object) {
		RequestContextHolder.getRequestAttributes().setAttribute(sessionName, object, RequestAttributes.SCOPE_SESSION);
    }
	
	/**
	 * 세션 object 가져오기
	 * @param sessionName
	 * @return
	 */
	public static Object getAttribute ( String sessionName ) throws Exception {
		return (Object) RequestContextHolder.getRequestAttributes().getAttribute(sessionName, RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	 * 세션 존재 여부
	 * @param sessionName
	 * @return
	 */
	public static boolean isSessionValid( String sessionName ) throws Exception {
		Object obj = getAttribute (sessionName);
		if (obj != null)
			return true;
		return false;
	}
	
	
	/**
	 * Map 타입 세션 객체 
	 * 세션키에 대한 세션맵  value를 리턴한다.
	 * @param sessionName
	 */
	public static String getSessionMapValue ( String sessionKey , String sessionName) throws Exception {
		Object obj = getAttribute (sessionKey);
		if (obj != null) {
			Map sessionMap = (Map) obj;
			if (sessionMap.containsKey(sessionName))
				return (sessionMap.get(sessionName) + "").trim();
		}
		return "";
	}
	
	
	/**
	 * 특정 세션 제거 
	 * @param sessionName
	 */
	public static void removeAttribute(String sessionName) {
		RequestContextHolder.getRequestAttributes().removeAttribute(sessionName, RequestAttributes.SCOPE_SESSION);
	}
	
	/**
	 * 모든 세션 제거 
	 * @throws Exception
	 */
	public static void invalidate () throws Exception {
		HttpSession session = ResourceUtil.getServletRequest().getSession();
		if (session != null) session.invalidate();
	}
}
