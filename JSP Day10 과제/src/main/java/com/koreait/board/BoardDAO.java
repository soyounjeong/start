package com.koreait.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.koreait.db.Dbconn;
import com.koreait.db.SqlMapConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.koreait.reply.replyDAO;
import com.koreait.board.BoardDTO;

public class BoardDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	List<BoardDTO> boardList = new ArrayList<>();

	SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;

	public BoardDAO() {
		sqlsession = ssf.openSession(true);	// openSession(true) 설정시 자동 commit
		System.out.println("마이바티스 설정 성공!");
	}

	// write.jsp
	public int write_ok(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("b_userid", board.getUserid());
		dataMap.put("b_title", board.getTitle());
		dataMap.put("b_content", board.getContent());
		dataMap.put("b_file", board.getFile());
		return sqlsession.insert("Board.write_ok", dataMap);
	}

	// like_ok.jsp
	public BoardDTO like_ok(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>(); // 객체 생성
		dataMap.put("b_idx" , String.valueOf(board.getIdx()));
		sqlsession.update("Board.update_like_ok", dataMap); // BoardDAO update 구문

		dataMap = sqlsession.selectOne("Board.like_ok", dataMap); // BoardDao select 구문
		if(dataMap != null){
			board.setLike((Integer.parseInt(String.valueOf(dataMap.get("b_like"))))); // String.valueof를 해줘야 값이 들어감!!!!
			// String.valueof : string으로바꿀때 값이 비어있는 null로 출력
			return board;
		}
		return null;
	}

	// edit.jsp
	public BoardDTO B_edit(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		if(dataMap != null){
			board.setTitle(dataMap.get("b_title"));
			board.setContent(dataMap.get("b_content"));
			board.setFile(dataMap.get("b_file"));
			return board; // board.xml select 구문
		}
		return null;
	}

	// edit_ok.jsp
	public int B_edit_ok(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>();
		if(dataMap != null){
			if(board.getFile() != null && !board.getFile().equals("")){
				dataMap.put("b_title", board.getTitle());
				dataMap.put("b_content", board.getContent());
				dataMap.put("b_file", board.getFile());
				dataMap.put("b_idx", String.valueOf(board.getIdx()));
				return sqlsession.update("Board.B_edit_ok1", dataMap); // board.xml update 구문
			}else{
				dataMap.put("b_title", board.getTitle());
				dataMap.put("b_content", board.getContent());
				dataMap.put("b_idx", String.valueOf(board.getIdx()));
				return sqlsession.update("Board.B_edit_ok2", dataMap); // board.xml update 구문
			}
		}
		return 0;
	}

	// view.jsp
	public BoardDTO view(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		sqlsession.update("Board.update_view", dataMap);
		dataMap = sqlsession.selectOne("Board.select_view", dataMap);
		if(dataMap != null){

			board.setUserid(dataMap.get("b_userid"));
			board.setTitle(dataMap.get("b_title"));
			board.setContent(dataMap.get("b_content"));
			board.setRegdate(String.valueOf(dataMap.get("b_regdate")));
			board.setLike((Integer.parseInt(String.valueOf(dataMap.get("b_like")))));
			board.setHit(Integer.parseInt(String.valueOf(dataMap.get("b_hit"))));
			board.setFile(dataMap.get("b_file"));
			return board;
		}
		return null;
	}

	// delete.jsp
	public int del(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		return sqlsession.delete("Board.del", dataMap);
	}


	// list.jsp
	public List<BoardDTO> list(int start, int pagePerCount){
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("start", String.valueOf(start));
		dataMap.put("pagePerCount", String.valueOf(pagePerCount));
		return sqlsession.selectList("Board.list", dataMap);
	}

	// list.jsp : 댓글 갯수
	public String replycnt(int idx){
		String replycnt_str = "";
		int replycnt = 0;
		HashMap<String,String> dataMap = new HashMap<>();
		dataMap.put("b_idx", String.valueOf(idx));
		dataMap = sqlsession.selectOne("Board.replycnt", dataMap);

		if(dataMap != null){
			replycnt = Integer.parseInt(String.valueOf(dataMap.get("replycnt")));
			if(replycnt > 0){
				replycnt_str = "[" + replycnt + "]";
			}
		}
		return replycnt_str;
	}

	// list.jsp : 글 갯수
	public int totalCount(BoardDTO board){
		HashMap<String, String> dataMap = new HashMap<>();
		dataMap.put("b_idx", String.valueOf(board.getIdx()));
		return sqlsession.selectOne("Board.totalCount", dataMap);
	}
}

