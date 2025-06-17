/**
 * JTable function
 * @Cracky
 */


/**
 * 파라미터 추가 
 */
var gfn_addParam = function (gridId , extendObjectJson) {
	jTable.addParameter(gridId , lfn_isEmpty(extendObjectJson) ? {} : extendObjectJson);
}

/**
 * 인서트 레이어 생성
 */
var gfn_openInsertLayer = function (gridId , extendObjectJson) {
	if ( !lfn_isEmpty (extendObjectJson) ) {
		gfn_addParam(gridId , extendObjectJson);
	}
	jTable.openInsertLayer(gridId);
}

/** 메세지 처리 오버라이드가 필요한 경우 
 * type : warn  : 경고 
 *        info  : 인포 
 *        error : 에러 
 **/
var local_message = function (mesage , type) {
	alert('[' + type + '] ' + mesage);
}

/** 페이지 이동 */
var lfn_pageMove = function (tabId , pageNum) {
	jTable.pageMove(tabId , pageNum);
}

/** replaceAll */
var lfn_replace = function (str, target , replacement) {
	if (!lfn_isEmpty(str)) {
		return str.split(target).join(replacement);
	}
	return str;
}

var lfn_isEmpty = function (obj) {
	if ( obj == null || obj.length == 0 || obj == undefined || $.trim(obj) == '' ) {
		return true;
	}
	return false;
}

