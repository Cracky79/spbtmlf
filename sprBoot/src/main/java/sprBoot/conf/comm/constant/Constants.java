package sprBoot.conf.comm.constant;

/**
 * 상수 클래스 정의 
 * @author Cracky
 *
 */
public class Constants {
	
	/**********************************************************************************************
	 * JSP | HTML LAYOUT PREFIX
	 **********************************************************************************************/
	public static String TILES_BASE_LAYOUT = "tiles";			/** 타일즈 레이아웃  prefix */
	public static String THYME_LEAF_ROOT_LAYOUT = "/";				/** 타임리프 이동 경로 prefix ( root ) */
	
	public static String THYME_LEAF_DEFAULT_LAYOUT	= "/pages";	/** 타임리프 관리자  */
	
	/**********************************************************************************************
	 * MULTI PART PROPERTY KEY
	 **********************************************************************************************/
	public static String MULTIPART_MAX_UPLOAD_SIZE = "joy.multi.maxUploadSize";
	public static String MULTIPART_MAX_MEMORY_SIZE = "joy.multi.maxInMemorySize";
	public static String MULTIPART_DIST_FILE_EXT = "joy.multi.distFiles";
	public static String MULTIPART_XSS_PATTERN = "joy.multi.appendPattern";
	public static String MULTIPART_MAX_UPLOAD_PER_SIZE = "joy.multi.per.maxUploadSize";
	
	/**********************************************************************************************
	 * Member Session key
	 **********************************************************************************************/
	public static String MNGE_SESSION_KEY = "MNGR_PJ_SESSION";		/** 관리자 세션 키 */
	public static String USER_SESSION_KEY = "USER_PJ_SESSION";		/** 사용자 세션 키 */
	
	
	/******************************************************************************************************
	 * Menu Session Key
	 ******************************************************************************************************/
	public final static String FULL_MENU_LIST = "FULL_MENU_LIST";		// 풂메뉴 목록		
	public final static String TOP_MENU_KEY = "_menu_code";				// top 메뉴 코드 
	public final static String LEFT_MENU_KEY = "_left_code";			// left 메뉴 코드 
	public final static String CHILD_MENU_KEY = "_chld_code";			// 3depth 메뉴 코드 
}
