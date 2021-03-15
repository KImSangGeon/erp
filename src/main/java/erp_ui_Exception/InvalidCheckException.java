package erp_ui_Exception;

@SuppressWarnings("serial")
public class InvalidCheckException extends RuntimeException {
	
	public InvalidCheckException() {
		super("공백이 존재합니다.");
	}
	public InvalidCheckException(Throwable cause) {
		super("목록을 선택하세요.", cause);
	}
	
	
}





