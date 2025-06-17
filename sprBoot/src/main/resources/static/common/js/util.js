/***********************************************************************************
 * Function Util Js
 ***********************************************************************************/

/**
 * check null
 * @param obj
 * @returns {Boolean}
 */
function isNotEmpty (obj) {
	if (obj == null || obj == '' || obj == undefined || obj.length == 0)
		return false;
	return true;
}

/**
 * 3자리 단위로 콤마 추가
 * @param str
 * @returns
 */
function gfn_addComma (str) {
	const regx =  /(^[+-]?\d+)(\d{3})/;
	str = gfn_removeComma(str);
	if (isNotEmpty(str)) {
		while(regx.test(str)) {
			str = str.replace(regx,"$1" + "," + "$2");
		}
	}
	return str;
}

/**
 * 콤마 제거
 * @param str
 * @returns
 */
function gfn_removeComma (str) {
	if (isNotEmpty(str))
		return str.replaceAll(",","");
	return str;
}


/**
 * 현재 일자에서 특정 일자를 계산한다.
 * @param pCalGub : y (년) , m (월) , d (일)
 * pTerm          : 기간
 * pDelium        : 구분자
 * @returns
 */
function gfn_getCalDate (pCalGub , pTerm , pDelium) {
	let curDate = new Date();
	if ( pCalGub.toLowerCase() == 'y' ) {
		curDate.setYear(curDate.getFullYear() + parseInt(pTerm));
	} else if ( pCalGub.toLowerCase() == 'm' ) {
		curDate.setMonth(curDate.getMonth() + parseInt(pTerm));
	} else if ( pCalGub.toLowerCase() == 'd' ) {
		curDate.setDate(curDate.getDate() + parseInt(pTerm));
	}
	let dMn = curDate.getMonth()+1;
	dMn = dMn < 10 ? '0'+ dMn : dMn;
	let dYn =curDate.getDate();
	dYn = dYn < 10 ? '0'+ dYn : dYn;
	return curDate.getFullYear() + pDelium + dMn + pDelium + dYn;
}

/**
 * 사업자 번호 유효성 체크
 * @param pCalGub : y (년) , m (월) , d (일)
 * bizNo          : 사업자번호
 * @returns
 */
function gfn_chkBizNo(bizNo){
    const checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1);
    let i, chkSum=0, c2, remander;
    bizNo = bizNo.getFilterValue();	/* 특수문자 제거 */
    for (i=0; i<=7; i++) chkSum += checkID[i] * bizNo.charAt(i);
    c2 = "0" + (checkID[8] * bizNo.charAt(8));
    c2 = c2.substring(c2.length - 2, c2.length);
    chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1));
    remander = (10 - (chkSum % 10)) % 10 ;
    if (Math.floor(bizNo.charAt(9)) == remander)
    	return true ;
    return false;
}

/**
 * formatted value
 * @param pVal      : 대상 value
 * @param pPattern  : 패턴 숫자 (ex) 3,5,2  -> 사업자번호 형태
 * @param pSptr		: 연결 구분자
 * @returns
 */
function lfn_makeFormat (pVal , pPattern, pSptr) {
	if (isNotEmpty(pVal)) {
		let ptrn = pPattern.split(',');
		let tmpval = pVal;
		let saveTmpValue = 0;
		for (let i = 0 ; i < ptrn.length ; i ++) {
			if (i == 0) {
				tmpval = pVal.substring(0 , parseInt(ptrn[0])) + pSptr;
				saveTmpValue = parseInt(ptrn[0]);
			} else {
				var _endLen = parseInt(ptrn[i]) <= pVal.length ? parseInt(ptrn[i]) : pVal.length;
				tmpval+= pVal.substring(saveTmpValue ,saveTmpValue + _endLen);
				saveTmpValue += _endLen;
				if ( i < ptrn.length -1)
					tmpval += pSptr;
			}
		}
		return tmpval;
	}
	return pVal;
}

/**
 * 이미지 파일 확장자 체크 
 * @returns
 */
function gfn_checkFileExt (fileValue) {
	const _imgExt = 'jpg,jpeg,bmp.png,gif,pcx';
	if (!isNotEmpty(fileValue))
		return false;
	else {
		let spValue = fileValue.split('.');
		let ext = spValue[spValue.length-1];
		if (_imgExt.indexOf(ext.toLowerCase()) != -1) 
			return true;
		else
			return false;
	}
}

/**
 * 사용자 파일 확장자 체크 
 * @param pType	: 파일 체크 기준 확장자
 * @param fileValue	: 체크 대상 value
 * @returns {Boolean}
 */
