package io;

import java.util.List;

public interface Save {

	String saveAs();

	void save(String path, List<Object> list);
}
