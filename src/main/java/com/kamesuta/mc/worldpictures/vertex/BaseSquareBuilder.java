package com.kamesuta.mc.worldpictures.vertex;

import java.util.ArrayList;

/**
 * 編集データの具体的な操作、保持、管理をします。
 * @author Kamesuta
 */
public abstract class BaseSquareBuilder extends AbstractSquareBuilder {

	protected ArrayList<Vector3f> data;
	protected int pos = 0;

	/**
	 * リストから初期化します。
	 * @param list
	 */
	protected BaseSquareBuilder(ArrayList<Vector3f> list) {
		data = list;
	}

	@Override
	public final int squareSize() {
		return 4;
	}

	@Override
	public int listSize() {
		return Math.min(squareSize(), data.size());
	}

	@Override
	public boolean hasSpace() {
		return lastListPos() < lastSquarePos();
	}

	@Override
	public boolean hasData() {
		return !data.isEmpty();
	}

	@Override
	public Vector3f get(int pos) {
		if (hasData())
			return data.get(inListRangePos(pos));
		else
			return null;
	}

	@Override
	public void set(int pos, Vector3f vec) {
		if (hasData() && inListRange(pos)) {
			int inpos = inSquareRangePos(pos);
			data.set(inpos, vec);
			setPos(inpos);
		} else {
			data.add(vec);
			setPosLast();
		}
	}

	@Override
	public void add(int pos, Vector3f vec) {
		boolean hasSpace = hasSpace();
		if (inListRange(pos)) {
			int inpos = inSquareRangePos(pos);
			data.add(inpos, vec);
			setPos(inpos);
		} else {
			data.add(vec);
			setPosLast();
		}
		if (!hasSpace) removeLast();
	}

	@Override
	public void remove(int pos) {
		if (hasData())
			data.remove(inListRangePos(pos));
	}

	@Override
	public Vector3f get() {
		return get(pos);
	}

	@Override
	public void set(Vector3f vec) {
		set(pos, vec);
	}

	@Override
	public void add(Vector3f vec) {
		add(pos, vec);
	}

	@Override
	public void remove() {
		remove(pos);
	}

	@Override
	public Vector3f getLast() {
		return get(lastListPos());
	}

	@Override
	public void setLast(Vector3f vec) {
		set(lastListPos(), vec);
	}

	@Override
	public void addLast(Vector3f vec) {
		add(lastListPos()+1, vec);
	}

	@Override
	public void removeLast() {
		remove(lastListPos());
	}

	@Override
	public int getPos() {
		return pos;
	}

	@Override
	public int setPos(int setpos) {
		return pos = inListRangePos(setpos);
	}

	/**
	 * 始端位置に移動
	 * @return 始端位置
	 */
	public int setPosFirst() {
		return pos = 0;
	}

	/**
	 * 終端位置に移動
	 * @return 終端位置
	 */
	public int setPosLast() {
		return pos = lastListPos();
	}

	@Override
	public int next() {
		return setPos((pos + 1) % listSize());
	}

	@Override
	public int prev() {
		int size = listSize();
		return setPos((pos + size - 1) % size);
	}

	@Override
	public Square build() throws IllegalStateException {
		if (isReady()) {
			return new Square(get(0), get(1), get(2), get(3));
		} else {
			throw new IllegalStateException("Not Ready");
		}
	}

	@Override
	public Vector3f[] export() {
		return (Vector3f[]) data.toArray();
	}

}