package com.zbcn.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Collection;

/**
 *  @title SearchHighlighter
 *  @Description 代码高亮
 *  @author zbcn8
 *  @Date 2019/12/20 17:10
 */
public class SearchHighlighter {

	/**
	 * 创建索引
	 * @param indexPath
	 * @param resourcePath
	 * @throws IOException
	 */
	public static void createIndex(String indexPath, String resourcePath) throws IOException {
		// 使用IK分词器
		Analyzer analyzer = new IKAnalyzer();
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
		}
		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	/**
	 * 查询索引
	 * @author jitwxs
	 * @version 创建时间：2018年3月5日 上午12:38:05
	 * @param indexPath 索引路径
	 * @param keyword 关键字
	 * @throws IOException
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

		// 定义高亮代码（前后加红色）
		String startCode = "<span style=\"color:red\">";
		String endCode = "</span>";

		System.out.println("共找到 " + topDocs.totalHits + " 处匹配");
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		for (ScoreDoc scoreDoc : scoreDocs) {
			Document doc = indexSearcher.doc(scoreDoc.doc);

			// 高亮代码
			String fileName = highlight(booleanBuery, doc, "fileName", startCode, endCode);
			String content = highlight(booleanBuery, doc, "content", startCode, endCode);

			System.out.println("fileName: " + fileName);
			System.out.println("content: " + content);
		}

		if (reader != null) {
			reader.close();
		}
	}

	/**
	 * 高亮显示代码
	 * @param query 查询对象
	 * @param doc Document对象
	 * @param fieldName filed名
	 * @param startCode 高亮起始代码
	 * @param endCode 高亮结束代码
	 * @return
	 * @throws IOException
	 * @throws InvalidTokenOffsetsException
	 * @throws InvalidTokenOffsetsException
	 */
	public static String highlight(Query query, Document doc, String fieldName, String startCode, String endCode) throws IOException, InvalidTokenOffsetsException, InvalidTokenOffsetsException {
		QueryScorer scorer = new QueryScorer(query);
		// 设置高亮的格式
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter(startCode, endCode);

		Highlighter highlighter = new Highlighter(formatter, scorer);
		// 设置高亮的分段
		highlighter.setTextFragmenter(new SimpleSpanFragmenter(scorer));

		String fieldVale = doc.get(fieldName);
		// 高亮代码，如果没找到匹配的，返回null
		String tmp = highlighter.getBestFragment(new IKAnalyzer(), fieldName, fieldVale);
		if(tmp != null) {
			return tmp;
		} else {
			return fieldVale;
		}
	}

	public static void main(String[] args) {
		// 存放用于查找文件内容的文件夹
		String resourcePath = "D:\\temp\\lucene-resource";
		// Lucene索引文件夹
		String indexPath = "D:\\temp\\lucene-index";
		try {
			createIndex(indexPath, resourcePath);
			queryIndex(indexPath, "jitwxs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
