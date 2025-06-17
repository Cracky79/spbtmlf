package sprBoot.mngr.dash.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sprBoot.conf.comm.constant.Constants;
import sprBoot.conf.comm.web.CommController;
import sprBoot.logsynk.comm.util.ParamUtil;

/**
 * 관리자 대시보드 컨트롤러
 * 25.06.16
 * @author cracky
 *
 */
@Controller
@RequestMapping("/mngr/dash/")
public class MngrDashboardController extends CommController {
	
	
	/**
	 * 대시보드 메인 
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/dashboard.do")
	public String selDashboard ( ParamUtil param , Model model ) throws Exception {
		return getForwardPageFromUrl(Constants.THYME_LEAF_DEFAULT_LAYOUT , param.getHttpServletrequestuest() ); 
	}
}
