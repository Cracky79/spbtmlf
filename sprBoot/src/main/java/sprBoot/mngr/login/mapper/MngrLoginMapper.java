package sprBoot.mngr.login.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import sprBoot.mngr.login.dto.MemberDto;

/**
 * 관리자 로그인 mapper ( DAO )
 * 2025.06.16
 * mngr-login-sql.xml
 * @author Cracky
 *
 */
@Mapper
@Repository
public interface MngrLoginMapper {
	
	/** mber_id를 통한 사용자 정보 반환  */
	MemberDto selMberUserInfo ( MemberDto mberDto ) throws Exception;
}
