package com.zbcn.pattern.observer;

import java.util.ArrayList;
import java.util.List;


/**        
 * Title: Transporter.java
 * <p>    
 * Description: 具体的被观察者
 * @author likun       
 * @created 2018-3-15 下午6:29:45
 * @version V1.0
 */ 
public class Transporter implements Watchered {
	
	private List<Watcher> list = new ArrayList<>();

	public void addWatcher(Watcher watcher) {
		list.add(watcher);
	}

	public void removeWatcher(Watcher watcher) {
		list.remove(watcher); 

	}

	public void notifyWatchers() {
		for (Watcher watcher : list)  
        {  
             watcher.update();  
        }  
	}

}
