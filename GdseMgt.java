import java.util.*;

class GdseMgt{

// static variab ========================================= 
	// to copy arraay - [ https://www.softwaretestinghelp.com/java-copy-array/ ] [ https://www.delftstack.com/howto/java/java-copy-2d-array/ ]
	//String[][0] = ID || String[][1] = name || String[][2] = prf || String[][3] = dbms || String[][4] = total
	public static String[][] student =  new String [0][4];		
	public static String[][] student_2;							// student_2 [2D][5] to store total marks
	public static int[] sumOfMarks ;
	public static int[] sortedSumOfMarks;
	public static int[] sortedMarks;

	public static boolean y_n = false ;
	public static boolean addNew = true ;
	public static boolean ChkAddNewStWithMarks = true ;	
	public static boolean addMarksRepeat = false;
	public static boolean loopRun = true;
	public static int indexOf2D = 0;
	


// Heading test =================================================
	/* public static void head(String heading){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\t"+ heading +"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}	*/

// Home page 	============================================
	public static void home(){

		Scanner input =  new Scanner(System.in);

		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\tWELCOME TO GDSE MARKS MANAGEMENT SYSTEM "+"\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
		
		System.out.println("\n[1] Add New Student\t\t\t[2] Add New Student With Marks");
		System.out.println("[3] Add Marks\t\t\t\t[4] Update Student Details");
		System.out.println("[5] Update Marks\t\t\t[6] Delete Student");
		System.out.println("[7] Print Student Details\t\t[8] Print Student Ranks");
		System.out.println("[9] Best in Programming Fundamentals\t[10] Best in Database Management System");

		System.out.print("\nEnter an option to continue > ");
		int methodId =  input.nextInt();
		switch(methodId) {									// Switch- Case / Menu Methods calling
			case 1  : {addNewStHead(); 
								y_n = true;
								addNewSt(); 
								home();
								break; }
			case 2  : {addNewStWithMarksHead(); 	
								addNewStWithMarks();
								home();
								break; }
			case 3  : {addMarksHead( );
								addMarks(); 
								home();
								break; }
			case 4  : {updateStudentDataHead( );
								updateStudentData();
								home(); 
								break; }
			case 5  : {updateMarksHead();
								updateMarks();
								home(); 
								break; }
			case 6  : { deleteStHead();
								deleteSt();
								home(); 
								break;}
			case 7  : {printStHead( );
								totMarks();
								printSt();								
								home(); 
								break;}
			case 8  : {printStRankHead();
								totMarks();
								printStRank();
								home(); 
								break;}
			case 9  : {bestPrfHead( );
								sortSubMarks(2);
								bestPrf();
								home(); 
								break;}
		   case 10  : {bestDbmsHead( );
		   						sortSubMarks(3);
		   						bestDbms();
		   						home(); 
		   						break; } 
			default :  System.out.println("Option Invalid"); 			
		}		
	}

//						 >>  Method Supportives <<

// add new  repeat =========================================

	public static void repeatAddNew(){
		Scanner input = new Scanner ( System.in);
		System.out.print("\nStudent has been added successfully. Do you want to add a new student (Y/n) : ") ; 
		char yesNo = input.next().charAt(0);			// taken from [ https://codegym.cc/groups/posts/nextchar-in-java ]
		y_n = yesNo == ('N' | 'n') ? false : true;		// given-->(Y/n) insted used --> (y/Y and N/n)
	}

// check duplicate =========================================

	public static boolean isDup(String arr1[][], String value ){	        										 	
        for (int i =0;i< student.length ;i++ ) {
            for ( int j =0; j < 1 ;j++ ) {                										
                if ((student[i][0].equals(value))) {		//if (student[i][0] == vlaue) { // cannot use for Strings				
                    System.out.println("The student ID already exists\n");
                    ChkAddNewStWithMarks = false; 
                	return true;
                }
            }            
        }
        ChkAddNewStWithMarks =true;
        return false;
	}

	public static boolean isDup(String arr1[][], String value, boolean key){        										 	
        for (int i =0;i< student.length ;i++ ) {
            for ( int j =0; j < 1 ;j++ ) {                										
                if ((student[i][0].equals(value))) {
                    indexOf2D = i;
                	return true;
                }
            }            
        }
        return false;
	}

// Marks validation ========================================
	public static void marksValid(int marks, int index){
		if( marks>=0 && marks <101) {
																// convert int to string >> https://stackoverflow.com/questions/3335737/integer-tostringint-i-vs-string-valueofint-i-in-java#:~:text=valueOf(int)%20is%20simply%20calling,are%20creating%20one%20String%20object).]
																// convert int to string >>	https://www.javatpoint.com/java-int-to-string
			student[student.length-1][index] = String.valueOf(marks);
		} else {
			System.out.print("Invalid marks, please enter correct marks.");
		}								
	}

