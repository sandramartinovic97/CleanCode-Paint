package io;

import java.util.List;

public class SaveManager implements Save {
	private Save saver;
	
	public SaveManager() {}

	public SaveManager(Save saver) {
		this.saver = saver;
	}

	public void setSaver(Save saver) {
		this.saver = saver;
	}

	public Save getSaver() {
		return this.saver;
	}

	@Override
	public String saveAs() {
		return saver.saveAs();
	}

	@Override
	public void save(String path, List<Object> objects) {
		saver.save(path, objects);
	}

}
