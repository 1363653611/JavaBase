package com.zbcn.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 *  @title SearchEnglish
 *  @Description 英文搜索
 *  @author zbcn8
 *  @Date 2019/12/20 10:27
 */
public class SearchEnglish {

	/**
	 * 创建索引
	 * @param indexPath 索引路径
	 * @param resourcePath 被索引的路径
	 */
	public static void createIndex(String indexPath,String resourcePath) throws IOException {

		/* Step1：创建IndexWrite对象
		 * （1）定义词法分析器
		 * （2）确定索引存储位置-->创建Directory对象
		 * （3）得到IndexWriterConfig对象
		 * （4）创建IndexWriter对象
		 */
		//创建标准的分词器
		Analyzer analyzer = new StandardAnalyzer();
		FSDirectory directory = FSDirectory.open(new File(indexPath).toPath());
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		// 清除以前的索引
		indexWriter.deleteAll();
		// 得到txt后缀的文件集合
		Collection<File> txtFiles = FileUtils.listFiles(new File(resourcePath), new String[] {"txt"}, true);
		for (File file:txtFiles){
			String fileName = file.getName();
			String content = FileUtils.readFileToString(file, "UTF-8");
			/*
			 * Step2：创建Document对象，将Field地下添加到Document中
			 * Field第三个参数选项很多，具体参考API手册
			 */
			Document document = new Document();
			document.add(new Field("fileName", fileName, TextField.TYPE_STORED));
			document.add(new Field("content", content, TextField.TYPE_STORED));
			/*
			 *Step4：使用 IndexWrite对象将Document对象写入索引库，并进行索引。
			 */
			indexWriter.addDocument(document);
		}
		if(indexWriter != null) {
			indexWriter.close();
		}
	}


	/**
	 * 查询索引
	 * @param indexPath indexPath 索引路径
	 * @param keyword 关键字
	 */
	public static void queryIndex(String indexPath, String keyword) throws IOException {
		/*
		 * Step1：创建IndexSearcher对象
		 * （1）创建Directory对象
		 * （2）创建DirectoryReader对象
		 * （3）创建IndexSearcher对象
		 */
		Directory directory = FSDirectory.open(new File(indexPath).toPath());
		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(reader);
		/*
		 * Step2：创建TermQuery对象，指定查询域和查询关键词
		 */
		TermQuery query1 = new TermQuery(new Term("fileName",keyword));
		TermQuery query2 = new TermQuery(new Term("content",keyword));
		/*
		 * Step3：创建Query对象
		 */
		BooleanQuery booleanQuery = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.SHOULD).add(query2, BooleanClause.Occur.SHOULD)
				.build();
		/*
		 * Step4：根据查询条件查询结果，并遍历输出
		 * （1）查询结果得到TopDocs对象
		 * （2）根据topDocs得到ScoreDoc对象
		 * （3）根据scoreDoc得到Document对象
		 * （4）输出结果
		 */
		TopDocs topDocs = indexSearcher.search(booleanQuery, 100);
		System.out.println("共找到 " + topDocs.totalHits + " 处匹配");
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			String fileName = doc.get("fileName");
			System.out.println("fileName: " + fileName);
		}
		if(reader != null) {
			reader.close();
		}
	}

	public static void main(String[] args) {
		// 存放用于查找文件内容的文件夹
		String resourcePath = "D:\\temp\\lucene-resource";
		// Lucene索引文件夹
		String indexPath = "D:\\temp\\lucene-index";
		try {
			createIndex(indexPath, resourcePath);
			queryIndex(indexPath, "github");
			queryIndex(indexPath, "java");
			queryIndex(indexPath, "shell");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
