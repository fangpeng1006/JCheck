
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtils {
    /**
     * 获取业务日志logger
     *
     * @return
     */
    public static Logger getBussinessLogger() {

        return LoggerFactory.getLogger(LogEnum.BUSSINESS.getCategory());
    }

    /**
     * 获取平台日志logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        return LoggerFactory.getLogger(LogEnum.PLATFORM.getCategory());
    }

    /**
     * 获取数据库日志logger
     *
     * @return
     */
    public static Logger getDBLogger() {
        return LoggerFactory.getLogger(LogEnum.DB.getCategory());
    }


    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }


    /**
     * 日志相关
     * @return
     */
    //static Logger logger = Logger.getLogger ( BaseController.class.getName () );
    /**
     * 获取调用此函数的代码的位置
     * @return 包名.类名.方法名(行数)
     */
//    @Value("${logResource}")
//    private String logResource;

//    public static String getCodeLocation(){
//        try{
//            /*** 获取输出信息的代码的位置 ***/
//            String location = "";
//            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
//            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
//                    + "(" + stacks[2].getLineNumber() + ")";
//            return location;
//        }catch (Exception e) {
//            // TODO: handle exception
//            logger.error(e);
//            return "";
//        }
//    }

    /**
     * 打印警告 异常整体捕捉
     * ExceptionLogger
     * @param obj
     */
    public static void warn(Object obj) {
        Logger logger =  getExceptionLogger();
        Logger logger1 = getPlatformLogger();
        try{
            /*** 获取输出信息的代码的位置 ***/
            // String location = getCodeLocation();
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";
            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                logger.error("异常来源：["+location+"] 异常内容：[" + str+"]。");
            } else {
                logger1.warn("警告来源：["+location+"] 警告内容：[" + obj.toString()+"]。");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 打印info
     * 业务逻辑打印
     * @param obj
     */


    public static void infoBussiness(Object obj) {
        Logger logger = getBussinessLogger();
        Logger logger1 = getExceptionLogger();
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";
            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                logger1.error("异常来源：["+location+"] 异常内容：[" + str+"]。");
                logger.error("异常来源：["+location+"] 异常内容：[" + str+"]。");
            } else {
                logger.info("打印来源：["+location+"] 打印内容：[" + obj.toString()+"]。");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**打印info
     * 平台输出、普通打印
     * @param obj
     */
    public static void info(Object obj) {
        Logger logger = getPlatformLogger();
        Logger logger1 = getExceptionLogger();
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";
            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                logger1.error("异常来源：["+location+"] 异常内容：[" + str+"]。");
            } else {
                logger.info("打印来源：["+location+"] 打印内容：[" + obj.toString()+"]。");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * 打印错误
     *
     * @param obj
     */
    public static void error(Object obj) {
        Logger logger =  getExceptionLogger();
        Logger logger1 = getPlatformLogger();
        try{
            /*** 获取输出信息的代码的位置 ***/
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
                    + "(" + stacks[2].getLineNumber() + ")";

            /*** 是否是异常  ***/
            if (obj instanceof Exception) {
                Exception e = (Exception) obj;
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                String str = sw.toString();
                logger.error("异常来源：["+location +"] 异常内容：[" + str +"]。");
            } else {
                logger1.error("错误来源：["+location +"] 错误内容：[" +  obj.toString()+"]。");
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


}
