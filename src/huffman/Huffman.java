/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author hp-
 */
public class Huffman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
          Scanner scanner;
          scanner = new Scanner(System.in);
          System.out.print("Enter the file name: ");
          String fileName = scanner.nextLine();
	  System.out.println("Main Menu\n1-compress\n2-decompress");
          System.out.print("select the required type :");
          int type = scanner.nextInt();
          long start = System.currentTimeMillis();
          switch (type){
              case 1:
                 Compress co = new Compress(fileName+".txt");
              break;
              
              case 2:
//                  decompress();
              break;        
          }
          long end = System.currentTimeMillis();
          System.out.println("Time taken to compress or decompress the file: " + (end - start) + " ms");
          


    }
    
}
