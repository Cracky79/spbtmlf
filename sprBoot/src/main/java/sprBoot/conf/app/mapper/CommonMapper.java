package sprBoot.conf.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import sprBoot.logsynk.comm.util.ParamUtil;

/**
 * Mybatis Mapper 처리 클래스  ( DAO )
 * 2025.06.17
 * @author Cracky
 *
 */
@Mapper
@Repository
public interface CommonMapper {
	
	/** 관리자 메뉴 목록 */
	List <Map> selMngrMenuList ( ParamUtil param ) throws Exception;
	 
	/** sample grid data */
	List <Map> selTestGridData (Map param) throws Exception;
	
}
