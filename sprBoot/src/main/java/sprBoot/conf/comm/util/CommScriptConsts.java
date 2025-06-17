package sprBoot.conf.comm.util;

/**
 * 컨트롤러 스크립트 처리 상수 
 * @version 1.0
 * @since 2020. 06. 10.
 * @author Cracky
 */
public class CommScriptConsts {

    /** 케릭터셋 */
    public static final String CHAR_SET = "utf-8";
    /** ST_HEAD  */
    public static final String ST_HTML = "<!DOCTYPE html><html lang=\"ko\">";
    /** ED_HEAD */
    public static final String ED_HTML = "</html>";
    /** ST_HEAD  */
    public static final String ST_HEAD = "<head><meta charset=\"utf-8\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />";
    /** ED_HEAD */
    public static final String ED_HEAD = "</head>";
    /** ST_TITLE */
    public static final String ST_TITLE = "<title>";
    /** ED_TITLE */
    public static final String ED_TITLE = "</title>";
    /** ST_BODY */
    public static final String ST_BODY = "<body onLoad='_pageMove();'>";
    /** ED_BODY */
    public static final String ED_BODY = "</body>";
    /** ST_SCRIPT */
    public static final String ST_SCRIPT = "<script type=\"text/javascript\">";
    /** ST_FUNC */
    public static final String ST_FUNC = "var _pageMove = function () {" ;
    /** ED_FUNC */
    public static final String ED_FUNC = "} ";
    /** ED_SCRIPT */
    public static final String ED_SCRIPT = "</script>";
    /** HISTORY_BACK */
    public static final String HISTORY_BACK = "history.back();";
    /** ST_ALERT */
    public static final String ST_ALERT = "alert(\"";
    /** ED_ALERT */
    public static final String ED_ALERT = "\");";
    /** CLOSE */
    public static final String CLOSE = "self.close();";
    /** OPENER_RELOAD */
    //public static final String OPENER_RELOAD = "if(opener) {opener.location.reload();}";
    public static final String OPENER_RELOAD = "if(opener) {opener.location.reload(opener.location.href);}";
    
    /** 주문 취소요청 . 환불 요청 , 구매확정 url fix  2021.07.21 */
    public static final String OPENER_RELOAD_ORDER_OPEN = "if(opener) {opener.location.href='";
    public static final String OPENER_RELOAD_ORDER_CLOSE = "';}";
    
    /** 주문서 에러 발생 후 location reload */
    public static final String LOCATION_RELOAD = "location.reload();";
    
    /** ST_HREF */
    public static final String ST_HREF = "location.href=\"";
    /** ST_HREF */
    public static final String ST_OPENER_HREF = "opener.location.href=\"";
    /** ED_HREF */
    public static final String ED_HREF = "\";";
    
    /** ST_FORM */
    public static final String ST_FORM	= "<form name = '_logSignFrm' id = '_logSignFrm' method = 'POST'>";
    /** ED_FORM */
    public static final String ED_FORM	= "</form>";
    /** ED_FORM */
    public static final String FORM_INIT  = "var rhForm = document._logSignFrm;";
    /** ST_FORM_ACTION */
    public static final String ST_FORM_ACTION  = "rhForm.action ='";
    /** ED_FORM_ACTION */
    public static final String ED_FORM_ACTION  = "';";
    /** */
    public static final String FORM_SUBMIT  = "rhForm.submit();";
    
    
    
}
