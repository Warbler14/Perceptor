package com.vision.perceptor.task;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WatcherWork implements Work{

	static final Logger logger = LoggerFactory.getLogger(WatcherWork.class);
	
	public boolean execute = false;
	private String watchDir;
	private static Path sharedDirectoryPath;
	private static WatchKey watchKey;
	private static WatchService watchService;
	
	public String getWatchDir() {
		return watchDir;
	}

	public void setWatchDir(String watchDir) {
		this.watchDir = watchDir;
	}

	@Override
	public void initalize() {
		watchDir = "C:\\Intel\\Logs";
		sharedDirectoryPath = Paths.get(watchDir);
		
		try {
			watchService = FileSystems.getDefault().newWatchService();
			watchKey = sharedDirectoryPath.register(watchService, 
					StandardWatchEventKinds.ENTRY_CREATE, 
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		
	}
	
	@Override
	public void run() {
		initalize();
		execute = true;
		while(execute) {
			List<WatchEvent<?>> events = watchKey.pollEvents();
			
			for (WatchEvent<?> event: events) {
				Kind<?> kind = event.kind();
				Path paths = (Path)event.context();
				
				logger.info("paths : " + paths.getFileName());
				logger.info("kind : " + kind);

			}
		}
		execute = false;
	}


	@Override
	public void stop() {
		execute = false;
		
	}

}
