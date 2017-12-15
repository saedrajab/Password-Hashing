package password.hashing;

import Jama.Matrix;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;

public class PasswordHashing {
    public static void main(String[] args) {
   
        String pass="";
        int lenghtofPass= 0;
        int offset=0;
        
        Random rand = new Random();
       
        //format output
        DecimalFormat df = new DecimalFormat("###.#");//no decimal
        DecimalFormat df2 = new DecimalFormat("###.##");//2 numb decimal
        
        do{
            
             pass=JOptionPane.showInputDialog("Enter your password (minimum 8 characters): ");
             lenghtofPass=(int)pass.length();
        
        }while(lenghtofPass <8);
        
        //the offset is to get the row size
        offset=(int) Math.sqrt(pass.length());
        
        char[][] myMatrix = new char[offset][lenghtofPass];
        char[] letters = pass.toCharArray();//convert string to set of char

        
        double [][] asciiMatrix=new double[offset][lenghtofPass];
        double [][]timesMatrix=new double[lenghtofPass][offset];
        
          
          
        //insert the string pass to a matrix
        for (int i = 0; i < offset; i++) 
        {
            //for(int j = 0; j < lenghtofPass ; j++)
                // myMatrix [i][j] = letters[j];
            System.arraycopy(letters, 0, myMatrix [i], 0, lenghtofPass);
            
              System.out.println(myMatrix[i]);
              
        }//end for loop
          
         System.out.println("\nASCII Matrix");
        
         // convert to ASCII
         for (int i = 0; i < offset; i++) 
        {
            for(int j = 0; j < lenghtofPass; j++)
            {
                asciiMatrix[i][j]= String.valueOf(letters[j]).codePointAt(0);
                System.out.print(df.format(asciiMatrix[i][j])+" ");
            }
            System.out.println();
        }//end for loop
         
         
         System.out.println("\nThe matrix generated to encrypt with: \n");
         
         
        //matrix to encrypt with
        for (double[] timesMatrix1 : timesMatrix) {
            // do the for in the row according to the column size
            for (int j = 0; j < offset; j++) {
                timesMatrix1[j] = ((int) rand.nextInt(100)-10);
                System.out.print(df.format(timesMatrix1[j]) + " ");
            }
            // add a new line
            System.out.println();
        }
        
        
        
        
        
        
        Matrix A=new Matrix(timesMatrix);
        Matrix B=new Matrix(asciiMatrix);
        Matrix encrypted=B.times(A);
        Matrix inverseMatrix=encrypted.times(A.inverse());
        Matrix decryptedMatrix=encrypted.times(inverseMatrix);
        double deter;
        deter = encrypted.det();
        Matrix trans=encrypted.transpose();
        Matrix hashed=trans.times(encrypted);
        
      
       
        
        double getEncryptedMatrix[][]=encrypted.getArrayCopy();
       
        
        
        
        System.out.println("\nThe encrypted matrix:");
        for (double[] encryptedMatrix : getEncryptedMatrix) {
            for (int j = 0; j < encryptedMatrix.length; j++) {
                System.out.print(df.format(encryptedMatrix[j])+" ");
            }
            System.out.println();
        } //end loop     
        
        //inverse matrix output
        
        double getInverseMatrix[][]=inverseMatrix.getArray();
        
        if(deter!=0)
        {
            System.out.println("\nThe inverse matrix:");
            for (double[] T : getInverseMatrix) {
                for (int j = 0; j < T.length; j++) {
                    System.out.print(" " +df2.format(T[j])+"");
                
                    }
                System.out.println();
            } //end loop
        
        
            double getDecMatrix[][]=decryptedMatrix.getArray();
            System.out.println("\nThe decrypted matrix: ");
            for (double[] K : getDecMatrix) {
                for (int j = 0; j < K.length; j++) {
                    System.out.print(" " +df2.format(K[j])+"");
                
                }
                System.out.println();
            } //end loop
        
        }//end if statment
        
        //get transpose of encrypted matrix
        double getTranspose[][]=trans.getArray();
            System.out.println("\nThe transpose matrix: ");
            for (double[] K : getTranspose) {
                for (int j = 0; j < K.length; j++) {
                    System.out.print(" " +df2.format(K[j])+"");
                
                }
                System.out.println();
            } //end loop
            
            
            //hashed matrix
            double getHashed[][]=hashed.getArray();
            System.out.println("\nThe hashed matrix:\n");
            for (double[] K : getHashed) {
                for (int j = 0; j < K.length; j++) {
                    System.out.print(" " +df2.format(K[j])+"");
                
                }
                System.out.println();
            } //end loop
           int hashValue=getHashed.length*(offset+lenghtofPass);
           int computeHash=Arrays.hashCode(getHashed)*(hashValue+(int)deter);
           String finalHash=""+computeHash;
           char []fh=finalHash.toCharArray();
           String done="";
          
           
        
        
        
        
        System.out.println("\nTenghtofPass: "+lenghtofPass);
        System.out.println("OFFSET: "+offset);
        System.out.println("determinant: "+deter);
        System.out.println("hashValue: "+hashValue);
        System.err.println(""+Arrays.toString(fh));
       
        for(int i=0;i<fh.length;i++)
            done+=String.valueOf(fh[i]).codePointAt(0);
        
        
        JOptionPane.showMessageDialog(null,"The hashed password is: "+done);

    }//end main

 
    
}//end class
