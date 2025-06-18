package sprBoot.mngr.login.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sprBoot.conf.comm.constant.Constants;
import sprBoot.conf.comm.util.Function;
import sprBoot.conf.comm.util.SessionUtil;
import sprBoot.conf.comm.util.Validate;
import sprBoot.conf.comm.web.CommController;
import sprBoot.logsynk.comm.util.ParamUtil;
import sprBoot.logsynk.comm.util.Token;
import sprBoot.mngr.login.dto.MemberDto;
import sprBoot.mngr.login.service.MngrLoginService;

/**
 * 관리자 로그인 컨트롤러 
 * 24.06.13
 * @author cracky
 *
 */
@Controller
@RequestMapping("/mngr/log/")
public class MngrLoginController extends CommController {
	
	
	@Autowired
	MngrLoginService mngrLoginService;
	
	/**
	 * 관리자 로그인 페이지 
	 * @param param
	 * @param model
	 * @param mberDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login.do")
	public String login( ParamUtil param , Model model ) throws Exception {
		this.setPageTitle(model, ":::: 관리자 로그인 ::::");
		setToken(param.getHttpServletrequestuest() , model);	/** 보안을 위한 1회용 토큰 발행  */
		return getForwardPageFromUrl(Constants.THYME_LEAF_DEFAULT_LAYOUT , param.getHttpServletrequestuest() );
	}
	
	
	/**
	 * 관리자 로그인 처리 
	 * @param param
	 * @param model
	 * @param mberDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping( value = "loginProc.do" , method = RequestMethod.POST)
	public String loginProc( ParamUtil param , Model model , MemberDto mberDto , HttpServletResponse response ) throws Exception {

		String forwardUrl = "login.do";
		String mesage = "잘못된 접근 입니다.";
		
		if ( isValidToken(param) ) {
			
			param.printParams();		/** ParamUtil 처리 가능 */
			
			MemberDto resultDto = mngrLoginService.selMberUserInfo(mberDto);
			
			if ( Validate.isNotEmpty(resultDto) ) {
				
				/** step1 사용자 비밀 번호 일치 여부 확인 */
				if ( mberDto.getMber_pwd().equals(resultDto.getMber_pwd()) ) {
					/** step2 use_at 체크  */
					if ( resultDto.getUse_at().equals("Y") ) {
						// 이동 페이지 
						forwardUrl = "/mngr/dash/dashboard.do";
						mesage = "";		/** 로그인 성공  */
						/** 세션 생성 */
						SessionUtil.setAttribute(Constants.MNGE_SESSION_KEY, resultDto);
					} else
						mesage = "[허가되지 않은 사용자 입니다.]";
				} else 
					mesage = "[비밀번호가 일치하지 않음]";
			} else
				mesage = "[일치하는 회원이 없음]";
		}
		scriptAlertRedirect(response, mesage, forwardUrl);
		return null;
	}
	
	
}
