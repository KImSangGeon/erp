package erp_ui_content;

import javax.swing.JPanel;

import erp_ui_service.TitleService;

public abstract class AbstractContentPanel<T> extends JPanel{


	
	
	public abstract void setItem(T t);
	public abstract T getItem();
	public abstract void validCheck();
	public abstract	void clearTf();
	
}
