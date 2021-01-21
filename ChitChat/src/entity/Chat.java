package entity;


public class Chat {
	private int chatId;
	private User msgFrom;
	private User msgTo;
	private String msgTime;
	private String message;
	
	public Chat(){
		this.setMsgFrom(new User());
		this.setMsgTo(new User());
	}
	
	public int getChatId() {
		return chatId;
	}
	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	public User getMsgFrom() {
		return msgFrom;
	}
	public void setMsgFrom(User msgFrom) {
		this.msgFrom = msgFrom;
	}
	public User getMsgTo() {
		return msgTo;
	}
	public void setMsgTo(User msgTo) {
		this.msgTo = msgTo;
	}
	public String getMsgTime() {
		return msgTime;
	}
	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}
	public String getMessage() {
		return message;
		
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {		
		return this.getMessage()+"::"+this.getMsgFrom().getUserid()+"->"+this.getMsgTo().getUserid();
	}
}
