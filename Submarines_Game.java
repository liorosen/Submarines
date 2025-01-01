import java.util.Random;
import java.util.Scanner;

public class Submarines_Game {
        public static Random GENERATOR;
        public static final Scanner SCAN = new Scanner(System.in);

        // Write auxiliary functions between the following lines
        //---  ----------------------------------------------------
        public static void main(String[] args) {
            System.out.println("Enter number:");
            GENERATOR = new Random(SCAN.nextInt());
            // Write the main function code between the following lines
            //-------------------------------------------------------
            int gameboardlength = 10;
            System.out.println("Welcome to The Great Battleship Console Game!");
            System.out.println(" Please enter level :");
            int level = LevelChoosing();
            char [][] tableUser = new char [gameboardlength][gameboardlength];
            char [][] tableComp = new char [gameboardlength][gameboardlength];
            for( int i = 0 ; i < gameboardlength ; i++ ) {
                for(int j = 0 ; j < gameboardlength ; j++) {
                    tableUser[i][j] = ' ';
                    tableComp[i][j] = ' ';
                }
            }
            System.out.println("Explantion about replacing the submarines: for example submarine length 3" + "\n" +
                    " (0,0) = ---" + "\n" + " (1,1) =   45 degrees" + "\n" + " (-1,1) =  135 degrees" + "\n" + " (-1,-1) = 215 degrees"+ "\n" + " (1,-1) =  315 degrees" + "\n" + " (0,-1) =  180 degrees" + "\n" + " (1,0) =   0 degrees"+ "\n" + " (0,1) =   90 degrees"+ "\n" + " (0,-1) =  270 degrees");

            int [] numOfSubsDownForUser = {0,0};
            printTable(tableUser , false);
            int[][][] listOfSubsLocationUser = Getlocation( tableUser , true, false);
            int[][][] listOfSubsLocationComp = Getlocation( tableComp , false , false);
            System.out.println("All battleships have been located successfully !");
            boolean isGameStillOn = true; boolean UserLost = false; boolean CompLost = false;
            while ( isGameStillOn ) {
                DirectionforAttack ( tableUser, tableComp);
                if( Attack(tableUser,tableComp, level , true, listOfSubsLocationComp , numOfSubsDownForUser , true) == true || Attack(tableUser,tableComp, level , false,listOfSubsLocationUser  , numOfSubsDownForUser,true) == true)
                    isGameStillOn = false;
            }
            //----------------------------------------------------------
        }
        public static int LevelChoosing() {
            int level = SCAN.nextInt();
            while( level < 1 || level > 7){
                System.out.println("Error: Invalid Level!");
                level = SCAN.nextInt();
            }
            return level;
        }

        public static void printTable(char [][] table , boolean IsHidden) {
            // building the table of the game:
            for( int i = 9 ; i >= 0 ; i--) {
                System.out.print(i + " |");
                for( int j = 0 ; j < 10 ; j++) {
                    if(IsHidden && table[i][j] == '*') {
                        System.out.print("  "); }
                    else {
                        System.out.print(" " + table[i][j]); }
                }
                System.out.println("");
            }
            System.out.println("    - - - - - - - - - - ");
            System.out.println("    0 1 2 3 4 5 6 7 8 9 ");
        }

        public static boolean IsLocationInputSubsLocate(int a,int b ,int len, int x, int y) {
            boolean ans = true;
            if(((x + len + a > 10) && (a == 1 || a == -1) ) ||
               ((x + len - a > 10) && (a == 1 || a == -1) ) ||
               ((y + len + b > 10) && (b == 1 || b == -1) ) ||
               ((x + len - a > 10) && (b == 1 || b == -1) ) ||
               ((x - len - a < 0) &&  (a == 1 || a == -1) ) ||
               ((x - len + a < 0) &&  (a == 1 || a == -1) ) ||
               ((y - len - b < 0) &&  (b == 1 || b == -1) ) ||
               ((y - len + b < 0) &&  (b == 1 || b == -1) )){
                return false;
            }
            return ans;
        }

