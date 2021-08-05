package com.koreait.reply;

import com.koreait.db.SqlMapConfig;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;

public class replyDAO {
    SqlSessionFactory ssf = SqlMapConfig.getSqlMapInstance();
    SqlSession sqlsession;

    public replyDAO(){
        sqlsession = ssf.openSession(true);	// openSession(true) 설정시 자동 commit
        System.out.println("마이바티스 설정 성공!");
    }

    // reply_ok.jsp
    public int reply_ok(replyDTO reply){
        HashMap<String,String> dataMap = new HashMap<>();
        dataMap.put("re_userid", reply.getUserid());
        dataMap.put("re_content", reply.getContent());
        dataMap.put("re_boardidx", String.valueOf(reply.getR_idx()));
        return sqlsession.insert("Reply.reply_ok", dataMap);
    }
}