function gfn_checkCustFileExt (pType , fileValue) {
	if (!isNotEmpty(fileValue))
		return false;
	else {
		let spValue = fileValue.split('.');
		let ext = spValue[spValue.length-1];
		if (pType.toLowerCase().indexOf(ext.toLowerCase()) != -1) 
			return true;
		else
			return false;
	}
}

/** 쿠키 관련 함수  
 * 
 * pName  : 쿠키명
 * pValue : 쿠키value
 * pExpirehours : 만료일
 * pDomain (opt)
 * */
function gfn_setCookie(pName, pValue, pExpirehours, pDomain) {
	let today = new Date();
	today.setTime(today.getTime() + (60*60*1000*pExpirehours));
	document.cookie = pName + "=" + escape( pValue ) + "; path=/; expires=" + today.toGMTString() + ";";
	if (pDomain) {
		document.cookie += "domain=" + pDomain + ";";
	}
}

/**
 * 쿠키 가져오기 
 * @param pName
 */
function gfn_getCookie(pName) {
	let find_sw = false;
	let start, end;
	let i = 0;
	for (i=0; i<= document.cookie.length; i++) {
		start = i;
		end = start + pName.length;
		if(document.cookie.substring(start, end) == pName) {
			find_sw = true;
			break;
		}
	}

	if (find_sw == true) {
		start = end + 1;
		end = document.cookie.indexOf(";", start);
		if(end < start)
			end = document.cookie.length;
		return document.cookie.substring(start, end);
	}
	return "";
}

/**
 * 쿠키 삭제 처리 
 * @param pName
 */
function gfn_deleteCookie(pName) {
	let today = new Date();
	today.setTime(today.getTime() - 1);
	let value = gfn_getCookie(pName);
	if(isNotEmpty(value))
		document.cookie = pName + "=" + value + "; path=/; expires=" + today.toGMTString();
}

/**
 * mail 포맷 체크
 * @param pMail
 * @returns {Boolean}
 */
function gfn_checkEmail (pMail) {
	const mailRegx = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
	if (isNotEmpty(pMail)) 
		if ( pMail.match(mailRegx) )
			return true;
	return false;
}

/**
 * 특수문자 치환 
 * @param str
 * @returns
 */
