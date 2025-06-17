package sprBoot.conf.comm.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 공통 유틸 클래스 
 * 2021.05.26
 * @author Cracky
 *
 */
public class Function {
	
	/**
	 * 현재 경로를 .do를 제외하고 반환 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentPath ( HttpServletRequest request ) throws Exception {
		String currentUrl = request.getRequestURI();
		if ( Validate.isNotEmpty (currentUrl) )
			currentUrl = currentUrl.substring( 0, currentUrl.indexOf("."));
		return currentUrl;
	}
	
	public static String getLastRequestURI ( HttpServletRequest request) throws Exception {
		String currentUrl = getCurrentPath(request);
		String [] splitUrl = currentUrl.split("\\/");
		if (Validate.isNotEmpty(splitUrl))
			currentUrl = splitUrl[splitUrl.length-1];
		return currentUrl;
	}
	
	
	/**
	 * 현재 경로를 / => . 으로  변환하여 반환 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentPathToDots ( HttpServletRequest request ) throws Exception {
		String currentUrl = getCurrentPath(request);
		if ( Validate.isNotEmpty (currentUrl) ) 
			currentUrl = currentUrl.replaceAll("\\/", ".");
		return currentUrl;
	}
}