        public static boolean IsLocationInputCorrect(char [][] table , int [] cordinatesArray ,int len, boolean Afterlocate, boolean locate) {
            boolean flag = true;
            int b = cordinatesArray[0];
            int a = cordinatesArray[1];
            int y = cordinatesArray[2];
            int x = cordinatesArray[3];
            if(  a >= 10  || b >= 10 || ( x < -1 || x > 1 ) || (y < -1 || y > 1 )) {
                System.out.println("Please choose : " + "\n" + " Two numbers between 0-9 " + "\n" + " Two numbers between 1 to -1 ");
                return false;
            }
            else if ( (x == 0 && y == 0) && len > 0) {
                System.out.println("Error: You chose Zero twice for subs degrees please choose again");
                return false;
            }
            else if (!IsLocationInputSubsLocate(a,b,len,x,y)) {
                System.out.println("Error: your ship location is over the board, please fit the coordinates to the ship size");
                return false;
            }
            else if (locate && ( a < 0 || b < 0 )) {
                System.out.println("Error: Invalid input!");
                System.exit(0);

            }
            else if(( a < 0 || b < 0 )) {
                return false;
            }
            for( int i = 0 ; i <= len ; i++) {
                if(a < 0 || b < 0 || a >= 10 || b >= 10  || (table[a][b] == '*' && Afterlocate == true)) {
                    return false;
                }
                a += x;
                b += y;
            }
            return true;
        }

        public static int [] GetcordinatesArray(int [] array) {
            array[0] = SCAN.nextInt(); // b
            array[1] = SCAN.nextInt(); // a
            array[2] = SCAN.nextInt(); // y
            array[3] = SCAN.nextInt(); // x
            return array;
        }

        public static int [] Getcordinates(boolean IsUser, int subLen, char[][] table, boolean locate) {
            int [] array = new int [4];
            if(IsUser) {
                System.out.println("Enter location for Battleship of size " + subLen + ":" );
                // Getting input from the user : (a,b)= coordinates ; (x,y) = angle of the ship
                GetcordinatesArray(array);
                while( !IsLocationInputCorrect(table,array ,subLen , true , locate)) {
                    System.out.println("Error: Incorrect parameters! Please enter location for the Battleship size " + subLen +  " again:");
                    GetcordinatesArray(array);
                }
            }
            else {
                array[0] = (int)(GENERATOR.nextDouble()*10);
                array[1] = (int)(GENERATOR.nextDouble()*10);
                array[2] = (int)(GENERATOR.nextDouble()*3) - 1 ;
                array[3] = (int)(GENERATOR.nextDouble()*3) - 1 ;

            }
            return array;
        }


        public static int[][][] createListOfSubLocation(int [] Submarines){
            // Submarines =  { {2,1} , {3,2} , {4,1} , {5,1} };

            int [][][] listOfSubLocation = new int [Submarines.length][][];
            for( int i = 0 ; i < listOfSubLocation.length ; i++) {
                int subLen = Submarines[i];
                listOfSubLocation[i] = new int [subLen][2];

            }
            return listOfSubLocation;
        }


        public static int[][][] Getlocation(char [][] table , boolean IsUser , boolean locate) {
            //int [][] Submarines =  { {2,1} , {3,2} , {4,1} , {5,1} }; // {len of the submarine ,how many of them }
            int [] Submarines =  {2,3,3,4,5};
            int [][][] listOfSubLocation = createListOfSubLocation(Submarines);
            int [] cordinatesarray ;
            for( int i = 0 ; i < Submarines.length ; i++) {
                cordinatesarray = Getcordinates(IsUser, Submarines[i], table , locate);
                while (!IsUser && !IsLocationInputCorrect( table , cordinatesarray , Submarines[i] , true , locate)) {
                    cordinatesarray = Getcordinates(IsUser, Submarines[i], table , locate);
                }
                int b = cordinatesarray[0];// change the x , y to put
                int a = cordinatesarray[1];
                int y = cordinatesarray[2];
                int x = cordinatesarray[3];
                for(int j = 0; j < Submarines[i]; j++) {
                    table[a][b] = '*' ;
                    listOfSubLocation[i][j][0] = a;
                    listOfSubLocation[i][j][1] = b;
                    a += x;
                    b += y;
                }
                if( IsUser && i != 4 )
                    printTable(table , false);
            }
            return listOfSubLocation;
        }