function gfn_transCssTagStr (str) {
	str = str.replace(/</g,"&lt;");
	str = str.replace(/>/g,"&gt;");
	str = str.replace(/\"/g,"&quot;");
	str = str.replace(/\'/g,"&#39;");
	str = str.replace(/\n/g,"<br />");
 return str;
}


/**
 * ajax escapeXml 처리  
 * @param value
 * @param escapeXml
 */
function gfn_escapeXml (value) {
	return lfn_escape(value);
}

/**
 * @param s
 * @returns
 */
function lfn_escape(s) {
	if (isNotEmpty(s)) {
		s = s.replaceAll("&lt;", "<");
		s = s.replaceAll("&gt;", ">");
		s = s.replaceAll("&amp;", "&");
		s = s.replaceAll("&quot;", "\"");
		s = s.replaceAll("&apos;", "'");
	}
	return s;
}

/**
 * 개인,법인 사업자 구분 
 * @param bizNo
 * @returns {String}
 */
function gfn_checkBizGrade (bizNo) {
	if (isNotEmpty(bizNo) && bizNo.length == 10) {
		let bizGrade = '법인';
		bizNo = bizNo.getFilterValue();
		let checkIndvCorpNo =  parseInt(bizNo.substring(3,5));
		if (checkIndvCorpNo >= '1' && checkIndvCorpNo <= '80') {		//79 까지 개인과세 사업자 , 80  : 법인 아닌 단체
			bizGrade = '개인';
		} 
		if (checkIndvCorpNo >= '89' && checkIndvCorpNo <= '99') {		//90 ~ 99 : 개인면세사업자  , 89 :  종교단체
			bizGrade = '개인';
		}  
		return bizGrade;
	}
	return '';
}


/**
 * 좌측 데이터 마스킹
 * @param pData 마스크 대상 데이터
 * @param pMaskLen
 */
function gfn_maskLeftData ( pData , pMaskLen ) {
	if (isNotEmpty(pData)) {
		let retData = '';
		if (pData.length < pMaskLen) 
			pMaskLen = pData.length -1;
		for (let i  = 0 ; i < pMaskLen ; i ++ ) retData += '*';
		retData += pData.substring(pMaskLen , pData.length);
		return retData;
	}
	return '';
}


/**
 * 우픅 데이터 마스킹 
 * @param pData 마스크 대상 데이터
 * @param pMaskLen
 */

function gfn_maskRightData ( pData , pMaskLen ) {
	if (isNotEmpty(pData)) {
		let retData = '';
		if (pData.length < pMaskLen) 
			pMaskLen = pData.length -1;
		retData += pData.substring(0,pData.length - pMaskLen);
		for (let i  = 0 ; i < pMaskLen ; i ++ ) retData += '*';
		return retData;
	}
	return '';
}


/**
 * 특수문자 제거
 * @param val
 * @returns
 */
String.prototype.getFilterValue = function (s) {
	if (isNotEmpty(this))
    	return this.replace(/[ #\&\+\-%@=\/\\\:;,_'\"\^`~\!\?\*$#<>()\[\]\{\}]/g, "");
    return this;
}

/**
 * replaceAll with split & join
 * @param searchStr
 * @param replaceStr
 * @returns
 */
String.prototype.replaceAll = function (searchStr, replaceStr) {
	return this.split(searchStr).join(replaceStr);
}

// proto type trim
String.prototype.trim = function() {
 return this.replace(/(^\s*)|(\s*$)/gi, "");
}

/**
 * DataBase 기준(byte)으로 계산하며, 한글과 도형문자 등은 UTF-8 기준 한자당 3의 길이로 계산한다.
 *
 * @param str
 * @returns
 */
function gfn_dbLength(str) {
	let length = 0;
    for(let i = 0 ; i < str.length ; i++) {
    	let char = str.charAt(i).toUpperCase();
    	let code = str.charCodeAt(i);
    	let number = parseInt(code);
        if((char < "0" || char > "9") && (char < "A" || char > "Z") && ((number > 255) || (number < 0))) 
            length += 3;
        else 
            length += 1;
    }
    return length;
};

/**
 * 한글 바이트 처리를 len 길이 처리
 * @param str , len
 * @returns {Number}
 */
function gfn_custDbLength(str ,len) {
    var length = 0;
    for(var i = 0 ; i < str.length ; i++) {
        var char = str.charAt(i).toUpperCase();
        var code = str.charCodeAt(i);
        var number = parseInt(code);
        if((char < "0" || char > "9") && (char < "A" || char > "Z") && ((number > 255) || (number < 0))) {
            length += len;
        } else {
            length += 1;
        }
    }
    return length;
};

/**
 * 메시지 포멧터. java와 같은 방식으로 {0} {1} 등의 포멧팅을 지원한다. <br />
 * 예 : source = {0}은 {1}과 같아야 합니다. , params = [ "키", "값"] 와 같이 배열<br />
 * 사용예 : gfn_msgFormat("영구는 바보이다"); 또는 gfn_msgFormat("{0}는 {1}이다", ["영구",
 * "바보"]);
 *
 * @param source
 * @param params
 * @returns
 */
function gfn_msgFormat(source, params) {
    if(arguments.length === 1) {
        return function() {
            var args = $.makeArray(arguments);
            args.unshift(source);
            return $.fn.opformat.apply(this, args);
        };
    }
    if(arguments.length > 2 && params.constructor !== Array) {
        params = $.makeArray(arguments).slice(1);
    }
    if(params.constructor !== Array) {
        params = [ params ];
    }
    $.each(params, function(i, n) {
        source = source.replace(new RegExp("\\{" + i + "\\}", "g"), function() {
            return n;
        });
    });
    return source;
}

/**
 * Jquery Object 로 변환 후 반환
 *
 * @param formNm
 * @param elemNm
 * @returns
 */
function vfn_elemObj(formNm, elemNm) {
    return $("form[name='" + formNm + "'] [name='"+elemNm+"']");
}

/*******************************************************************************
 * 개별 값 검증 함수
 ******************************************************************************/

/**
 * 대상이 존재하고 대상의 값이 있으면 true
 *
 * @param elemObj
 * @returns
 */
function vfn_required(elemObj) {
    var value =  elemObj.val();
    if(!value) {
        return false;
    }
    var valueSize = gfn_length(elemObj);
    if(valueSize > 0) {
        return true;
    }
    elemObj.focus();
    return false;
}

/**
 * 대상의 값이 min~max 이상~이하 인 경우 참
 *
 * @param elemObj
 * @param minNum
 * @param maxNum
 * @returns
 */
function vfn_rangeNum(elemObj, minNum, maxNum) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var intValue;
    try {
        intValue = new Number(value);
    } catch(e) {
        return false;
    }
    if(minNum && intValue < minNum) {
        return false;
    }
    if(maxNum > 0 && intValue > maxNum) {
        return false;
    }
    return true;
}

/**
 * maxNum과 같거나 작으면 참
 *
 * @param elemObj
 * @param maxNum
 * @returns
 */
function vfn_maxNum(elemObj, maxNum) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var intValue;
    try {
        intValue = new Number(value);
    } catch(e) {
        return false;
    }
    if(maxNum && intValue > maxNum) {
        return false;
    }
    return true;
}

/**
 * minNum과 같거나 크면 참
 *
 * @param elemObj
 * @param minNum
 * @returns
 */
function vfn_minNum(elemObj, minNum) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var intValue;
    try {
        intValue = new Number(value);
    } catch(e) {
        return false;
    }
    if(minNum && intValue < minNum) {
        return false;
    }
    return true;
}

/**
 * 대상의 문자열길이가 maxLength 이하이면 참. UTF-8 기준으로 한글은 1자당 3의 길이를 가진다.(DB 컬럼크기와 동일)
 *
 * @param elemObj
 * @param maxLength
 * @returns
 */
function vfn_maxLength(elemObj, maxLength) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var valueSize = gfn_length(elemObj);
    if(valueSize > maxLength) {
        return false;
    }
    return true;
}

