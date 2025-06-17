package sprBoot.conf.comm.resolver;

import javax.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sprBoot.logsynk.comm.util.ParamUtil;


/**
 * request 매개 변수에 ParamUtil 을 추가 하여 처리 
 * @author cracky
 * 2025.06.12
 *
 */
@Component
public class ParamUtilHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	
	/**
	 * 바인딩 클래스 지정 
	 */
	@Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(ParamUtil.class);
    }
	
	/**
	 * Map || ParamUtil 반
	 */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, 
    		                      ModelAndViewContainer modelAndViewContainer, 
    		                      NativeWebRequest nativeWebRequest, 
    		                      WebDataBinderFactory webDataBinderFactory) throws Exception {
	    HttpServletRequest request = (HttpServletRequest)  nativeWebRequest.getNativeRequest();   
	    ParamUtil param = new ParamUtil(request);
		return param;  
    }
    
}
