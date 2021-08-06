package com.koreait.board;

public class BoardDTO {
	private int idx;
	private String userid;
	private String title;
	private String content;
	private int hit;
	private int b_like;
	private String regdate;
	private String file;
	private String name;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getLike() {
		return b_like;
	}

	public void setLike(int like) {
		this.b_like = like;
	}

	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BoardDTO{" +
				"idx=" + idx +
				", userid='" + userid + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", hit=" + hit +
				", b_like=" + b_like +
				", regdate='" + regdate + '\'' +
				", file='" + file + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
