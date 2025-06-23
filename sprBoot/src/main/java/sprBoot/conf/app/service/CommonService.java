package sprBoot.conf.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sprBoot.logsynk.comm.util.ParamUtil;
import sprBoot.conf.app.mapper.CommonMapper;

/**
 * 공통 서비스 처리 
 * 25.06.17
 * @author cracky
 *
 */
@Service
@Transactional
public class CommonService {
	
	@Autowired
	private CommonMapper mapper;
	
	
	/**
	 * 권한 및 그룹에 따른 관리자 초기 메뉴 목록
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List <Map> selMngrMenuList ( ParamUtil param ) throws Exception {
		return mapper.selMngrMenuList(param.getParameterMap());
	}
	
	/**
	 * jTable Grid 테스트
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List <Map> selTestGridData (ParamUtil param) throws Exception {
		return mapper.selTestGridData(param.getParameterMap());
	}
}
