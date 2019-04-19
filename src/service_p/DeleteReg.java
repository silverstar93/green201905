package service_p;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.BoardDAO;
import db_p.BoardDTO;
import di.MvcAction;
import di.MvcForward;

public class DeleteReg implements MvcAction {

	@Override
	public MvcForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String path = request.getRealPath("/fff");
		path = "D:\\lee\\lee_public\\utf8Work\\mvcProj\\WebContent\\fff";
		
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBid(Integer.parseInt(request.getParameter("bid")));
		dto.setPw(request.getParameter("pw"));
		
		
		String msg = "암호가 일치하지 않습니다.";
		String goUrl = "DeleteForm?bid="+dto.getBid();
		
		
		BoardDTO dto2 = new BoardDAO().fileDelete(dto);
		if(dto2!=null && dto2.getUpfile()!=null) {
			
			///파일삭제
			File ff = new File(path+"\\"+dto2.getUpfile());
			ff.delete();
		}
		

		if(new BoardDAO().delete(dto)) {
			msg = "삭제되었습니다.";
			goUrl = "List";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("goUrl", goUrl);
		request.setAttribute("mainUrl", "board/alert.jsp");
		
		
		return null;
	}

}
