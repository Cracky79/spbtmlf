package sprBoot.conf.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mybatis Mapper 처리 클래스  ( DAO )
 * 2025.06.17
 * @author Cracky
 *
 */
@Mapper
@Repository
public interface CommonMapper {
	List <Map> selTestGridData (Map param) throws Exception;
}
