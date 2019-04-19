package service_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.BoardDAO;
import db_p.BoardDTO;
import di.MvcAction;
import di.MvcForward;

public class ModifyForm implements MvcAction {

	@Override
	public MvcForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		BoardDTO dto = new BoardDTO();
		dto.setBid(Integer.parseInt(request.getParameter("bid")));
		
		BoardDAO dao = new BoardDAO();
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		request.setAttribute("dto", dao.detail(dto));
		request.setAttribute("nowPage", page);
		dao.close();
		
		return null;
	}

}
