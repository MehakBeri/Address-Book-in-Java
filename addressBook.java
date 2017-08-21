
import java.util.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
public class addressBook{

	public static void main(String[] args)
	{	Scanner k= new Scanner(System.in);
		System.out.println("Welcome to the Address Book!\n");
		int choice=0;
		entry[] En=new entry[10];
		int serial=0;
		while(choice!=9){
		System.out.println("1) Load from file\n2) Save to file\n3) Add an entry\n4) Remove an entry\n5) Edit an existing entry\n6) Sort the address book\n7) Search for a specific entry\n8) Move an entry\n9) Quit");
		System.out.println("\nPlease choose what you'd like to do with the database");
		choice= k.nextInt();

		if(choice == 1){
			System.out.print("What is the name of the file?");
			String filename= k.next();
			try
			{
			loadFile file= new loadFile(filename);
			String[] data= file.OpenFile();
			for(int i=0; i<data.length; i++)
			{
				String arr[]= data[i].split("/");
				System.out.println("Entry "+(i+1));
				System.out.println("First Name: "+arr[0]);
				System.out.println("Last Name: "+arr[1]);
				System.out.println("Phone no: "+arr[2]);
				System.out.println("Address: "+arr[3]);
				System.out.println("Email: "+arr[4] + "\n");
				En[i]=new entry();
				En[i].firstName=arr[0];
				En[i].lastName=arr[1];
				En[i].phoneNo= Integer.valueOf(arr[2]);
				En[i].address=arr[3];
				En[i].email=arr[4];				
			}serial=data.length;}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
		if(choice==2){
			System.out.print("Please enter the name of the file to which you want to save the data: ");
			String saveto=k.next();
			
			try{
				saveFile s= new saveFile(saveto, false);
			
				for(int j=0; j<serial; j++){
						
					s.writeToFile(En[j].firstName+"/"+En[j].lastName+"/"+En[j].phoneNo+"/"+En[j].address+"/"+En[j].email , j);
				}
				System.out.println("Data saved to "+ saveto);
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
		if(choice==3){
			System.out.println("Adding entry no. "+(serial+1));
			En[serial]=new entry();
			System.out.print("Please enter your first name: ");
			En[serial].firstName= k.next();
			System.out.print("\nPlease enter your last name: ");
			En[serial].lastName= k.next();
			System.out.print("\nPlease enter your phone number: ");
			En[serial].phoneNo= k.nextInt();
			k.nextLine();
			System.out.print("\nPlease enter your address: ");
			En[serial].address= k.nextLine();
			System.out.print("\nPlease enter your email: ");
			En[serial].email= k.next();
			
			serial++;
		//	System.out.println("\n Printing the array..");
		//	for(int b=0; b<serial; b++){System.out.println(En[b].firstName);}
			
		}
		if(choice==4){
			System.out.println("Please enter the serial number of the entry that you want to delete.");
			int del= k.nextInt();
			for(int d=del-1; d<serial-1; d++){
				En[d].firstName=En[d+1].firstName;
				En[d].lastName=En[d+1].lastName;
				En[d].phoneNo=En[d+1].phoneNo;
				En[d].address=En[d+1].address;
				En[d].email=En[d+1].email;
			}
			serial--;
		}
		if(choice==5){
			System.out.print("Which entry do you want to edit?");
			int edit=k.nextInt();
			System.out.println("Entry no. "+edit+"\nFirst Name: "+En[edit-1].firstName+"\nLast Name: "+En[edit-1].lastName+"\nPhone: "+En[edit-1].phoneNo+"\nAddress: "+En[edit-1].address+"\nEmail: "+En[edit-1].email+"\n");
			System.out.println("Enter 1 if you want to edit first name. Enter 2 if you want to edit last name and so on..");
			int toEdit = k.nextInt();
			if(toEdit==1){
					System.out.print("Enter edited value of first name: ");
					String fEdited=k.next();
					En[edit-1].firstName=fEdited;
					}
			if(toEdit==2){
					System.out.print("Enter edited value of last name: ");
					String lEdited=k.next();
					En[edit-1].lastName=lEdited;
					}
			if(toEdit==3){
					System.out.print("Enter edited value of phone no: ");
					int pEdited=k.nextInt();
					En[edit-1].phoneNo=pEdited;
					}
			if(toEdit==4){
					System.out.print("Enter edited value of address: ");
					k.nextLine();
					String aEdited=k.nextLine();
					En[edit-1].address=aEdited;
					}
			if(toEdit==5){
					System.out.print("Enter edited value of email: ");
					String eEdited=k.next();
					En[edit-1].email=eEdited;
					}
			System.out.println("\tAfter Editing");
			System.out.println("Entry no. "+edit+"\nFirst Name: "+En[edit-1].firstName+"\nLast Name: "+En[edit-1].lastName+"\nPhone: "+En[edit-1].phoneNo+"\nAddress: "+En[edit-1].address+"\nEmail: "+En[edit-1].email+"\n");
		}
		if(choice==6){
			System.out.print("Which parameter do you want to sort by? To sort by last name enter 1, to sort by phone number enter 2..");
			int sort=k.nextInt();
			if(sort==1){

				System.out.println("\nSorting by last name..");
				entry swapp;
				for(int kkk=0; kkk<serial-1; kkk++){
				for(int jkk=0; jkk<serial; jkk++)
		{  if(jkk!=serial-1){
			if (En[jkk].lastName.compareTo(En[jkk+1].lastName)>0) {swapp = En[jkk]; En[jkk] = En[jkk+1]; En[jkk+1]=swapp;}}
		}}
				System.out.println("sorted. save to a file and load the file to view the sorted database!");

			}
			if(sort==2){
				System.out.println("\nSorting by phone numbers..");
				entry swap;
				for(int kk=0; kk<serial-1; kk++){
				for(int jk=0; jk<serial; jk++)
		{  if(jk!=serial-1){
			if (En[jk].phoneNo >= En[jk+1].phoneNo) {swap = En[jk]; En[jk] = En[jk+1]; En[jk+1]=swap;}}
		}}
				System.out.println("sorted. save to a file and load the file to view the sorted database!");				
			}
			
		}
		if(choice==7){
			System.out.print("Please enter the first letter of the last name that you wish to search for? ");
			char search= k.next().charAt(0);
			int xx=0;
			for(int t=0; t<serial; t++){
				//System.out.println("in here "+t);
				if(En[t].lastName.charAt(0)==(search))
				{	xx=1;
					System.out.println("Entry no. "+(t+1));
					System.out.println("Information: "+ En[t].firstName+", "+En[t].lastName+", "+En[t].phoneNo+", "+En[t].address+"  , "+En[t].email);
				}
				
			}
			if(xx==0) {System.out.println("No such entry.");}
		}

		if(choice==8){
			System.out.print("Please load the file from which you want to move an entry using option 1. Enter 1 if you have done so, or enter 0 to go back to the main menu. ");
			int opt=k.nextInt();
			if(opt==1){
				System.out.print("Enter entry number you want to copy? ");
				int copy= k.nextInt();
				System.out.print("Enter the name of the file where you want to copy the entry ");
				String copyTo = k.next();
			try{
				saveFile ss= new saveFile(copyTo);
				ss.writeToFile(En[copy-1].firstName+"/"+En[copy-1].lastName+"/"+En[copy-1].phoneNo+"/"+En[copy-1].address+"/"+En[copy-1].email , 1);
				
				System.out.println("Data copied to "+ copyTo);
			}
			catch(IOException e){
				System.out.println(e.getMessage());
			}
				
			}
			
		}

		}
		System.out.println("Ok Bye!");
	
	}
	
}
class entry{
		String firstName;
		String lastName;
		int phoneNo;
		String address;
		String email;
	}
class loadFile{
	private String path;
	public loadFile(String file_path){
		path=file_path;
		
	}

	public String[] OpenFile() throws IOException{

		FileReader fr= new FileReader(path);
		BufferedReader textReader = new BufferedReader(fr);
		int numberOfLines=readLines();
		String[] textData = new String[numberOfLines];
		for(int i=0; i<numberOfLines; i++){
			textData[i] = textReader.readLine();
		}
		textReader.close();
		return textData;
	}
	public int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		int numberOfLines=0;
		while((bf.readLine())!=null) {numberOfLines++;}
		//System.out.println("no of lines: "+ numberOfLines);
		bf.close(); //flushed buffer
		return numberOfLines;
	}
}
class saveFile{
	private String path;
	private boolean append_to_file=false; //true=append, false=dont append, erase n write anew
	
	public saveFile(String file_path)
	{
		path= file_path;
	}
	public saveFile( String file_path , boolean append_value ) {
		//second constructor if pass wanna append value
		path = file_path;
	append_to_file = append_value;
	}
	public void writeToFile(String textLine, int i)throws IOException{
		if(i==0){append_to_file=false;}
		else {append_to_file=true;}
//filewriter writes bytes printwriter prints text with the help of filewriter
		FileWriter write = new FileWriter(path, append_to_file);//first item will clear the text file, after that any additional added item will be appended
		PrintWriter print_line = new PrintWriter(write);
		print_line.printf("%s"+"%n",textLine);
		print_line.close();
	}
}

