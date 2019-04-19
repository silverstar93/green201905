package db_p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	
	Connection con;
	PreparedStatement ptmt;
	ResultSet rs;
	String sql;
	
	public BoardDAO() {
		// TODO Auto-generated constructor stub
		try {
			DataSource ds = (DataSource)new InitialContext().lookup("java:comp/env/ggggg");
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	public int total() {
		
		int res = 0;
		
		try {
			sql = "select count(*) from board";
			
			ptmt = con.prepareStatement(sql);

			rs = ptmt.executeQuery();
			
			rs.next();
				
			res = rs.getInt(1);		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}

		return res;
	}
	
	
	
	public Object list(int page, int limit) {
		ArrayList<BoardDTO> res = new ArrayList<BoardDTO>();
		
		
		
		try {
			sql = "select * from board order by gid desc , seq limit ? , ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, page);
			ptmt.setInt(2, limit);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBid(rs.getInt("bid"));
				dto.setGid(rs.getInt("gid"));
				dto.setSeq(rs.getInt("seq"));
				dto.setLevel(rs.getInt("level"));
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setPname(rs.getString("pname"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				res.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}
		
		
		return res;
	}
	
	
	public void addCount(BoardDTO dto) {
		
		sql = "update board set no = no + 1 where  bid = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.bid);
			ptmt.executeUpdate();
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	
	
	public BoardDTO detail(BoardDTO dto) {
		BoardDTO res = null;
		
		sql = "select * from board where  bid = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.bid);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new BoardDTO();
				res.setBid(rs.getInt("bid"));
				res.setGid(rs.getInt("gid"));
				res.setSeq(rs.getInt("seq"));
				res.setLevel(rs.getInt("level"));
				res.setNo(rs.getInt("no"));
				res.setTitle(rs.getString("title"));
				res.setPname(rs.getString("pname"));
				res.setRegdate(rs.getTimestamp("regdate"));
				res.setUpfile(rs.getString("upfile"));
				res.setContent(rs.getString("content"));
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

		return res;
	}
	
	
	
	public BoardDTO fileDelete(BoardDTO dto) {
		BoardDTO res = null;
		
		sql = "select * from board where  bid = ? and pw = ?";
		
		try {
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.bid);
			ptmt.setString(2, dto.pw);
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				res = new BoardDTO();
				res.setBid(rs.getInt("bid"));
				res.setGid(rs.getInt("gid"));
				res.setSeq(rs.getInt("seq"));
				res.setLevel(rs.getInt("level"));
				res.setNo(rs.getInt("no"));
				res.setTitle(rs.getString("title"));
				res.setPname(rs.getString("pname"));
				res.setRegdate(rs.getTimestamp("regdate"));
				res.setUpfile(rs.getString("upfile"));
				res.setContent(rs.getString("content"));
				
				
				sql = "update board set upfile = null where bid = ?";
				
				ptmt = con.prepareStatement(sql);
				
				ptmt.setInt(1, dto.getBid());
				
				ptmt.executeUpdate(); 
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}

		return res;
	}
	
	
	
	public void write(BoardDTO dto) {
		
		
		try {
			
			sql = "insert into board "
			+ "(seq, level, no, title, pname, pw, content, upfile, regdate ) values "
			+ "(0    ,  0  ,-1,  ? ,  ? ,   ? ,  ? , ? ,  sysdate() )";
			
			ptmt = con.prepareStatement(sql);
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getPw());
			ptmt.setString(4, dto.getContent());
			ptmt.setString(5, dto.getUpfile());
			ptmt.executeUpdate();
			
			sql = "select max(bid) from board";
			
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			dto.setBid(rs.getInt(1));
			
			
			sql = "update board set gid = bid where  bid = ?";
			ptmt = con.prepareStatement(sql);
			ptmt.setInt(1, dto.bid);
			ptmt.executeUpdate();

					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}

	}
	
	
	
	
	public boolean delete(BoardDTO dto) {
		
		boolean res = false;
		
		try {
			
			sql = "delete from board where bid = ? and pw = ? ";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, dto.getBid());
			ptmt.setString(2, dto.getPw());
			
			res = ptmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}
		
		
		return res;

	}
	
	
	public boolean modify(BoardDTO dto) {
		
		boolean res = false;
		
		try {
			
			sql = "update board set title = ?, pname = ?, content = ?, no = no-1 , upfile = ?"
					+ " where bid = ? and pw = ? ";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setString(1, dto.getTitle());
			ptmt.setString(2, dto.getPname());
			ptmt.setString(3, dto.getContent());
			ptmt.setString(4, dto.getUpfile());
			ptmt.setInt(5, dto.getBid());
			ptmt.setString(6, dto.getPw());
			
			res = ptmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}
		
		
		return res;

	}
	
	
	
	
	public void reply(BoardDTO dto) {
		
		
		try {
			
			BoardDTO ori = detail(dto);
			
			sql = "update board set seq = seq +1 where gid = ? and seq > ?";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.gid);
			ptmt.setInt(2, ori.seq);
			
			ptmt.executeUpdate();
			
			sql = "insert into board "
			+ "(gid, seq, level, no, title, pname, pw, content, regdate ) values "
			+ "( ?,   ?,    ?  ,-1,   ? ,    ? ,    ? ,  ? ,   sysdate() )";
			
			ptmt = con.prepareStatement(sql);
			
			ptmt.setInt(1, ori.gid);
			ptmt.setInt(2, ori.seq+1);
			ptmt.setInt(3, ori.level+1);
			ptmt.setString(4, dto.getTitle());
			ptmt.setString(5, dto.getPname());
			ptmt.setString(6, dto.getPw());
			ptmt.setString(7, dto.getContent());
			
			ptmt.executeUpdate();
			
			
			sql = "select max(bid) from board";
			
			ptmt = con.prepareStatement(sql);
			
			rs = ptmt.executeQuery();
			
			rs.next();
			
			dto.setBid(rs.getInt(1));
			
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close();
		}

	}
	
	
	
	
	public void close() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {	}
		if(ptmt!=null) try {ptmt.close();} catch (SQLException e) {	}
		if(con!=null) try {con.close();} catch (SQLException e) {	}
	}

}
