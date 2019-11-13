package com.zbcm.office.doc4j;

import com.zbcm.office.util.CellMergerUtil;
import com.zbcm.office.util.Docx4jUtils;
import com.zbcm.office.util.vo.CellRangeAddress;
import org.docx4j.TextUtils;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.finders.ClassFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableTemplateMergeTest {

	public static void main(String[] args) throws Exception {



		String templatePath = System.getProperty("user.dir") + "\\javaOffice\\template\\模板式表格_动态单元格合并_template.docx";

		WordprocessingMLPackage wordPackage = WordprocessingMLPackage.load(new File(templatePath));

		//找到模板中的表格对象

		ClassFinder find = new ClassFinder(Tbl.class);

		new TraversalUtil(wordPackage.getMainDocumentPart().getContent(), find);

		Tbl table = (Tbl) find.results.get(0);

		//填充数据--并合并单元格

		fillTableDataAndMerge(table);
		String outPath = System.getProperty("user.dir") + "\\javaOffice\\template\\out\\模板式表格--动态单元格合并.docx";

		wordPackage.save(new File(outPath));
	}

	//根据表格模板找到表格,并填充数据

	private static void fillTableDataAndMerge(Tbl table) throws Exception {

		List<Map<String , Object>> dataList = getDataList();

		Tr dynamicTr = (Tr) table.getContent().get(2);//第三行约定为模板
		String elementContent = getElementContent(dynamicTr);
		System.out.println(elementContent);

		List<String> trAllCellContent = getTrAllCellContent(dynamicTr);

		String dynamicTrXml = XmlUtils.marshaltoString(dynamicTr);//获取模板行的xml数据

		for (Map<String, Object> dataMap : dataList) {

			Tr newTr = (Tr) XmlUtils.unmarshallFromTemplate(dynamicTrXml, dataMap);//填充模板行数据

			table.getContent().add(newTr);

		}
		//删除模板行的占位行
		table.getContent().remove(2);
		//合并单元格，工具类，多列并列合并，但每次合并只是在同一个列中实现的并列合并
		int mergerRows[] = {0 , 1 , 2};

		Map<Integer, List<CellRangeAddress>> rangeMap = CellMergerUtil.getRanglesByData(dataList, mergerRows, 2);

		CellMergerUtil.mergerColumns(table, rangeMap);
	}

	public static List<String> getTrAllCellContent(Tr tr){
		List<Tc> trAllCell = Docx4jUtils.getTrAllCell(tr);
		return  trAllCell.stream().map(tc -> {
			try {
				return Docx4jUtils.getTcContent(tc);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	public static String getElementContent(Object obj) throws Exception {
		StringWriter stringWriter = new StringWriter();
		TextUtils.extractText(obj, stringWriter);
		return stringWriter.toString();
	}



	private static List<Map<String, Object>> getDataList() {

		List<Map<String , Object>> dataList = new ArrayList<Map<String,Object>>();

		Map<String , Object> m1 = new LinkedHashMap<String, Object>();

		m1.put("companyName", "公司1");m1.put("deptName", "学术部");m1.put("realName", "渣渣1");m1.put("sex", "男");m1.put("remark", "备注1");

		Map<String , Object> m2 = new LinkedHashMap<String, Object>();

		m2.put("companyName", "公司1");m2.put("deptName", "学术部");m2.put("realName", "渣渣2");m2.put("sex", "男");m2.put("remark", "备注2");

		Map<String , Object> m3 = new LinkedHashMap<String, Object>();

		m3.put("companyName", "公司1");m3.put("deptName", "行政部");m3.put("realName", "渣渣3");m3.put("sex", "男");m3.put("remark", "备注3");

		Map<String , Object> m4 = new LinkedHashMap<String, Object>();

		m4.put("companyName", "公司2");m4.put("deptName", "行政部");m4.put("realName", "渣渣1");m4.put("sex", "男");m4.put("remark", "备注4");

		Map<String , Object> m5 = new LinkedHashMap<String, Object>();

		m5.put("companyName", "公司2");m5.put("deptName", "行政部");m5.put("realName", "渣渣1");m5.put("sex", "女");m5.put("remark", "备注5");

		Map<String , Object> m6 = new LinkedHashMap<String, Object>();

		m6.put("companyName", "公司3");m6.put("deptName", "行政部");m6.put("realName", "渣渣N");m6.put("sex", "女");m6.put("remark", "备注6");

		dataList.add(m1);

		dataList.add(m2);

		dataList.add(m3);

		dataList.add(m4);

		dataList.add(m5);

		dataList.add(m6);

		return dataList;

	}
}
