package indi.tammy.simfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/**
 * Hello world!
 *
 */
public class App 
{	
	private final static int pageSize = 100;
	private final static int topN = 10;
	
	public static void main( String[] args )
    {
    	DBManager.initalize();
    	List<Question> uncheckQuestions = DBManager.findUnCheckSimQuestions();
    	for(Question x:uncheckQuestions){
    		System.out.println("Base Question:" + x.toString());
    		List<Question> res = checkSimilarity(x);
    		for(Question y:res){
    			System.out.println("-" + y.toString());
    			//DBManager.insertIntoSimQuestion(x.getId(), y.getId());
    		}
    	}
    }
    
    public static List<Question> checkSimilarity(Question q){
    	List<Question> res = new ArrayList<Question>();
    	Comparator<Question> comp = new Comparator<Question>(){
    	    public int compare(Question q1, Question q2) {
    			if(q1.getSimilarity() > q2.getSimilarity()){
     				return 1;
     			 }
     			 else if(q1.getSimilarity() == q2.getSimilarity()){
     				 return 0;
     			 }
     			 else{
     				 return -1;
     			 }
    	     }
    	};
    	for(int i = 0;;i ++){
    		List<Question> candidates = DBManager.findAllQuestionsByPage(i * pageSize + 1, (i + 1) * pageSize, q.getId());
    		for(Question x:candidates){
    			x.setSimilarity(calcSimilarity(q, x));
    		}
    		Collections.sort(candidates, comp);
    		List<Question> temp = candidates.subList(0, topN-1<candidates.size()?topN-1:candidates.size());
    		res.addAll(temp);
    		if(candidates.size() < pageSize){
    			Collections.sort(res, comp);
    			return res.subList(0, topN-1<res.size()?topN-1:res.size());
    		}
    	}
    }
    
    public static long calcSimilarity(Question q1, Question q2){
    	String str1 = q1.getContent();
    	String str2 = q2.getContent();
    	str1 = str1.replaceAll("<div[/]?>|<br>|<br/>", "");	
    	str2 = str2.replaceAll("<div[/]?>|<br>|<br/>", "");
    	long sim = 0;
    	while(true){
    		String res = getLCString(str1.toCharArray(), str2.toCharArray());
    		if(res == null || res.length() <= 2){
    			break;
    		}
    		str1 = str1.replaceFirst(res, "");
    		str2 = str2.replaceFirst(res, "");
    		sim += res.length() * res.length();
    	}
    	
    	System.out.println(sim);
    	return 1;
    }
    
    public static String getLCString(char[] str1, char[] str2) {
        int i, j;
        int len1, len2;
        len1 = str1.length;
        len2 = str2.length;
        int maxLen = len1 > len2 ? len1 : len2;
        int[] max = new int[maxLen];
        int[] maxIndex = new int[maxLen];
        int[] c = new int[maxLen]; // 记录对角线上的相等值的个数
 
        for (i = 0; i < len2; i++) {
            for (j = len1 - 1; j >= 0; j--) {
                if (str2[i] == str1[j]) {
                    if ((i == 0) || (j == 0))
                        c[j] = 1;
                    else
                        c[j] = c[j - 1] + 1;
                } else {
                    c[j] = 0;
                }
 
                if (c[j] > max[0]) { // 如果是大于那暂时只有一个是最长的,而且要把后面的清0;
                    max[0] = c[j]; // 记录对角线元素的最大值，之后在遍历时用作提取子串的长度
                    maxIndex[0] = j; // 记录对角线元素最大值的位置
 
                    for (int k = 1; k < maxLen; k++) {
                        max[k] = 0;
                        maxIndex[k] = 0;
                    }
                } else if (c[j] == max[0]) { // 有多个是相同长度的子串
                    for (int k = 1; k < maxLen; k++) {
                        if (max[k] == 0) {
                            max[k] = c[j];
                            maxIndex[k] = j;
                            break; // 在后面加一个就要退出循环了
                        }
 
                    }
                }
            }
        }
 
        for (j = 0; j < maxLen; j++) {
            if (max[j] > 0) {
                System.out.println("第" + (j + 1) + "个公共子串:");
                for (i = maxIndex[j] - max[j] + 1; i <= maxIndex[j]; i++)
                    System.out.print(str1[i]);
                System.out.println(" ");
            	return new String(str1, maxIndex[j] - max[j] + 1, max[j]);
            }
        }
        return null;
    }
}
