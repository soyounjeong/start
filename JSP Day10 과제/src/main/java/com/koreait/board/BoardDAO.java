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
			board.setLike(Integer.parseInt(dataMap.get("b_like")));
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
		if(dataMap != null){
			dataMap.put("b_hit", String.valueOf(board.getHit()));
			dataMap.put("b_idx", String.valueOf(board.getIdx()));
			sqlsession.update("Board.update_view", dataMap); //

			dataMap = sqlsession.selectOne("Board.select_view", dataMap);
			dataMap.put("b_idx", String.valueOf(board.getIdx()));
			dataMap.put("b_userid", board.getUserid());
			dataMap.put("b_title", board.getTitle());
			dataMap.put("b_content", board.getContent());
			dataMap.put("b_regdate", board.getRegdate());
			dataMap.put("b_like", String.valueOf(board.getLike()));
			dataMap.put("b_hit", String.valueOf(board.getHit()));
			dataMap.put("b_file",board.getFile());
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
}
