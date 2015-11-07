/**
 * 
 */
package com.wsheng.aggregator.solr.handler;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import com.wsheng.aggregator.util.IOUtils;

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class SolrCleanIndexHandler {
	/**
	 * 由于本地无法连接production的数据库和Solr，Http/Rest Delete in production 
	 * Search-Contianer is not work(TODO：resolve this)
	 * 
	 * 用此main方法打runnable jar包并指定main()方法在diandi.im上删除这些索引
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String filePath = SolrCleanIndexHandler.class.getResource("/")+"dismissed_cids.txt";
		filePath = filePath.replace("file:", ""); // for mac
		System.out.println("======================"+ filePath); // 不 work
		
		filePath = "/home/joshwang/dismissed_cids.txt"; // linux 绝对路径
		List<String> lines = IOUtils.readLines(filePath);
		
		SolrClient solrClient = new HttpSolrClient("http://diandi_be0:44000/solr/circle");
		System.out.println("--------- start --------");
		for (String currentLine : lines) {
			solrClient.deleteById(currentLine);
			System.out.println(currentLine);
		}
		solrClient.commit(); // very important
		solrClient.close();
		System.out.println(" ----------- end --------");
	}
}
