package com.kamesuta.mc.worldpictures.objects;

import com.kamesuta.mc.worldpictures.texture.WorldTextureManager;
import com.kamesuta.mc.worldpictures.vertex.WorldVertexManager;

public class WorldObjClient extends WorldObj {
	public WorldObjClient(WorldObj obj) {
		super(obj);
	}

	public void render(WorldTextureManager textureManager, WorldVertexManager vertexManager) {
		textureManager.bindTexture(picture);
		vertexManager.drawVertex(picture);
	}
}
