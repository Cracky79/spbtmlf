/***************************************************************
 * Java 객체에 대한 Validate 검증 처리 클래스
 ***************************************************************/
package sprBoot.conf.comm.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 검증에 필요한 함수를 정의 한다.
 * 2020.06.01
 * @author cracky
 *
 */
public class Validate {
	
	/** 로그 */
	private static Logger log = LoggerFactory.getLogger(Validate.class);
	
	/**
	 * 문자 최대길이 확인. maxLength 보다 같거나 짧으면 참
	* DataBase 기준으로 검증하기 때문에 문자열을 byte 단위를 사용하며
	* UTF-8을 기준하므로 한글 또는 도형문자 등은 1자를 3byte로 계산한다.
	 * @param validValue
	 * @param maxLength
	 * @return
	 */
	public static boolean isMaxLength(final CharSequence validValue, int maxLength) {
		int length = 0;
		if(isNotEmpty(validValue)) {
			length = getByteLength(validValue.toString());
		}
		return (length <= maxLength);
	}

	/**
	 * 문자 최대길이 확인. maxLength 보다 같거나 짧으면 참. DataBase 기준으로 검증하기 때문에
	 * 문자열을 byte 단위를 사용하며 UTF-8을 기준하므로 한글 또는 도형문자 등은 1자를 3byte로 계산한다.
	 * @param validValue
	 * @param minLength
	 * @return
	 */
	public static boolean isMinLength(final CharSequence validValue, int minLength) {
		int length = 0;
		if(isNotEmpty(validValue)) {
			length = getByteLength(validValue.toString());
		}
		return (length >= minLength);
	}

	/**
	 * 최대 숫자값 확인. max 보다 같거나 작으면 참
	 * @param validValue
	 * @param max
	 * @return
	 */
	public static boolean isMax(final Number validValue, int max) {
		return ((validValue != null) && (validValue.intValue() <= max));
	}

	/**
	 * 최대 숫자값 확인. max 보다 같거나 작으면 참
	 * @param validValue
	 * @param max
	 * @return
	 */
	public static boolean isMax(final CharSequence validValue, int max) {
		if(isEmpty(validValue)) {
			return false;
		}
		Integer value = Integer.parseInt(validValue.toString());
		return (value.intValue() <= max);
	}

	/**
	 * 최소 숫자값 확인. min 보다 같거나 크면 참
	 * @param validValue
	 * @param min
	 * @return
	 */
	public static boolean isMin(final Number validValue, int min) {
		return ((validValue != null) && (validValue.intValue() >= min));
	}

	/**
	 * 최소 숫자값 확인. min 보다 같거나 크면 참
	 * @param validValue
	 * @param min
	 * @return
	 */
	public static boolean isMin(final CharSequence validValue, int min) {
		if(isEmpty(validValue)) {
			return false;
		}
		Integer value = Integer.parseInt(validValue.toString());
		return (value.intValue() >= min);
	}

