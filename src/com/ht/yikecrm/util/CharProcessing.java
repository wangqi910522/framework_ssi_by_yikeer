package com.ht.yikecrm.util;

public class CharProcessing {
   /** @param obj0 */
   public static Object codeToString(Object obj0) {
   	Object s = obj0;
   	try{
   		byte tempB[]=((String) s).getBytes("ISO-8859-1");
   		s = new String(tempB);
   		return s;
   	}
   	catch(Exception e)
   	{
   		return s;
   	}
   }
   
   /** @param obj0 */
   public static void println(Object obj0) {
   	System.out.println(codeToString(obj0));
   }

}