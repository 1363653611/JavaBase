package com.zbcn.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
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
//import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Collection;

/**
 * 使用 IKAnalyzer 中文分词器 分析
 */
public class SearchChinese {

	/**
	 * 创建索引
	 * @param indexPath 索引路径
	 * @param resourcePath 被索引的路径
	 */
	public static void createIndex(String indexPath, String resourcePath) throws IOException {
// 使用IK分词器
		//Analyzer analyzer = new IKAnalyzer();
		Analyzer analyzer = new Analyzer() {
			@Override
			protected TokenStreamComponents createComponents(String s) {
				return null;
			}
		};
		Directory directory = FSDirectory.open(FileSystems.getDefault().getPath(indexPath));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
		// 清除以前的索引
		indexWriter.deleteAll();
		// 得到txt后缀的文件集合
		Collection<File> txtFiles = FileUtils.listFiles(new File(resourcePath), new String[] { "txt" }, true);
		for (File file : txtFiles) {
			String fileName = file.getName();
			// 以UTF-8编码读取文件内容，如果文件不是该编码，去掉该参数即可
			String content = FileUtils.readFileToString(file, "UTF-8");

			Document document = new Document();
			document.add(new Field("fileName", fileName, TextField.TYPE_STORED));
			document.add(new Field("content", content, TextField.TYPE_STORED));
			indexWriter.addDocument(document);

			printParticiple(analyzer, content);
		}

		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	/**
	 * 输出分词的具体内容
	 * @param analyzer 分词器
	 * @param content 内容
	 * @throws IOException
	 */
	public static void printParticiple(Analyzer analyzer, String content) throws IOException {
		TokenStream tokenStream = analyzer.tokenStream("", content);

		// 设置一个引用(相当于指针)，这个引用可以是多种类型，可以是关键词的引用，偏移量的引用等等，charTermAttribute对象代表当前的关键词
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		// 调用tokenStream的reset方法，不调用该方法，会抛出一个异常
		tokenStream.reset();
		// 使用while循环来遍历单词列表
		while (tokenStream.incrementToken()) {
			System.out.println(charTermAttribute);
		}
		tokenStream.close();
	}

	/**
	 * 查询索引
	 * @param indexPath 全路径
	 * @param keyword 关键字
	 * @throws Exception
	 */
	public static void queryIndex(String indexPath, String keyword) throws Exception {
		Directory directory = FSDirectory.open(new File(indexPath).toPath());
		DirectoryReader reader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(reader);

		TermQuery query1 = new TermQuery(new Term("fileName", keyword));
		TermQuery query2 = new TermQuery(new Term("content", keyword));
		BooleanQuery booleanBuery = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.SHOULD).add(query2, BooleanClause.Occur.SHOULD)
				.build();
		TopDocs topDocs = indexSearcher.search(booleanBuery, 100);

		System.out.println("共找到 " + topDocs.totalHits + " 处匹配");
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);
			String fileName = doc.get("fileName");
			String content = doc.get("content");
			System.out.println("fileName: " + fileName);
			System.out.println("content: " + content);
		}

		if (reader != null) {
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
			queryIndex(indexPath, "j i t w x s");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
