package sprBoot.conf.comm.web;

import groovy.util.logging.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sprBoot.conf.comm.constant.Constants;
import sprBoot.logsynk.comm.util.ParamUtil;
import sprBoot.logsynk.comm.util.Token;
import sprBoot.conf.comm.util.CommScriptConsts;
import sprBoot.conf.comm.util.CommScriptGenerator;
import sprBoot.conf.comm.util.Function;
import sprBoot.conf.comm.util.Validate;
import sprBoot.logsynk.comm.util.JTable;

/**
 * 공통 부모 클래스
 * 2025.06.11
 * @author Cracky
 *
 */
@Controller
public class CommController implements ErrorController {
	
	/** 로그 */
	protected static Logger log = LoggerFactory.getLogger(CommController.class);

	/**
	 * page Title 
	 * @param model
	 * @param pageTitle
	 */
	protected void setPageTitle (Model model , String pageTitle) {
		model.addAttribute("pageTitle", pageTitle);
	}
	
	/**
	 * base tiles layout page
	 * @param param
	 * @return
	 * @throws Exception
	 */
	protected String tilesResultPage ( ParamUtil param ) throws Exception {
		String returnTilesStr = Constants.TILES_BASE_LAYOUT + Function.getCurrentPathToDots(param.getHttpServletrequestuest());
		return returnTilesStr;
	}
	/**
	 * JTable 페이징 변수 처리
	 * @param param
	 * @throws Exception
	 */
 	protected void setJTableParam ( ParamUtil param ) throws Exception {
 		JTable.setJtablePagingParam(param, JTable.CURRENT_PAGE_SIZE, JTable.CURRENT_PAGE_KEY);
 	}
 	
 	/**
 	 * responsebody for json
 	 * @param result
 	 * @param param
 	 * @return
 	 * @throws Exception
 	 */
 	protected Map <String , Object > responseJTableBody ( List <Map> result , ParamUtil param ) throws Exception {
 		return JTable.responseBody(result, param);
 	}
 	
 	
 	/**
 	 * validate 체크를 위한 1회용 토큰 발행
 	 * @param model
 	 * @throws Exception
 	 */
 	protected void setToken ( HttpServletRequest request , Model model ) throws Exception {
 		Token.set(request);
 		model.addAttribute("TOKEN_KEY",Token.getTokenKey());
 	}
 	
 	/**
 	 * 토큰 검증 처리 
 	 * 25.06.16
 	 * @param request
 	 * @return
 	 * @throws Exception
 	 */
 	protected boolean isValidToken (ParamUtil param) throws Exception {
 		if ( param.isNotEmpty(Token.getTokenKey()) && Token.isValid(param.getHttpServletrequestuest()) ) {
 			/** 토큰이 일치 하는 경우  새롭게 토큰 재발행*/
 			Token.set(param.getHttpServletrequestuest());
 			return Boolean.TRUE;
 		}
 		return Boolean.FALSE;
 	}
 	
 	/**
 	 * url 기반의 이동 페이지 설정 
 	 * @param prefix
 	 * @param param
 	 * @return
 	 * @throws Exception
 	 */
 	protected String getForwardPageFromUrl ( String prefix , HttpServletRequest request ) throws Exception {
 		 return prefix + Function.getCurrentPath(request);
 	}
 	
