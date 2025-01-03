package controller; 
 
public class Security 
{
	private static final int KEY = 1;
 	public static int getKey() {
 		return KEY ;
	} 
	
	public static String encrypt(String str)
	{
		String newEncryptString = "";
		for (int i = 0; i <= str.length()-1 ; i++)
		{
			newEncryptString = newEncryptString + (char)(str.charAt(i) + Security.getKey());
		}
		return newEncryptString;
 	}
	 
	public static String decrypt(String str)
	{
		String newDecryptString = "";
		for (int i = 0; i <= str.length()-1 ; i++)
		{
			newDecryptString = newDecryptString + (char)(str.charAt(i) - Security.getKey());
		}
		return newDecryptString;
 	}
	 
	
}
 