package sprBoot.conf.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import joy.logsynk.file.FileInfoBean;
import joy.logsynk.file.FilesUtil;
import sprBoot.conf.comm.constant.Constants;
import sprBoot.conf.comm.util.Function;
import sprBoot.logsynk.comm.util.ParamUtil;
import sprBoot.conf.comm.util.Validate;
import sprBoot.conf.comm.web.CommController;
import sprBoot.conf.app.service.CommonService;

@Controller
//@EnableAutoConfiguration
@RequestMapping("/test/")
public class TestController extends CommController {
	
	@Autowired
	private CommonService service;
	
	/**
	 * reponse 예쩨
	 * @return
	 */
	@RequestMapping("smple.do")
	@ResponseBody
	public Map <String,String> selSmple () {
		Map <String,String> map = new HashMap();
		map.put("test", "message do");
		return map;
	}
	
	/**
	 * JTable 리스트 페이지 (jsp)
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("jtable.do")
	public String sss (ParamUtil param , Model model) throws Exception {
		return tilesResultPage(param);
	}
	
	/**
	 * JTable Ajax 처리 예제 
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("jtableAjax.do")
	@ResponseBody
	public Map <String,Object> sssAjax (ParamUtil param , Model model) throws Exception {
		setJTableParam(param);
		List <Map> result = service.selTestGridData(param);
		return this.responseJTableBody(result, param);
	}
	
	/**
	 * FileUpload 예제
	 * @param param
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("fileUpload.do")
	public String fileUpload (ParamUtil param , Model model) throws Exception {
		param.printParams();
		Map < String ,  List<FileInfoBean> > fileMap  = FilesUtil.uploadFileMap(param.getHttpServletrequestuest(), "c:/root");
		System.out.println(fileMap);
		if ( Validate.isNotEmpty(fileMap) ) {
			/** 메인 이미지 업로드시 */
			if ( Validate.isNotEmpty(fileMap.get("sx")) ) {
				List <FileInfoBean> lfib  = fileMap.get("sx");	
				System.out.println(lfib.get(0).getUploadDirectory());
				System.out.println(lfib.get(0).getRealFileName());
			}
		}
		param.printParams();
		return "redirect:jtable.do";
	}
}