        public static void DirectionforAttack (char [][] tableUser,char [][] tableComp) {
            System.out.println("Your following table:");
            printTable(tableComp,true);
            System.out.println("The computer's following table:");
            printTable(tableUser,false);
            System.out.println("It's your turn!");
            System.out.println("Enter coordinates for attack:");
        }

        public static boolean Changing_Var(char [][] tableUser, int a, int b) {
            if(tableUser[a][b] == '*' ) {
                tableUser[a][b] = 'V';
                return true;
            }
            else if(tableUser[a][b] == 'X' || tableUser[a][b] == 'V')
            {
                return false;
            }
            else {
                tableUser[a][b] = 'X';
                return true;
            }
        }

        public static boolean Attack (char [][] tableUser,char [][] tableComp , int level , boolean IsUser, int[][][] listOfSubLocation, int []numSubsDown , boolean locate) {
            boolean endGame = false ;
            if (IsUser) {
                int b = SCAN.nextInt();
                int a = SCAN.nextInt();
                int[] arr = {b, a};
                if( a < 0 ||  b < 0 || a > 9 || b > 9) {
                    System.out.println("Error: Invalid input!");
                }
                while( !Changing_Var(tableComp,arr[1],arr[0])) {
                    System.out.println("Error: Incorrect parameters! Please enter coordinates for attack again:");
                    arr[0] = SCAN.nextInt();
                    arr[1] = SCAN.nextInt();

                }

                endGame = SubmarineIsHit(tableComp, a, b, IsUser, listOfSubLocation, numSubsDown);
                if( endGame == true) { return endGame; }
            }
            else {
                endGame = GetCompAttack (tableUser , level ,  IsUser, listOfSubLocation, numSubsDown);
                if( endGame == true) { return endGame; }
            }
            return endGame;
        }


        public static boolean GetCompAttack (char [][] tableUser , int level , boolean IsUser, int[][][] listOfSubLocation, int[] numSubsDown) {
            boolean ans = false;
            for(int i = 0 ; i < level && !IsUser ; i++) {
                int b = (int)(GENERATOR.nextDouble()*10);
                int a = (int)(GENERATOR.nextDouble()*10);
                if(tableUser[a][b] == '*' ) {
                    tableUser[a][b] = 'V';
                }
                else if(tableUser[a][b] == 'X' || tableUser[a][b] == 'V')
                {
                    i--;
                }
                else {
                    tableUser[a][b] = 'X';
                }
                ans = SubmarineIsHit(tableUser, a, b, IsUser, listOfSubLocation, numSubsDown);
                if(ans)
                    return ans;
            }
            return ans;
        }

        public static boolean SubmarineIsHit(char[][] table , int a , int b , boolean IsUser , int [][][] listOfSubLocation, int[] numSubsDown) {
            for(int i = 0 ; i < listOfSubLocation.length ; i ++) {
                for( int j = 0 ; j < listOfSubLocation[i].length ; j++) {
                    if( listOfSubLocation[i][j][0] == a && listOfSubLocation[i][j][1] == b ) {
                        if(SubmarineIsDown( table , IsUser , listOfSubLocation, i )) {
                            if(IsUser)
                                numSubsDown[1]++;
                            else
                                numSubsDown[0]++;

                        }
                        if(numSubsDown[0] ==  listOfSubLocation.length || numSubsDown[1] ==  listOfSubLocation.length) {
                            if( IsUser) {
                                System.out.println("Congratulations! You are the winner of this great battle!");
                            }
                            else {
                                System.out.println("GAME OVER! You lost...");
                            }
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
            return false;
        }

        public static boolean SubmarineIsDown(char[][] table ,boolean IsUser , int [][][] listOfSubLocation, int subID) {
            boolean IsDown = true;
            for( int k = 0 ; k < listOfSubLocation[subID].length ; k++) {
                if( table[listOfSubLocation[subID][k][0]][listOfSubLocation[subID][k][1]] != 'V') {
                    IsDown = false;
                }
            }
            if( IsDown) {
                if(!IsUser) {
                    System.out.println("Your battleship of size " + listOfSubLocation[subID].length + " has been drowned!");
                }
                else {
                    System.out.println("The computer's battleship of size " + listOfSubLocation[subID].length + " has been drowned!");
                }
            }
            return IsDown;
        }


        //-------------------------------------------------------


}

