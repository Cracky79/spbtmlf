package sprBoot.conf.comm.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;

import sprBoot.conf.app.mapper.CommonMapper;
import sprBoot.conf.comm.constant.Constants;
import sprBoot.conf.comm.util.CommScriptGenerator;
import sprBoot.conf.comm.util.SessionUtil;
import sprBoot.conf.comm.util.Validate;
import sprBoot.logsynk.comm.util.ParamUtil;
import sprBoot.mngr.login.dto.MemberDto;

/**
 * 관리자 로그인 인터셉터 
 * 25.06.17
 * @author cracky
 *
 */

@Slf4j
public class MngrLogInterceptor implements HandlerInterceptor{
	
	@Autowired
	CommonMapper commonMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		/** 세션 객체 반환 */
		MemberDto mDto = (MemberDto)SessionUtil.getAttribute(Constants.MNGE_SESSION_KEY);
		/** 관리자 접근 인 경우 세션 체크  ( 로그인 되지 않았다면 로그인 페이지로 이동 처리  */
		if ( requestUri.indexOf("/mngr/") != -1 && Validate.isEmpty(mDto)) {
			CommScriptGenerator._generateScript(response , "로그인이 필요한 서비스입니다." ,
					CommScriptGenerator.ST_HREF  + "/mngr/log/login.do" +  CommScriptGenerator.ED_HREF , null);
			return false;
		}
		
		/** 서비스에 매핑 된 메뉴가 없어도  로그아웃 처리 시킨다.  25.06.18*/
		if ( Validate.isEmpty (SessionUtil.getAttribute(Constants.FULL_MENU_LIST)) ) {
			if ( SessionUtil.isSessionValid(Constants.MNGE_SESSION_KEY) )	/** 생성된 세션이 있다면 전부 clear 시킨다.*/
				SessionUtil.invalidate();
			CommScriptGenerator._generateScript(response , "허용된 서비스 정보가 없습니다." ,
					CommScriptGenerator.ST_HREF  + "/mngr/log/login.do" +  CommScriptGenerator.ED_HREF , null);
			return false;
		} 
		 
		return true;
	}
	
	/**
	 * 전체 트리 계층형 메뉴 처리
	 * 25.06.18 추가
	 * @param request
	 * @throws Exception
	 */
	private void _initMenuList (HttpServletRequest request , MemberDto mDto) throws Exception {
		
		if ( !SessionUtil.isSessionValid(Constants.FULL_MENU_LIST) ) { 
			
			ParamUtil param = new ParamUtil(request);
			
			param.set("_authorcode", mDto.getAuthor_code()+"".toUpperCase());		/** 관리자 권한 코드 **/
			param.set("_filter" , "Y");			/** 권한 있는 메뉴만 디스플레이 한다. */
			param.set("_group_code", mDto.getGroup_code());		/** 그룹 (업체) 별 메뉴 디스플레이  */
			
			List <Map> mngrMenuList = commonMapper.selMngrMenuList(param);			/** 세션에 대한 메뉴 목록 */
			
			// 메뉴 데이터가 있는 경우에는 세션을 생성 시킨다.  25.06.18 수정 
			if (Validate.isNotEmpty(mngrMenuList)) {
				SessionUtil.setAttribute(Constants.FULL_MENU_LIST, mngrMenuList);
				SessionUtil.setAttribute(Constants.TOP_MENU_KEY, mngrMenuList.get(0).get("menu_code"));				
			}
		}
		_setParamToSession(request);
	}
	
	/**
	 * 메뉴 이동에 따른 파라미터를 세션으로 저장한다.
	 * @param request
	 * @throws Exception
	 */
	private void _setParamToSession (HttpServletRequest request) throws Exception {
		
		if ( Validate.isNotEmpty( request.getParameter(Constants.TOP_MENU_KEY)) )
			SessionUtil.setAttribute(Constants.TOP_MENU_KEY, request.getParameter(Constants.TOP_MENU_KEY));
		if ( Validate.isNotEmpty( request.getParameter(Constants.LEFT_MENU_KEY)) )
			SessionUtil.setAttribute(Constants.LEFT_MENU_KEY, request.getParameter(Constants.LEFT_MENU_KEY));
		if ( Validate.isNotEmpty( request.getParameter(Constants.CHILD_MENU_KEY)) )
			SessionUtil.setAttribute(Constants.CHILD_MENU_KEY, request.getParameter(Constants.CHILD_MENU_KEY));
		
		
		/**  하위메뉴가 타 메뉴 이동 후에도 세션 유지되므로 TOP ,LEFT 만 not null인경우  클리어 시킨다. */
		if ( Validate.isNotEmpty( request.getParameter(Constants.TOP_MENU_KEY)) && 
				Validate.isEmpty( request.getParameter(Constants.LEFT_MENU_KEY)) &&
					Validate.isEmpty( request.getParameter(Constants.CHILD_MENU_KEY))) {
			SessionUtil.setAttribute(Constants.LEFT_MENU_KEY,"");
			SessionUtil.setAttribute(Constants.CHILD_MENU_KEY,"");
		}
		
		/**  하위메뉴가 타 메뉴 이동 후에도 세션 유지되므로 TOP ,LEFT 만 not null인경우  클리어 시킨다. */
		if ( Validate.isNotEmpty( request.getParameter(Constants.TOP_MENU_KEY)) && 
				Validate.isNotEmpty( request.getParameter(Constants.LEFT_MENU_KEY)) &&
					Validate.isEmpty( request.getParameter(Constants.CHILD_MENU_KEY))) {
			SessionUtil.setAttribute(Constants.CHILD_MENU_KEY,"");
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

}