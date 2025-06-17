package sprBoot.conf.comm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import sprBoot.conf.comm.constant.Constants;
import sprBoot.conf.comm.util.CommScriptGenerator;
import sprBoot.conf.comm.util.SessionUtil;
import sprBoot.conf.comm.util.Validate;
import sprBoot.mngr.login.dto.MemberDto;

/**
 * 관리자 로그인 인터셉터 
 * 25.06.17
 * @author cracky
 *
 */

@Slf4j
public class MngrLogInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		/** 관리자 접근 인 경우 세션 체크  ( 로그인 되지 않았다면 로그인 페이지로 이동 처리  */
		if ( requestUri.indexOf("/mngr/") != -1 && Validate.isEmpty(SessionUtil.getAttribute(Constants.MNGE_SESSION_KEY))) {
			//MemberDto mberDto = (MemberDto)SessionUtil.getAttribute(Constants.MNGE_SESSION_KEY);
			CommScriptGenerator._generateScript(response , "로그인이 필요한 서비스입니다." ,
					CommScriptGenerator.ST_HREF  + "/mngr/log/login.do" +  CommScriptGenerator.ED_HREF , null);
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

}