	public static void marksValid(int marks, int index, boolean key){
		if( marks>=0 && marks <101) {
			addMarksRepeat = false;
			student[indexOf2D][index] = String.valueOf(marks);
		} else {
			System.out.println("Invalid marks, please enter correct marks\n ");
			addMarksRepeat = true;
		}								
	}	

// loop Runing     =========================================
	public static void loopRunning(){
		Scanner input = new Scanner ( System.in);
		char yesNo = input.next().charAt(0);
		System.out.println();  
		loopRun = yesNo == ('N' | 'n') ? false : true;		
	}

// Total marks =============================================
	public static void totMarks(){	
		String[][] temp= new String [student.length][5];		// temp[2D][4] to store total marks
		int[] sumOfMarksTemp = new int[student.length];

		for (int i = 0;i < student.length ;i++ ) {
			for (int j =0; j< 4 ;j++ ) {
				temp[i][j] = student[i][j];
			}
			temp[i][4]= String.valueOf(Integer.valueOf(temp[i][2]) + Integer.valueOf(temp[i][3]));	
			sumOfMarksTemp[i] = Integer.valueOf(temp[i][4]);			
		}

		student_2 = temp;
		sumOfMarks = sumOfMarksTemp;
		Arrays.sort(sumOfMarksTemp);		//prf doc Q337
		sortedSumOfMarks = sumOfMarksTemp;
	}

// ranking per head ========================================
	public static int ranking(int value){

		for(int i =0; i < sumOfMarks.length;i++){
			if(sortedSumOfMarks[i] == value) {
				return (i+1);
			}			
		}
		return 0;
	}

// ranking of total ========================================
	public static int ranking(int value, boolean key){

		for(int i =0; i < sumOfMarks.length;i++){
			if(sumOfMarks[i] == value) {
				return (i);
			}			
		}
		return 0;
	}

// subjectwise sorted marks =====================================
	public static void sortSubMarks(int value){
		
		int[] marksTemp = new int[student.length];

		for(int i =0; i < student.length; i++){
			marksTemp[i] = Integer.valueOf(student[i][value]);
								
		}
		
		Arrays.sort(marksTemp);		
		sortedMarks = marksTemp;
		//System.out.println( Arrays.toString(marksTemp));
	}

// subjectwise Ranking =====================================
	public static int subRanking(int marks, int index){
		for (int i =0; i<student.length; i++ ) {
			if(marks == Integer.valueOf(student[i][index])){
				return i;
			} 
		}
		return 0;
	}


// 						>> Major Methods <<

//Add New Student ==========================================
	public static void addNewStHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tADD NEW STUDENT "+"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}
	public static void  addNewSt(){
		Scanner input = new Scanner ( System.in);
		do{	

			System.out.print("Enter Student ID\t: ");
			String id = input.nextLine();							//replaced nextLine() by next() - ref [https://stackoverflow.com/questions/69784155/program-appears-to-skip-first-iteration-of-while-loop]
			String[][] tempSt = new String[student.length +1][4];	// load current Student data list

			if(!isDup(tempSt, id)){									// chk duplicate
				for(int i = 0; i<student.length; i++){
					tempSt[i] = student[i];							// no need to copy one by one, the current student data can be modified
				}
				tempSt[tempSt.length-1][0] = id;
				
				System.out.print("Enter Student Name\t: ");
				String  name = input.nextLine ();				
				tempSt[tempSt.length-1][1] = name;
				student = tempSt;
				
				if(addNew){
					repeatAddNew();
				}
				
			} else{
				continue;
			}

		} while(y_n == true);		
	}

//Add New Student With Marks ===============================
	public static void addNewStWithMarksHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\tADD NEW STUDENT WITH MARKS\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}
	public static void addNewStWithMarks(){
		Scanner input = new Scanner ( System.in);

		do{
			addNew = false;
			y_n = false;
			addNewSt();

			if (true) {													// add marks validation
				System.out.print("Programming Fundamentals Marks : ");
				int prfMarks = input.nextInt();	
				marksValid(prfMarks, 2);
				System.out.print("Database Management System Marks : ");
				int dbmsMarks = input.nextInt();
				marksValid(dbmsMarks, 3);
			}
			addNew = true;
			repeatAddNew();
			System.out.println("Test");

		} while(y_n == true);		
	}

