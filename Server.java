import java.io.*;
import java.net.*;
import java.util.*;
import java.security.*;

public class Server {
  	public static void main(String[] args){
    
//Load Database   
	if(args.length != 1)
	{
		System.out.print("Usage: Server <port_number>");
		System.exit(1);
	}
	try
	{
       	FileReader lI = new FileReader("user_pass.txt");
       	BufferedReader loginText = new BufferedReader(lI);
       	HashMap<String, String> loginMap = new HashMap<String, String>();
       	String pwd;
       	String uName = "test";
       	int lisPort = Integer.parseInt(args[0]);
       	
       	while((pwd = loginText.readLine()) != null)
		{
						String[] creds = pwd.split(" ");
						String pass = AeSimpleSHA1.SHA1(creds[1]);
						loginMap.put(creds[0], pass);
		
		//System.out.println(pwd);
		//System.out.println("Size is now " + loginMap.size());
		}

		  //Check hashmap print
		  //for(String entry: loginMap.values())
		  // System.out.println("* " + entry + " *");
	
	
//Establish Socket connection
		ServerSocket lisSock = new ServerSocket(lisPort);	
		Socket clSock = lisSock.accept();
		System.out.println("Connection successful");
		PrintWriter out =
				new PrintWriter(clSock.getOutputStream(), true);
		BufferedReader in = new BufferedReader(
			new InputStreamReader(clSock.getInputStream()));
		
		String userName, pswd, textOut;
//Ask for username and password (should spawn thread around here)
		out.println("Hey! You've connected to the chat server. " +
		"Please enter your username and password as prompted" + "Username: "  );
		userName = in.readLine();
		out.println("Password: ");
		pswd = AeSimpleSHA1.SHA1(in.readLine());

		System.out.println(userName + " "  + pswd);


		
		//out.println("This is the username you enetered, ya? - " + textIn);
			
	} 
     catch(FileNotFoundException e)
	 {
	    System.out.println("Login file not found");
            System.exit(1);
         }
	 catch(IOException e)
	 {
		 System.out.println("input error");
		 System.exit(1);
	 }

  }
}
