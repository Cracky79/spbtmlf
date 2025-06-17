package sprBoot.mngr.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sprBoot.mngr.login.dto.MemberDto;
import sprBoot.mngr.login.mapper.MngrLoginMapper;

/**
 * 관리자 로그인 서비스 
 * 25.06.16
 * @author cracky
 *
 */
@Service("mngrLoginService")
@Transactional
public class MngrLoginService {
	
	@Autowired
	MngrLoginMapper mapper;
	
	/**
	 * 관리자 로그인 정보 ( mber_id )
	 * @param mberDto
	 * @throws Exception
	 */
	public MemberDto selMberUserInfo ( MemberDto mberDto ) throws Exception {
		return mapper.selMberUserInfo(mberDto);
	}
}
