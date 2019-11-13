package com.zbcm.office.doc4j;

import org.docx4j.Docx4J;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.finders.ClassFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableTemplateTest {

	public static void main(String[] args) throws Exception {



		String templatePath = System.getProperty("user.dir") + "\\javaOffice\\template\\模板式表格_template.docx";

		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\模板式表格.docx";

		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(templatePath));

		HashMap<String, String> mappings = new HashMap<String, String>();

		//构造非循环格子的表格数据

		mappings.put("name", "马参军");

		mappings.put("sex", "男");

		mappings.put("skill", "散谣：三人成虎事多有");

		//构造循环列表的数据

		ClassFinder find = new ClassFinder(Tbl.class);

		new TraversalUtil(wordMLPackage.getMainDocumentPart().getContent(), find);

		Tbl table = (Tbl) find.results.get(1);

		Tr dynamicTr = (Tr) table.getContent().get(1);//第二行约定为模板

		String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);//获取模板行的xml数据

		List<Map<String , Object>> dataList = getDataList();

		for (Map<String, Object> dataMap : dataList) {

			Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);//填充模板行数据

			table.getContent().add(newTr);

		}

		//删除模板行的占位行

		table.getContent().remove(1);

		wordMLPackage.getMainDocumentPart().variableReplace(mappings);//设置全局的变量替换

		Docx4J.save(wordMLPackage, new File(outPath));

	}



	//构造循环数据

	private static List<Map<String , Object>> getDataList() {

		List<Map<String , Object>> dataList = new ArrayList<Map<String , Object>>();

		Map<String , Object> m1 = new HashMap<String , Object>();

		m1.put("item.number", "1");m1.put("item.name", "关银萍");

		m1.put("item.sex", "女");m1.put("item.skill", "来吧，青龙偃月刀");

		dataList.add(m1);

		Map<String , Object> m2 = new HashMap<String , Object>();

		m2.put("item.number", "2");m2.put("item.name", "马云禄");

		m2.put("item.sex", "女");m2.put("item.skill", "啥玩意，手里方片摸牌，占位占位看到换行的样式效果，占位占位看到换行的样式效果");

		dataList.add(m2);

		Map<String , Object> m3 = new HashMap<String , Object>();

		m3.put("item.number", "3");m3.put("item.name", "张星彩");

		m3.put("item.sex", "女");m3.put("item.skill", "长缨在手，擦擦擦擦");

		dataList.add(m3);



		return dataList;

	}
}
