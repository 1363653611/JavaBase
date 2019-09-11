package com.zbcn.pattern.observer;

public interface Watchered {
	
	public void addWatcher(Watcher watcher);
	
	public void removeWatcher(Watcher watcher);
	
	public void notifyWatchers();
	
}