/**
 * 대상의 문자열길이가 minLength 이상이면 참. UTF-8 기준으로 한글은 1자당 3의 길이를 가진다.(DB 컬럼크기와 동일)
 *
 * @param elemObj
 * @param minLength
 * @returns
 */
function vfn_minLength(elemObj, minLength) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var valueSize = gfn_length(elemObj);
    if(valueSize < minLength) {
        return false;
    }
    return true;
}

/**
 * 대상의 문자열길이가 minLength 이상, maxLength 이하이면 참. UTF-8 기준으로 한글은 1자당 3의 길이를 가진다.(DB
 * 컬럼크기와 동일)
 *
 * @param elemObj
 * @param minLength
 * @param maxLength
 * @returns
 */
function vfn_rangeLength(elemObj, minLength, maxLength) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var valueSize = gfn_length(elemObj);
    if(minLength && valueSize < minLength) {
        return false;
    }
    if(maxLength > 0 && valueSize > maxLength) {
        return false;
    }
    return true;
}

function vfn_rangeSize(elemObj, minSize, maxSize) {
    // null 인경우 참을 반환
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var valueSize = gfn_length(elemObj);
    if(valueSize > 0) {
        if(minSize && valueSize < minSize) {
            return false;
        }
        if(maxSize > 0 && valueSize > maxSize) {
            return false;
        }
    }
    return true;
}

/**
 * 문자열이 소숫점이 없는 정수이면 참
 *
 * @param elemObj
 * @returns
 */
function vfn_isDigit(elemObj) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    return /^\d+$/.test(value);
}

/**
 * 문자열이 정수 또는 소숫점을 포함한 실수이면 참.
 *
 * @param elemObj
 * @returns
 */
function vfn_isNumber(elemObj) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)(?:\.\d+)?$/.test(value);
}

/**
 * 문자열이 영문 대소문자이면 참. 대문자만 또는 소문자만 필요한 경우 아래 정규 표현식에서 대소문자 범위만 삭제하여 추가하면 됨.
 *
 * @param elemObj
 * @returns
 */
function vfn_isAlpha(elemObj) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    return /^[a-zA-Z]+$/i.test(value);
}

/**
 * 문자열이 Date 변환 가능하면 참. new Date() 함수 사용시.
 *
 * @param elemObj
 * @returns
 */
function vfn_isDate(elemObj) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    var date = value;
    if(date.length == 8) {
        var year = value.substring(0, 4);
        var month = value.substring(4, 6);
        var day = value.substring(6, 8);
        date = year + "-" + month + "-" + day;
    }
    return !/Invalid|NaN/.test(new Date(date));
}

/**
 * 문자열이 Email 형식에 맞으면 참
 *
 * @param elemObj
 * @returns
 */
function vfn_isEmail(elemObj) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    return /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(value);
}

/**
 * 문자열이 URL 형식이면 참. http:// 또는 https:// 포함
 *
 * @param elemObj
 * @returns
 */
function vfn_isUrl(elemObj) {
    var value = elemObj.val();
    if(!value) {
        return false;
    }
    return /(http|https):\/\/[^\s^\.]+(\.[^\s^\.]+)*/.test(value);
}

/**
 * 영문 + 숫자 혹은
 * 영문 + 특수문자 비밀번호 검증 9 ~ 15 자리
 * @returns {Boolean}
 */
function gfn_checkPassword(upw) {
	var regx_enp = /^[a-zA-Z0-9!@#$%^&*()?_~\s]{9,15}$/;	/* 영문 + 숫자 + 특수문자*/
	 var chk = 0;
	 if(upw.search(/[0-9]/g) != -1 ) chk ++;
	 if(upw.search(/[a-z]/ig)  != -1 ) chk ++;
	 if(upw.search(/[!@#$%^&*()?_~\s]/g)  != -1  ) chk ++;
	 if(chk < 2) {
		 return false;
	 }
	 if(upw.length < 9 || upw.length > 15) {
		return false;
	 }
	return true;
}
