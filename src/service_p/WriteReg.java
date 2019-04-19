package service_p;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.BoardDAO;
import db_p.BoardDTO;
import di.MvcAction;
import di.MvcForward;

public class WriteReg implements MvcAction {

	@Override
	public MvcForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String path = request.getRealPath("/fff");
		path = "D:\\lee\\lee_public\\utf8Work\\mvcProj\\WebContent\\fff";
		
		try {
			MultipartRequest mm = new MultipartRequest(
					request,
					path,
					10*1024*1024,
					"utf-8",
					new DefaultFileRenamePolicy()
					);
			
			BoardDTO dto = new BoardDTO();
			dto.setTitle(mm.getParameter("title"));
			dto.setPname(mm.getParameter("pname"));
			dto.setPw(mm.getParameter("pw"));
			dto.setContent(mm.getParameter("content"));
			dto.setUpfile(mm.getFilesystemName("upfile"));
			
			new BoardDAO().write(dto);
			
			request.setAttribute("msg", "작성되었습니다.");
			request.setAttribute("goUrl", "Detail?bid="+dto.getBid());
			request.setAttribute("mainUrl", "board/alert.jsp");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return null;
	}

}
