package indi.tammy.simfinder;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DBManager.initalize();
    	DBManager.findUnCheckSimQuestions();
    	DBManager.findAllQuestionsByPage(1, 1, 9);
    }
}
