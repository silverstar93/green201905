package service_p;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import db_p.BoardDAO;
import db_p.BoardDTO;
import di.MvcAction;
import di.MvcForward;

public class FileDelete implements MvcAction {

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
			
			int page = 1;
			if(mm.getParameter("page")!=null) {
				page = Integer.parseInt(mm.getParameter("page"));
			}
			
			BoardDTO dto = new BoardDTO();
			
			dto.setBid(Integer.parseInt(mm.getParameter("bid")));
			dto.setTitle(mm.getParameter("title"));
			dto.setPname(mm.getParameter("pname"));
			dto.setPw(mm.getParameter("pw"));
			dto.setContent(mm.getParameter("content"));
			//dto.setUpfile(mm.getFilesystemName("upfile"));
			
			String msg = "암호가 일치하지 않습니다.";
			BoardDTO dto2 = new BoardDAO().fileDelete(dto);
			if(dto2!=null) {
				
				///파일삭제
				File ff = new File(path+"\\"+dto2.getUpfile());
				ff.delete();
				msg = "파일이 삭제되었습니다.";
				
			}else {
				dto.setUpfile(mm.getParameter("upfile"));
			}
			
			
			request.setAttribute("dto", dto);			
			request.setAttribute("msg", msg);
			
			request.setAttribute("nowPage", page);
			
			request.setAttribute("mainUrl", "board/ModifyForm.jsp");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return null;
	}

}
