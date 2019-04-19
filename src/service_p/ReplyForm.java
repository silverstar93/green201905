package service_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.BoardDAO;
import db_p.BoardDTO;
import di.MvcAction;
import di.MvcForward;

public class ReplyForm implements MvcAction {

	@Override
	public MvcForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		BoardDTO dto = new BoardDTO();
		dto.setBid(Integer.parseInt(request.getParameter("bid")));
		
		BoardDAO dao = new BoardDAO();
		
		request.setAttribute("dto", dao.detail(dto));
		
		dao.close();
		
		return null;
	}

}
