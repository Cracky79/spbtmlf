package sprBoot.conf.comm.util;

import java.io.IOException;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;

import sprBoot.logsynk.comm.util.ParamUtil;

/**
 * 스크립스 상수를 참조한 페이지 구성 클래스 
 * @author cracky
 *
 */
public class CommScriptGenerator extends CommScriptConsts {
	
	protected static final Log logger = LogFactory.getLog(CommScriptGenerator.class);
	
	/**
	 * ajax response 처리 
	 * @param response
	 * @param mssage
	 * @throws IOException
	 */
	public static void sendAjaxResponse ( HttpServletResponse response , String mssage , Object value , boolean isJsonResponse ) throws IOException {
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding(CommScriptConsts.CHAR_SET);
        StringBuilder sb = new StringBuilder();
        if ( isJsonResponse ) {	/** json 형식의 response 인 경우 */
        	JSONObject jo = new JSONObject();
        	jo.put("login_result", "false");
        	jo.put("message",mssage);
        	jo.put("value" , value);
        	sb.append(jo.toString());
        } else 
        	sb.append(false);
        response.getWriter().write(sb.toString());
    }
	
	
	/**
     * base script for event
     * @param response
     * @param mssage
     * @param eventConstants
     * @throws IOException
     */
    public static void _generateScript ( HttpServletResponse response , String mssage , String eventConstants  , ParamUtil param ) throws IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding(CommScriptConsts.CHAR_SET);

        StringBuilder sb = new StringBuilder();
        sb.append(CommScriptConsts.ST_HTML + "\n");
        sb.append(CommScriptConsts.ST_HEAD + "\n");
        sb.append(CommScriptConsts.ST_TITLE);
        sb.append(mssage);
        sb.append(CommScriptConsts.ED_TITLE);
        sb.append(CommScriptConsts.ST_SCRIPT + "\n");
        sb.append(CommScriptConsts.ST_FUNC + "\n");
        if ( Validate.isNotEmpty(mssage) ) {			// 메세지가 있는 경우에만 alert 처리 
	        sb.append(CommScriptConsts.ST_ALERT);
	        sb.append(mssage);
	        sb.append(CommScriptConsts.ED_ALERT + "\n");
        }
        if ( Validate.isNotEmpty(eventConstants))		// 이벤트가 없는 경우는 페이지 포워딩 
        	sb.append(eventConstants + "\n");		
        else {
        	sb.append(CommScriptConsts.FORM_INIT + "\n");
        	sb.append(CommScriptConsts.ST_FORM_ACTION);
        	sb.append(param.getString("_submUrl"));
        	sb.append(CommScriptConsts.ED_FORM_ACTION + "\n");
        	sb.append(CommScriptConsts.FORM_SUBMIT + "\n");
        }
        sb.append(CommScriptConsts.ED_FUNC + "\n");
        sb.append(CommScriptConsts.ED_SCRIPT + "\n");
        sb.append(CommScriptConsts.ED_HEAD + "\n");
        sb.append(CommScriptConsts.ST_BODY + "\n");
        
        if ( Validate.isNotEmpty(param) && param.isNotEmpty("_submUrl")) {
        	sb.append(CommScriptConsts.ST_FORM + "\n");
        	sb.append(makeParamElementString( param , "POST" , param.getString("_exceptKeys")) + "\n");
        	sb.append(CommScriptConsts.ED_FORM + "\n");
        }
        
        sb.append(CommScriptConsts.ED_BODY + "\n");
        sb.append(CommScriptConsts.ED_HTML + "\n");
        
        response.getWriter().write(sb.toString());
    }
    
    /**
     * 메세지 처리 후 앱으로 back 이벤트 발생 
     * 2021.08.03 추가
     * @param response
     * @param mssage
     * @param eventConstants
     * @param param
     * @throws IOException
     */
    public static void alertWithBackToApp ( HttpServletResponse response , String mssage ) throws Exception {
    	
    	response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding(CommScriptConsts.CHAR_SET);

        StringBuilder sb = new StringBuilder();
        sb.append(CommScriptConsts.ST_HTML + "\n");
        sb.append(CommScriptConsts.ST_HEAD + "\n");
        sb.append(CommScriptConsts.ST_TITLE);
        sb.append(mssage);
        sb.append(CommScriptConsts.ED_TITLE);
        sb.append(CommScriptConsts.ST_SCRIPT + "\n");
        sb.append(CommScriptConsts.ST_FUNC + "\n");
        if ( Validate.isNotEmpty(mssage) ) {			// 메세지가 있는 경우에만 alert 처리 
	        sb.append(CommScriptConsts.ST_ALERT);
	        sb.append(mssage);
	        sb.append(CommScriptConsts.ED_ALERT + "\n");
        }
        String backAppId = "";
        sb.append("let retAppMessage = {id : '"+backAppId+"',  type : 'shopping_close', data : '' };\n");
        sb.append("top.postMessage(retAppMessage, '"+backAppId+"');\n");
        sb.append(CommScriptConsts.ED_FUNC + "\n");
        sb.append(CommScriptConsts.ED_SCRIPT + "\n");
        sb.append(CommScriptConsts.ED_HEAD + "\n");
        sb.append(CommScriptConsts.ST_BODY + "\n");
       
        sb.append(CommScriptConsts.ED_BODY + "\n");
        sb.append(CommScriptConsts.ED_HTML + "\n");
        
        response.getWriter().write(sb.toString());
    }
	
    
    /**
	 * make hidden elements string
	 * @param param
	 * @methodType
	 * @param exceptKeys : 제외할 파라미터 키   ( 콤마로 나열 )
	 * @return
	 */
	private static String makeParamElementString ( ParamUtil param , String methodType ,String exceptKeys) {
		StringBuffer sb = new StringBuffer();
		if ( Validate.isNotEmpty(param) ) {
			Set keyset = param.keySet();
			String key = "";
			Object val = null;
			java.util.Iterator itr = keyset.iterator();
			while(itr.hasNext()) {
				key = itr.next().toString();
				if ( !isDistKey ( exceptKeys , key ) ) {		// 제외키가 아닌경우 히든 생성 
					val = param.get(key);
					if(val instanceof String) {
						if (methodType.trim().equalsIgnoreCase("post"))
							sb.append("<input type = 'hidden' name = '"+key+"' value = '" + val.toString() + "'/>\n");
						else	/* get Parameter 조합 */
							sb.append(key+"=" + val.toString()+"&");
					} else if(val instanceof String[]) {
						String[] vals = (String[])val;
						if (vals != null && vals.length > 0) {
							for(int i = 0 ; i < vals.length ; i++) {
								if (methodType.trim().equalsIgnoreCase("post"))
									sb.append("<input type = 'hidden' name = '"+key+"' value = '" + vals[i] + "'/>\n");
								else
									sb.append(key+"=" + val.toString()+"&");
							}
						}
					} 
				}
			}
		}
		return sb.toString();
	}
	
	

    
	
	/**
	 * 제외키 체크 
	 * @param exceptKey
	 * @param checkKey
	 * @return
	 */
	private static boolean isDistKey ( String exceptKey , String checkKey) {
		boolean isDistKey = false;
		if ( Validate.isNotEmpty(exceptKey) ) {
			if (exceptKey.indexOf(checkKey) != -1)
				isDistKey = true;
		}
		return isDistKey;
	}
}
