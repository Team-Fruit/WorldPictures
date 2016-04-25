package com.kamesuta.mc.worldpictures.net;

import com.kamesuta.mc.worldpictures.reference.Reference;

public class NetProcessor extends Thread {
	private final NetManager manager;

	public NetProcessor(final String threadname, final NetManager manager) {
		setName(threadname + getId());
		this.manager = manager;
	}

	@Override
	public void run() {
		NetTask task;
		while ((task = this.manager.tasks.poll()) != null) {
			Reference.logger.info(task);
			try {
				task.processTask(this.manager.client);
			} catch (final Exception e) {
				Reference.logger.warn("Failed to download :", e);
			}
		}
	}
}