//Add Marks ================================================
	public static void addMarksHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\t\tADD MARKS\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}
	public static void addMarks(){
		Scanner input = new Scanner ( System.in);

		do{
			System.out.print("Enter Student ID\t: ");
			String id = input.next();			// nextLine() --> next() 
			if(isDup( student, id, true)){		// if a Duplicate exists -> Id exists
				System.out.print("Student Name \t\t: "+ student[indexOf2D][1] );

				System.out.println("\n");

				if(student[indexOf2D][2] != null | student[indexOf2D][3] != null ) {	//>>>><<<<< Error -> fixed 
					System.out.print("This student's marks have been already added.\n");
					System.out.print("If you want to update the marks, please use [5] Update Marks option.\n");
					System.out.println();

					System.out.print("Do you want to add marks for another student? (Y/n) ");
					loopRunning();
					
					continue;
				}				

				do{
				System.out.print("Programming Fundamentals Marks : ");
				int prfMarks = input.nextInt();	
				marksValid(prfMarks, 2, true);
				} while(addMarksRepeat);

				do{ 
				System.out.print("Database Management System Marks : ");
				int dbmsMarks = input.nextInt();
				marksValid(dbmsMarks, 3, true);
				} while(addMarksRepeat);

				System.out.print("Marks have been added. Do you want to add marks for another student? (Y/n) ");
				loopRunning();

			} else {
				System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
				loopRunning();				
			}

		} while(loopRun);

		loopRun = true;
	}

// Update Student Details ==================================
	
	public static void updateStudentDataHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tUPDATE STUDENT DETAILS"+"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");		
	}
	
	public static void updateStudentData(){
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Enter Student ID\t: ");
			String id = input.next();
			if(isDup( student, id, true)){
				System.out.println("Student Name \t\t: "+ student[indexOf2D][1] );
				System.out.print("\nEnter New Student Name\t: ");
				String newName = input.next();
				student[indexOf2D][1] = newName;
				System.out.print("\nStudent details has been updated successfully");
				System.out.println();

						System.out.print("Do you want to add marks for another student? (Y/n) ");
						loopRunning();
						
						continue;
			} else {
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					loopRunning();			
				}
		} while (loopRun);

		loopRun = true;
	}

// Update Marks ============================================
	
	public static void updateMarksHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tUPDATE MarkS"+"\t\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");		
	}
	
	public static void updateMarks(){
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Enter Student ID\t: ");
			String id = input.next();
			if(isDup( student, id, true)){
				System.out.println("Student Name\t\t: "+student[indexOf2D][1]);
				System.out.println();

						//====check if have marks - start====// 

				if(student[indexOf2D][2] != null | student[indexOf2D][3] != null ) {

					System.out.println("Programming Fundamentals Marks \t\t: "+ student[indexOf2D][2] );	
					System.out.println("Database Management Systems Marks\t: "+ student[indexOf2D][3] );						

					System.out.println();

					do{
						System.out.print("Enter new Programming Fundamentals Marks\t: ");
						int prfMarks = input.nextInt();
						marksValid(prfMarks, 2, true);
					} while (addMarksRepeat);

					do{
						System.out.print("Enter new Database Management Systems Marks\t: ");
						int dbmsMarks = input.nextInt();
						marksValid(dbmsMarks, 3, true);
					} while(addMarksRepeat);

					System.out.print("\nMarks have been updated successfully");
					System.out.println();

					System.out.print("Do you want to add marks for another student? (Y/n) ");
					loopRunning();
					continue;

				}else{
						System.out.println("This student's marks yet to be added.");
						System.out.print("Do you want to add marks for another student? (Y/n) ");
						loopRunning();
						continue;	
					}
						//====check marks have - end====// 

			}else{
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					loopRunning();			
				}
		} while (loopRun);

		loopRun = true;
	}

// Delete Student ==========================================
	public static void deleteStHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tDELETE STUDENT "+"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}

	public static void adjustArray(){

		String[][] temp = new String [student.length-1][4];

		for (int i =0;i<indexOf2D ;i++ ) {
			for (int j=0;j<4 ;j++ ) {
				temp[i][j] = student [i][j];
			}
		}

		for(int i =(indexOf2D +1); i<student.length; i++){
			for (int j=0;j<4 ;j++ ) {
				temp[i-1][j] = student [i][j];
			}			
		}
		student= temp;
	}

	public static void deleteSt(){
		Scanner input = new Scanner(System.in);
		
		do {
			System.out.print("Enter Student ID\t: ");
			String id = input.next();
			if(isDup( student, id, true)){
				System.out.println("Student Name\t\t: "+student[indexOf2D][1]);
				System.out.println();
				
				adjustArray();			// String-> int ==> Integer.valueOf() - https://www.javatpoint.com/java-string-to-int 

				System.out.println("Student has been deleted successfully.");
				System.out.print("Do you want to delete another student? (Y/n) ");
				loopRunning();			

			}else{
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					loopRunning();			
				}			

		} while (loopRun);

		loopRun = true;
	}

