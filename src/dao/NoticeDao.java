package dao;

import model.Notice;

public interface NoticeDao {
	public Notice[] getAll(String userId);
	public Notice[] getUnread(String userId);
	public boolean send(Notice notice);
	
	public boolean read(Notice[] arr);
}