 	/**
 	 * thymeleaf error page
 	 * @param request
 	 * @param response
 	 * @param model
 	 * @return
 	 */
 	@RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response,  Model model) {
 		
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        int statusCode = Integer.parseInt(status.toString());
        response.setStatus(statusCode);

        model.addAttribute("code", status.toString());
        model.addAttribute("msg", HttpStatus.valueOf(Integer.valueOf(status.toString())));
        return Constants.THYME_LEAF_ROOT_LAYOUT + "error/error";
    }
 	
 	
 	/*******************************************************************************************
 	 * Generate Html message , page move 
 	 *******************************************************************************************/
 	
 	/**
     * <pre>
     * 팝업 처리 javascript alert(message) 후 self.close() 호출  
     * </pre>
     * @date : 2020. 7. 10. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2020. 7. 10.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @return
     * @throws IOException
     */
    protected void scriptAlertCloseOpenerReload( HttpServletResponse response, String mssage) throws IOException {
    	CommScriptGenerator._generateScript (response , mssage , CommScriptConsts.OPENER_RELOAD  + CommScriptConsts.CLOSE   , null);
    }
    
    
    /**
     * <pre>
     * 팝업 처리 javascript alert(message) 후 self.close() 호출
     * opener url을 파라미터로 받는다.   
     * </pre>
     * @date : 2021. 7. 21. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2021. 7. 21.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @return
     * @throws IOException
     */
    protected void scriptAlertCloseOpenerReloadCust ( HttpServletResponse response , String mssage , String pageUrl ) throws IOException {
    	CommScriptGenerator._generateScript (response , mssage , CommScriptConsts.OPENER_RELOAD_ORDER_OPEN + pageUrl + CommScriptConsts.OPENER_RELOAD_ORDER_CLOSE  + CommScriptConsts.CLOSE   , null);
    }
    
    /**
     * <pre>
     * 팝업 처리 javascript alert(message) 후 self.close() 호출  
     * </pre>
     * @date : 2025. 6. 15. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2025. 6. 15.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @return
     * @throws IOException
     */
    protected void scriptAlertClose( HttpServletResponse response, String mssage) throws IOException {
    	CommScriptGenerator._generateScript (response , mssage , CommScriptConsts.CLOSE  , null);
    }
    
    /**
     * <pre>
     * 팝업 처리 javascript alert(message) 후 history.back() 호출  
     * </pre>
     * @date : 2025. 6. 15. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2025. 6. 15.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @return
     * @throws IOException
     */
    protected void scriptAlertBack(HttpServletResponse response, String mssage) throws IOException {
    	CommScriptGenerator._generateScript(response , mssage , CommScriptConsts.HISTORY_BACK , null);
    }
    
    
    /**
     * <pre>
     * alert 메세지 반환 후  페이지 리다이렉트 
     * </pre>
     * @date : 2025. 6. 15. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2025. 6. 15.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @return
     * @throws IOException
     */
    protected void scriptAlertRedirect(HttpServletResponse response, String mssage , String pageUrl ) throws IOException {
    	CommScriptGenerator._generateScript(response , mssage ,
    						CommScriptGenerator.ST_HREF  + pageUrl +  CommScriptGenerator.ED_HREF , null);
    }
    
    /**
     * <pre>
     * alert 메세지 반환 후 페이지 이동 없이 이벤트 종료 
     * </pre>
     * @date : 2025. 6. 15. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2025. 6. 15.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @return
     * @throws IOException
     */
    protected void scriptAlertReload(HttpServletResponse response, String mssage) throws IOException {
    	CommScriptGenerator._generateScript(response , mssage , CommScriptGenerator.LOCATION_RELOAD  , null);
    }
    
    /**
     * <pre>
     * 팝업 처리 javascript alert(message) 후 페이지 이동   
     * </pre>
     * @date : 2025. 6. 15. 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2025. 6. 15.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param response
     * @param mssage
     * @param url 
     * @param paramUtil
     * @param exceptKeys		: paramUtil 내의 제외 파라미터 키  ( 콤마로 나열  )
     * @return
     * @throws IOException
     */
    protected void scriptAlertForward ( HttpServletResponse response, String mssage , String url , ParamUtil param , String exceptKeys) throws IOException {
    	if ( !Validate.isNotEmpty(param )) param = new ParamUtil();
    	param.set("_submUrl", url);
    	param.set("_exceptKeys", exceptKeys);
    	CommScriptGenerator._generateScript(response , mssage , null , param);
    }
    
    
    /**
     * <pre>
     * 비동기 통신 ajax responsebody json 응답
     * </pre>
     * @date : 2025. 6. 15 
     * @author : Cracky
     * @history :
     * ------------------------------------------------------------------------------------
     * 변경일                             작성자                             변경내용
     * ------------------------------------------------------------------------------------
     * 2025. 6. 15.                      Cracky                       최초작성
     * ------------------------------------------------------------------------------------
     * @param success : true or false   (key : result  )
     * @param mssage  : 메세지 			(key : message )
     * @param value   : object value    (key : value   )
     * @return
     * @throws Exception
     */
    protected Map <String ,Object> responseJsonBody ( boolean success , String message , Object value ) throws Exception {
    	Map <String,Object> jsonBody = new HashMap ();
    	jsonBody.put("result" , success);
    	jsonBody.put("message", message);
    	jsonBody.put("value"  , value  );
    	return jsonBody;
    }
}
