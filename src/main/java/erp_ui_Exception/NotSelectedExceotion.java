package erp_ui_Exception;

@SuppressWarnings("serial")
public class NotSelectedExceotion extends RuntimeException {

	public NotSelectedExceotion(Throwable cause) {
		super("목록을 선택하세요.", cause);
	}

	public NotSelectedExceotion() {
		super("목록을 선택하세요");
	}

}
