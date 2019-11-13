package com.zbcm.office.doc4j;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;

import java.io.File;
import java.math.BigInteger;
/**
 *  @title TableSimpleTest
 *  @Description 表格列表可能是使用最为广泛的效果了，无论是各种需求都可能会涉及到，这里所讲到的所有关于表格的东西均不涉及表格嵌套，换句话讲我在研究这些表格的时候并未对表格的嵌套进行分析。至于使用编程式的方式创建一个表格非常简单，无非就是Tbl、Tr、Tc这些，但是如果就这些对象你会发现创建出来的表格没有边框，不会居中这些之类的，在本篇文章中所涉及到的实现一个表格包括表格的边框线、边框颜色、表格宽度、单元格宽度这些
 *  @author zbcn8
 *  @Date 2019/11/11 10:36
 */
public class TableSimpleTest {
	public static void main(String[] args) throws Exception {

		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();

		MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();

		ObjectFactory objectFactory = Context.getWmlObjectFactory();

		Tbl table = objectFactory.createTbl();

		//创建标题行

		Tr tr = objectFactory.createTr();

		Tc tableNumCell = objectFactory.createTc();

		tableNumCell.getContent().add(mainDocumentPart.createParagraphOfText("编号"));

          /*TblWidth width = new TblWidth();

          width.setW(new BigInteger("250"));

          tableNumCell.setTcPr(new TcPr());

          tableNumCell.getTcPr().setTcW(width);*/

		Tc tableNameCell = objectFactory.createTc();

		tableNameCell.getContent().add(mainDocumentPart.createParagraphOfText("姓名"));

		Tc tableSexCell = objectFactory.createTc();

		tableSexCell.getContent().add(mainDocumentPart.createParagraphOfText("性别"));

		Tc tableEmailCell = objectFactory.createTc();

		tableEmailCell.getContent().add(mainDocumentPart.createParagraphOfText("Email"));

		tr.getContent().add(tableNumCell);

		tr.getContent().add(tableNameCell);

		tr.getContent().add(tableSexCell);

		tr.getContent().add(tableEmailCell);

		table.getContent().add(tr);

		//创建数据行

		for(int i= 1 ; i <= 10 ; i++){

			Tr dataTr = objectFactory.createTr();

			for(int j=1 ; j <= 4 ; j++){

				Tc tc = objectFactory.createTc();

				tc.getContent().add(mainDocumentPart.createParagraphOfText(i + "_" + j));

				tc.setTcPr(new TcPr());

				tc.getTcPr().setTcBorders(getTcBorders());

				int w = 500;

				if(j != 1 && j != 2 && j != 3){

					w = 5000 - w * 3;

				}

				if(i == 5){

				}

				//设置单元格宽度，分别为：2前面3列为百分之10，最后一列为百分之70

				tc.getTcPr().setTcW(getTblWidth(w));

				dataTr.getContent().add(tc);

			}

			table.getContent().add(dataTr);

		}

		table.setTblPr(new TblPr());

		table.getTblPr().setTblBorders(getTblBorders());//设置表格线

		table.getTblPr().setTblW(getTblWidth(5000));

		mainDocumentPart.addObject(table);

		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\编程式表格.docx";

		wordPackage.save(new File(outPath));

	}

	//设置表格宽度

	private static TblWidth getTblWidth(int w) {

		TblWidth width = new TblWidth();

		width.setW(BigInteger.valueOf(w));

		width.setType("pct");

		//此处试了几种方式均不好用,只有这个pct的合适,50分之一是一个百分点,5000为百分之百的宽度

		return width;

	}



	//表格边框

	private static TblBorders getTblBorders() {

		//构造边框样式

		CTBorder border = new CTBorder();

		border.setColor("red");

		border.setSz(new BigInteger("4"));

		border.setSpace(new BigInteger("0"));

		border.setVal(STBorder.SINGLE);

		//设置边框的上下左右边框

		TblBorders borders = new TblBorders();

		borders.setTop(border);

		borders.setBottom(border);

		borders.setLeft(border);

		borders.setRight(border);

		return borders;

	}



	//单元格边框

	private static TcPrInner.TcBorders getTcBorders() {

		//构造边框样式

		CTBorder border = new CTBorder();

		border.setColor("blue");

		border.setSz(new BigInteger("15"));

		border.setSpace(new BigInteger("0"));

		border.setVal(STBorder.SINGLE);

		//设置边框的上下左右边框

		TcPrInner.TcBorders borders = new TcPrInner.TcBorders();

		borders.setTop(border);

		borders.setBottom(border);

		borders.setLeft(border);

		borders.setRight(border);

		return borders;

	}
}
