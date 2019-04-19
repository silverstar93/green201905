package service_p;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_p.BoardDAO;
import di.MvcAction;
import di.MvcForward;

public class List implements MvcAction {

	@Override
	public MvcForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		
		int limit = 3;	//한 페이지 당 게시물 수
		int pageLimit = 4 ; //리스트 하단에 보여질 페이지번호 갯수
		
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		
		
		BoardDAO dao = new BoardDAO();
		
		///전체글수 가져오기
		int total = dao.total();
		
		///전체페이지수
		int totalPage = total/limit;
		
		if(total%limit>0)
			totalPage++;
		
		
		int startPage = (page-1)/pageLimit*pageLimit+1;
		int endPage = startPage+pageLimit-1;
		
		if(endPage>totalPage)
			endPage=totalPage;
		
		
		
		int start = (page-1)*limit;
		
		request.setAttribute("data",dao.list(start, limit));
		
		request.setAttribute("start",start);
		request.setAttribute("nowPage",page);
		request.setAttribute("startPage",startPage);
		request.setAttribute("endPage",endPage);
		request.setAttribute("totalPage",totalPage);
		
		System.out.println("리스트 서비스 진입");
		
		
		
		return null;
	}

}
