package com.zbcm.office.util.vo;

public class CellRangeAddress {

	private int firstRow, lastRow, firstCol, lastCol;

	public CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getFirstCol() {
		return firstCol;
	}

	public void setFirstCol(int firstCol) {
		this.firstCol = firstCol;
	}

	public int getLastCol() {
		return lastCol;
	}

	public void setLastCol(int lastCol) {
		this.lastCol = lastCol;
	}

	@Override
	public String toString() {
		return "CellRangeAddress [firstRow=" + firstRow + ", lastRow="
				+ lastRow + ", firstCol=" + firstCol + ", lastCol=" + lastCol
				+ "]";
	}

}
