package main.at.fhv.itb5.systemarchitecture.ue3;

import main.at.fhv.itb5.systemarchitecture.ue2.filter.SaveFastForwardFilter;

public class SaveFastForward extends AbstractFilterBean {
	private static final long serialVersionUID = 1L;
	
	public String _path;

	public SaveFastForward() {
		super();
		_path = null;
		setJaiPlanarImageFilter(new SaveFastForwardFilter(_path, this));
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
		setJaiPlanarImageFilter(new SaveFastForwardFilter(_path, this));
	}
	
}
