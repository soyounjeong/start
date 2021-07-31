package com.koreait.member;

import con.koreait.db.Dbconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;
    String sql = "";

    // write_ok.jsp : 글쓰기
    public int write_ok(BoardDTO board){
        try{
            sql = "insert into tb_board(b_userid, b_title, b_content) values (?, ?, ?)";
            conn = Dbconn.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getUserid());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            if(pstmt.executeUpdate() >0){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // delete.jsp : 삭제
    public int del(BoardDTO board){
        try{
            conn = Dbconn.getConnection();
            if(conn != null){
                sql = "delete from tb_board where b_idx=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, board.getIdx());
                if(pstmt.executeUpdate() >= 1){
                    return 1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0; // DB 오류 발생
    }

    //edit.jsp : 수정
    public BoardDTO B_edit(BoardDTO board){
        try{
            sql = "select b_title, b_content, b_file from tb_board where b_idx=?";
            conn = Dbconn.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, board.getIdx());
            rs = pstmt.executeQuery();
            if(rs.next()){
                board.setTitle(rs.getString("'b_title"));
                board.setContent(rs.getString("'b_content"));
                board.setFile(rs.getString("'b_file"));
                return board;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    // edit_ok.jsp : 수정
    public int B_edit_ok(BoardDTO board){
        try {
            sql = "update tb_board set b_userid=?, b_title=?, b_content=?, b_file=? where b_idx=?";
            conn = Dbconn.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getUserid());
            pstmt.setString(2, board.getTitle());
            pstmt.setString(3, board.getContent());
            pstmt.setString(4, board.getFile());
            pstmt.setInt(5, board.getIdx());
            if(pstmt.executeUpdate() > 0) {
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    //like.jsp : 좋아요
    public BoardDTO like(BoardDTO board){
        try{
            conn = Dbconn.getConnection();
            if(conn != null){
                sql = "update tb_board set b_like = b_like + 1 where b_idx=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, board.getIdx());
                pstmt.executeUpdate();

                sql = "select b_like from tb_board where b_idx=?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, board.getIdx());
                rs = pstmt.executeQuery();
                if(rs.next()){
                    board.setLike(rs.getString("b_like"));
                    return board;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