	/**
	 * 영문만으로 값이 구성되어 있는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isAlpha(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int size = validValue.length();
		for(int i = 0; i < size; i++) {
			char ch = validValue.charAt(i);
			if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 문자만으로 값이 구성되어 있는지 확인. 모든 국가의 문자 포함(한글, 영문, 중문, 일문...)
	 * @param validValue
	 * @return
	 */
	public static boolean isLetter(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int size = validValue.length();
		for(int i = 0; i < size; i++) {
			if(!Character.isLetter(validValue.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 한글만으로 값이 구성되어 있는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isHangul(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int size = validValue.length();
		for(int i = 0; i < size; i++) {
			char c = validValue.charAt(i);
			if(!(44032 <= c && c <= 55203)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 영문과 숫자만으로 구성되었는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isAlphaNumeric(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int sz = validValue.length();
		for(int i = 0; i < sz; i++) {
			char ch = validValue.charAt(i);
			if(!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) && !Character.isDigit(validValue.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 영문이 소문자로만 구성되었는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isAlphaLower(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int sz = validValue.length();
		for(int i = 0; i < sz; i++) {
			char ch = validValue.charAt(i);
			if(!Character.isLowerCase(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 영문이 대문자로만 구성되었는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isAlphaUpper(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int sz = validValue.length();
		for(int i = 0; i < sz; i++) {
			char ch = validValue.charAt(i);
			if(!Character.isUpperCase(ch)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 입력 값(문자열, 문자배열, 숫자, 숫자배열, Collection, Array)과 제시된 비교 대상 문자배열을 비교하여
	 * 비교 값이 존재하는지 확인. 다대다 관계를 비교하여 포함 여부를 확인한다. 하나라도 같으면 참 반환.
	 * @param validValue
	 * @param compareValues
	 * @return
	 */
	public static boolean isContainsValue(final Object validValue, String... compareValues) {
		if(validValue == null) {
			return false;
		}
		// 문자열 확인
		if(validValue instanceof CharSequence) {
			String value = (String)validValue;
			for(String compareValue : compareValues) {
				if(value.equals(compareValue)) {
					return true;
				}
			}
		}
		// 문자 배열 확인
		else if(validValue instanceof String[]) {
			String[] values = (String[])validValue;
			List<String> valueList = Arrays.asList(values);
			for(String compareValue : compareValues) {
				if(valueList.contains(compareValue)) {
					return true;
				}
			}
		}
		// 숫자 확인
		else if(validValue instanceof Number) {
			Integer value = (Integer)validValue;
			for(String compareValue : compareValues) {
				Integer numValue = Integer.getInteger(compareValue);
				if(numValue.equals(value)) {
					return true;
				}
			}
		}
		// 숫자 배열 확인
		else if(validValue instanceof Integer[]) {
			Integer[] values = (Integer[])validValue;
			List<Integer> valueList = Arrays.asList(values);
			for(String compareValue : compareValues) {
				Integer numValue = Integer.getInteger(compareValue);
				if(valueList.contains(numValue)) {
					return true;
				}
			}
		}
		// Collection 확인
		else if(validValue instanceof Collection) {
			Collection<?> collection = (Collection<?>)validValue;
			for(String compareValue : compareValues) {
				if(collection.contains(compareValue)) {
					return true;
				}
			}
		}
		// Array 배열
		else if(validValue.getClass().isArray()) {
			Object[] arrays = (Object[])validValue;
			int arrayCnt = arrays.length;
			for(String compareValue : compareValues) {
				for(int i = 0; i < arrayCnt; i++) {
					String value = arrays[i].toString();
					if(compareValue.equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 지정된 날짜 포멧 패턴과 입력 값을 비교하여 패턴에 적합한지 확인하고,
	 * 또한 입력된 날짜를 파싱하여 입력값이 존재하는 일자 인지 확인. 년 월 일만 확인한다.
	 * @param validValue
	 * @param datePattern
	 * @return
	 */
	public static boolean isDate(final CharSequence validValue, String datePattern) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		String patternLowerCase = datePattern.toLowerCase();
		String value = validValue.toString();
		if(patternLowerCase.length() == value.length()) {
			String delimiter = "";
			if(datePattern.length() > 8) {
				delimiter = datePattern.substring(4, 5);
			}
			String valuePattern = "[123][0-9]{3}" + delimiter + "[0-9]{2}" + delimiter + "[0-9]{2}";
			if(value.matches(valuePattern)) {
				String[] dateArr = { "", "", "" };
				if("".equals(delimiter)) {
					dateArr[0] = value.substring(0, 4);
					dateArr[1] = value.substring(4, 6);
					dateArr[2] = value.substring(6, 8);
				} else {
					dateArr = value.split(delimiter);
				}
				int yyyy = new Integer(dateArr[0]);
				int mm = new Integer(dateArr[1]);
				int dd = new Integer(dateArr[2]);
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, yyyy);
				int maxVal = calendar.getActualMaximum(Calendar.MONTH) + 1;
				int minVal = calendar.getActualMinimum(Calendar.MONTH) + 1;
				if(mm > maxVal || mm < minVal) {
					return false;
				}
				calendar.set(Calendar.MONTH, mm - 1);
				maxVal = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				minVal = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
				if(dd > maxVal || dd < minVal) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 이메일 형식이 맞는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isEmail(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		String value = validValue.toString();
		boolean match = value
				.matches("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
		return match;
	}

	/**
	 * 주민번호 검증 수식에 따라서 입력된 값을 계산 하여 확인.
	 * 주민번호는 '-'가 포함되어 있는 지는 상관없음
	 * @param validValue
	 * @return
	 */
	public static boolean isJuminNo(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		String value = validValue.toString();
		value = value.replaceAll("-", "");
		if(value.length() != 13) {
			return false;
		}
		if(Integer.parseInt(value.substring(6, 7)) == 5 || Integer.parseInt(value.substring(6, 7)) == 6) {
			return true;
		}
		int sum = 0;
		for(int i = 0; i < 12; i++) {
			sum += (((i % 8) + 2) * Integer.parseInt(value.substring(i, i + 1)));
		}
		sum = 11 - (sum % 11);
		sum = sum % 10;
		String lastNum = value.substring(12);
		if(sum == Integer.parseInt(lastNum)) {
			return true;
		}
		return false;
	}

	/**
	 * 외국인등록번호 검증 수식에 따라서 입력된 값을 계산 하여 확인.
	 * 외국인등록번호는 '-'가 포함되어 있는 지는 상관없음
	 * @param validValue
	 * @return
	 */
	public static boolean isForeignerNo(final CharSequence validValue) {
		boolean retVal = false;
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		String value = validValue.toString();
		value = value.replaceAll("-", "");
		if(value.length() != 13) {
			return false;
		}
		if(isDigits(validValue.toString().substring(0, 1))) {
			retVal = false;
		} else {
			retVal = true;
		}
		/*
		// 5,6,7,8이 외국인 규칙
		String sex = value.substring(6, 7);
		int sexNum = Integer.valueOf(sex);
		if(sexNum < 5 || sexNum > 8){
			return false;
		}
		int sum = 0;
		for(int i = 2; i < 12; i++){
			sum += (((i % 8) + 2) * Integer.parseInt(value.substring(i, i + 1)));
		}
		sum = 11 - (sum % 11);
		sum = sum % 10;
		String lastNum = value.substring(12);
		if(sum == Integer.parseInt(lastNum)){
			return true;
		}
		*/
		return retVal;
	}

	/**
	 * 입력된 문자열의 길이가 최소, 최대 값 사이인지 확인.
	 * DataBase 기준으로 검증하기 때문에 문자열을 byte 단위를 사용하며
	 * UTF-8을 기준하므로 한글 또는 도형문자 등은 1자를 3byte로 계산한다.
	 * @param validValue
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isLengthMetch(final CharSequence validValue, int min, int max) {
		if(isEmpty(validValue)) {
			return false;
		}
		int length = getByteLength(validValue.toString());
		if(max < 0 && length >= min) {
			return true;
		}
		if(max >= 0 && length >= min && length <= max) {
			return true;
		}
		return false;
	}

	/**
	 * 입력 값이 <code>null</code> 인지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isNull(final Object validValue) {
		if(validValue == null) {
			return true;
		}
		return false;
	}

	/**
	 * 입력 값이 <code>not null</code> 인지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isNotNull(final Object validValue) {
		return (validValue != null);
	}

	/**
	 * 숫자(정수)만으로 구성되어 있는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isDigits(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		int size = validValue.length();
		for(int i = 0; i < size; i++) {
			if(!Character.isDigit(validValue.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 숫자(소숫점 포함 실수 까지만 적용)만으로 구성되어 있는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isNumeric(final CharSequence validValue) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		String value = validValue.toString();
		Float.valueOf(value);
		return true;
	}

	/**
	 * 입력된 숫자 값이 최소, 최대값 사이인지 확인(정수 값만).
	 * 입력값 타입은 <code>Number</code> 또는 <code>String</code> 타입이어야 함
	 * @param validValue
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isMinMaxMatch(final Object validValue, int min, int max) {
		int value = 0;
		if(validValue instanceof Number) {
			value = ((Number)validValue).intValue();
		} else if(validValue instanceof CharSequence) {
			value = Integer.parseInt(validValue.toString());
		} else {
			return false;
		}
		if(max < 0 && value >= min) {
			return true;
		}
		if(max >= 0 && value >= min && value <= max) {
			return true;
		}
		return false;
	}

	/**
	 * 문자열을 정규표현식을 통하여 검증 예 : [a-zA-Z0-9]
	 * @param validValue
	 * @param regexp
	 * @return
	 */
	public static boolean isRegExpMatch(final CharSequence validValue, String regexp) {
		if(validValue == null || validValue.length() == 0) {
			return false;
		}
		String value = validValue.toString();
		return value.matches(regexp);
	}

	/**
	 * 대상 객체가 <code>null</code>이 아니고, 길이가 0 이 아닌지를 확인한다.
	 * 숫자형의 경우는 0 이상의 값인지를 확인한다. 0 보다 작은 경우 true
	 * <code>CharSequence, Number, Enumeration, Iterator, Array</code>를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isNotEmpty(final Object validValue) {
		if(validValue == null) {
			return false;
		}
		// 문자열 확인
		if(validValue instanceof CharSequence) {
			return (validValue.toString().length() > 0);
		}
		// 숫자 확인(숫자는 null이 아니면 무조건 true)
		else if(validValue instanceof Number) {
			// Number prop = (Number) validValue;
			return true;
		}
		// Enumeration
		else if(validValue instanceof Enumeration) {
			Enumeration<?> enumer = (Enumeration<?>)validValue;
			return enumer.hasMoreElements();
		}
		// Iterator
		else if(validValue instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>)validValue;
			return iter.hasNext();
		}
		// Collection
		else if(validValue instanceof Collection) {
			Collection<?> collection = (Collection<?>)validValue;
			return (collection != null && collection.size() > 0);
		}
		// Array
		else if(validValue.getClass().isArray()) {
			return (Array.getLength(validValue) > 0);
		}
		// Object
		return isNotEmpty(validValue.toString());
	}

	/**
	 * 대상 객체가 "", <code>null</code> 또는 길이가 0 인지를 확인한다.
	 * <code>CharSequence, Number, Enumeration, Iterator, Array</code>를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isEmpty(final Object validValue) {
		return !(isNotEmpty(validValue));
	}

	/**
	 * 대상 문자열이 "", <code>null</code> 이 아닌지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isNotEmpty(final CharSequence validValue) {
		boolean isNotEmpty = false;
		if(validValue != null && validValue.length() > 0) 
			isNotEmpty = true;
		return isNotEmpty;
	}

	/**
	 * 대상 문자열이 "", <code>null</code> 인지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isEmpty(final CharSequence validValue) {
		return (validValue == null || validValue.length() <= 0);
	}

	/**
	 * 대상 숫자가 <code>null</code>이 아니고 0보다 큰지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isNotEmpty(final Number validValue) {
		return (validValue != null && validValue.intValue() > 0);
	}

	/**
	 * 대상 숫자가 <code>null</code> 또는 0 이하 인지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isEmpty(final Number validValue) {
		return (validValue == null || validValue.intValue() <= 0);
	}

	/**
	 * 대상 <code>Collection</code>이 <code>null</code>이 아니고, size가 0보다 큰지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isNotEmpty(final Collection<?> validValue) {
		return (validValue != null && validValue.size() > 0);
	}

	/**
	 * 대상 <code>Collection</code>이 <code>null</code> 또는 size가 0 인지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isEmpty(final Collection<?> validValue) {
		return (validValue == null || validValue.size() <= 0);
	}

	/**
	 * 대상 <code>Map</code>이 <code>null</code>이 아니고, 값이 있는지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isNotEmpty(final Map<?, ?> validValue) {
		return (isNotNull(validValue) && !validValue.isEmpty());
	}

	/**
	 * 대상 <code>Map</code>이 <code>null</code> 또는 값이 없는지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isEmpty(final Map<?, ?> validValue) {
		return (isNull(validValue) || validValue.isEmpty());
	}

	/**
	 * 대상 <code>Object[]</code> 배열이 <code>null</code>이 아니고, length가 0보다 큰지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isNotEmpty(final Object[] validValue) {
		return (validValue != null && validValue.length > 0);
	}

	/**
	 * 대상 <code>Object[]</code>이 <code>null</code> 또는 size가 0 인지를 확인한다.
	 * @param validValue
	 * @return
	 */
	public static boolean isEmpty(final Object[] validValue) {
		return (validValue == null || validValue.length <= 0);
	}

	/**
	 * <code>Collection, Array, Enumeration, Iterator, Map</code>의 지정 Size 범위와 일치하는지 확인
	 * @param validValue
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isSizeMatch(final Object validValue, int min, int max) {
		if(validValue == null) {
			return false;
		}
		int size = 0;
		// Map
		if(validValue instanceof Map) {
			Map<?, ?> map = (Map<?, ?>)validValue;
			size = map.size();
		}
		// Collection
		else if(validValue instanceof Collection) {
			Collection<?> collection = (Collection<?>)validValue;
			size = collection.size();
		}
		// Enumeration
		else if(validValue instanceof Enumeration) {
			Enumeration<?> enumer = (Enumeration<?>)validValue;
			size = 0;
			while(enumer.hasMoreElements()) {
				size++;
				enumer.nextElement();
			}
		}
		// Iterator
		else if(validValue instanceof Iterator) {
			Iterator<?> iter = (Iterator<?>)validValue;
			size = 0;
			while(iter.hasNext()) {
				size++;
				iter.next();
			}
		}
		// Array 배열
		else if(validValue.getClass().isArray()) {
			try {
				size = Array.getLength(validValue);
			} catch(IllegalArgumentException e) {
				log.debug("isSizeMatch Array Error : {}", e);
			}
		}
		// 기타 String Integer 등
		else {
			size = validValue.toString().length();
		}
		if(max < 0 && size >= min) {
			return true;
		}
		if(max >= 0 && size >= min && size <= max) {
			return true;
		}
		return false;
	}

	/**
	 * <code>URL</code> 형식과 일치하는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean isUrl(final CharSequence validValue) {
		String value = validValue.toString();
		boolean match = value.matches("(http|https|ftp)://[^\\s^\\.]+(\\.[^\\s^\\.]+)*");
		if(match) {
			return true;
		}
		return false;
	}

	/**
	 * 공백 문자가 포함되어 있지 않으면 참
	 * @param validValue
	 * @return
	 */
	public static boolean hasNoWhiteSpace(final CharSequence validValue) {
		return !(hasWhitespace(validValue));
	}

	/**
	 * 공백 문자가 포함되어 있는지 확인
	 * @param validValue
	 * @return
	 */
	public static boolean hasWhitespace(final CharSequence validValue) {
		if(validValue == null) {
			return false;
		}
		int size = validValue.length();
		for(int i = 0; i < size; i++) {
			if(Character.isWhitespace(validValue.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 문자열을 DB 입력시 길이에 맞도록 byte로 길이를 확인
	 * @param validValue
	 * @return
	 */
	public static int getByteLength(final CharSequence validValue) {
		String str = validValue.toString();
		if(isEmpty(str)) {
			return 0;
		}
		byte[] bt = str.getBytes();
		return bt.length;
	}

	/**
	 * 대상 Map에 keys에 해당하는 값이 존재하는지 여부 확인
	 * @param map
	 * @param keys
	 * @return
	 */
	public static boolean hasValues(Map<String, Object> map, String... keys) {
		boolean hasValues = true;
		for(String key : keys) {
			if(Validate.isEmpty(map.get(key))) {
				return false;
			}
		}
		return hasValues;
	}

}
