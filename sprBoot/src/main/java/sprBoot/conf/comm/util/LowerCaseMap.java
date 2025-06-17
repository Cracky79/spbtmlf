package sprBoot.conf.comm.util;

import java.util.HashMap;

import org.apache.ibatis.type.Alias;
/**
 * 
 * mybatis resultType for lowercase key
 * @author Cracky
 *
 */
@Alias("lmap")
public class LowerCaseMap extends HashMap<String, Object> {
	
	public Object put(String paramName, Object paramValue) {
		return super.put(paramName.toLowerCase(), paramValue);
	}
}