// Print St ================================================
	public static void printStHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tPRINT STUDENT DETAILS"+"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}
	public static void printSt(){
		Scanner input = new Scanner(System.in);

		do {
			System.out.print("Enter Student ID\t: ");
			String id = input.next();
			if(isDup( student, id, true)){
				System.out.println("Student Name\t\t: "+student[indexOf2D][1]);
				System.out.println("+---------------------------------------+-------------+");
							
							//==== summery - start ====// 

				if(student[indexOf2D][2] != null | student[indexOf2D][3] != null ) {
					int total = Integer.valueOf(student_2[indexOf2D][4]);		// String-> int ==> Integer.valueOf() - https://www.javatpoint.com/java-string-to-int 
					int rank = ranking(total);
					System.out.println("| Programming Fundamentals Marks \t| "+ student[indexOf2D][2] );	
					System.out.println("| Database Management Systems Marks\t| "+ student[indexOf2D][3] );						
					System.out.println("| Total Marks\t\t\t\t| "+ total);
					System.out.println("| Avg. Marks\t\t\t\t| "+ (double)total/2);
					System.out.print("| Rank\t\t\t\t\t| "+ rank); 
					System.out.println(rank== 1 ? "(First)": rank==2?"(Second)": rank==3?"(Third)": rank==student.length ? "(Last)": "");
					System.out.println("+---------------------------------------+-------------+");
					
 
					System.out.print("Do you want to search another student details? (Y/n) ");
					loopRunning();
					continue;
				}else{
						System.out.println("Marks yet to be added.");
						System.out.print("Do you want to search another student details? (Y/n) ");
						loopRunning();
						continue;	
					}	

						//==== summery - end ====//

			}else{
					System.out.print("Invalid Student ID. Do you want to search again? (Y/n) ");
					loopRunning();			
				}			

		} while (loopRun);

		loopRun = true;
	}

// print st ranks ==========================================

	public static void printStRankHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tPRINT STUDENT RANKS"+"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}
	public static void printStRank(){		
		System.out.println("\n+-------+---------------+---------------+-----------------------+---------------+");		
		System.out.println("|Rank\t|ID\t\t|Name\t\t|Total Marks\t\t|Avg. MArks     |");
		System.out.println("+-------+---------------+---------------+-----------------------+---------------+");

		for (int i=0;i< student_2.length; i++ ) {
			System.out.print("| "+ (i+1)+"\t");
			int id = ranking(sortedSumOfMarks[i], true);
			System.out.print("|"+student[id][0]+"\t\t| "+ student_2[id][1]+ "\t\t| "+ student_2[id][4]+ "\t\t\t| "+ (double)Double.valueOf(student_2[id][4])/2+"          |");
			System.out.println();
		}
		System.out.println("+-------+---------------+---------------+-----------------------+---------------+");
	}

// Best in Programming Fundamentals ========================
	public static void bestPrfHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\tBEST IN PROGRAMMING FUNDAMENTALS"+"\t\t\t\t|");
		System.out.println("---------------------------------------------------------------------------------");
	}	
	public static void bestPrf(){		
		System.out.println("\n+---------------+---------------+-----------------------+---------------+");		
		System.out.println("|ID\t\t|Name\t\t|PF Marks\t\t|DBMS Marks     |");
		System.out.println("+---------------+---------------+-----------------------+---------------+");	
		
		for (int i = (sortedMarks.length-1);i>=0; i-- ) {
			int id = subRanking(sortedMarks[i], 2);	
			System.out.println("|"+ student[id][0] +"\t\t| "+ student[id][1]+ "\t\t| "+student[id][2]+ "\t\t\t| "+ student[id][3]+"            |");
		}
		System.out.println("+---------------+---------------+-----------------------+---------------+");	
	}		 	
	
// Best in DBMS ========================
	public static void bestDbmsHead(){
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println("|\t\tBEST IN DATABASE MANAGEMENT SYSTEMS"+"\t\t\t         |");
		System.out.println("---------------------------------------------------------------------------------");
	}	
	public static void bestDbms(){		
		System.out.println("\n+---------------+---------------+-----------------------+---------------+");		
		System.out.println("|ID\t\t|Name\t\t|DBMS Marks\t\t|PF Marks       |");
		System.out.println("+---------------+---------------+-----------------------+---------------+");	
		
		for (int i = (sortedMarks.length-1);i>=0; i-- ) {
			int id = subRanking(sortedMarks[i], 3);	
			System.out.println("|"+ student[id][0] +"\t\t| "+ student[id][1]+ "\t\t| "+student[id][3]+ "\t\t\t| "+ student[id][2]+"            |");
		}

		System.out.println("+---------------+---------------+-----------------------+---------------+");	
	}

// main meth ===============================================
	public static void main(String args[]){
		home();		
	}
}

// To Do ==============

	//practice insertion sort if asked
	//add console clear @ mentioned last in cw
