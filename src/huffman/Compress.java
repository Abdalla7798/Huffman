/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author hp-
 */
import java.io.*;
import java.util.*;


public class Compress {

    private HashMap<String, Integer> Frequencies = new HashMap<String, Integer>();
    private Node root;
    private File outputFile = new File("compressedFile.txt");
    private String internal = "internalNode";
    private HashMap<String, String> CharactersCodes = new HashMap<String, String>();
    private BufferedWriter out;
    
    String filename;
    public Compress(String filename) throws IOException {
        this.filename = filename;
        compress(this.filename);
    }
    
    private void compress(String str) throws IOException {
        
        this.Frequencies = calculateFrequencies(str);   
        PriorityQueue<Node> pq = buildMinHeap();        
        PriorityQueue<Node> listOfNodes = new PriorityQueue<Node>(pq);
        this.root = buildHuffmanTree(pq);
        generateCodes(root, "");
        writeFile(listOfNodes, this.outputFile);
        
    }
    
    private  HashMap<String,Integer> calculateFrequencies(String filename){
        HashMap<String, Integer> freq = new HashMap<String, Integer>();
		try {

			FileInputStream file = new FileInputStream(filename);
			String read;
			while (file.available() >= 1) {
				Character temp = (char) file.read();
				read = temp.toString();
                                
				if (!freq.containsKey(read)){
					freq.put(read, 1);
                                }
                                else{
				freq.put(read, freq.get(read) + 1);
                                }
			}
             
		} catch (IOException e) {
			e.printStackTrace();
		}

		return freq;
    }
    
    private PriorityQueue<Node> buildMinHeap(){
        PriorityQueue<Node> minH = new PriorityQueue<Node>(Frequencies.size(), new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.getFrequency() - o2.getFrequency();
			}
		}); 
        for (int i = 0; i < Frequencies.size(); i++) {
		minH.add(new Node((String) Frequencies.keySet().toArray()[i], Frequencies.get(Frequencies.keySet().toArray()[i]),null, null, null, ""));
	} 
        return minH;
    }  
    private Node buildHuffmanTree (PriorityQueue<Node> pq){
        Node r;
        while (pq.size() != 1) {
                Node o1 = pq.remove();
                Node o2 = pq.remove();
                Node parent = new Node(this.internal, o1.getFrequency() + o2.getFrequency(), null, null, null, "");
                o1.setParent(parent);
                o2.setParent(parent);
                parent.setLeft(o1);
                parent.setRight(o2);
                pq.add(parent);
        }
        r = pq.remove();
        return r;
    }
    
    private void generateCodes(Node node, String str){
        
        if (node == null)
                return;
            generateCodes(node.getLeft(), (str.substring(0, str.length()) + "0"));
            generateCodes(node.getRight(), (str.substring(0, str.length()) + "1"));
            node.setCode(str);
    }
    
    private void writeFile(PriorityQueue<Node> pq, File outputFile) {
        
        try {
            String BinaryString = "";
            double totalFrequencies = 0.0;
            double totalNumberOfbits = 0.0;
            double compressionRatio;
            PriorityQueue<Node> copy = new PriorityQueue<Node>(pq);
            Scanner scanner = new Scanner(new File(this.filename));
            String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            
            out = new BufferedWriter(new FileWriter(outputFile));
            int NumberOfNodes = pq.size();
            for (int i = 0; i < NumberOfNodes; i++) {
				Node q = pq.poll();
				CharactersCodes.put(q.getCharacter(), q.getCode());
                                totalFrequencies += q.getFrequency();
//                                System.out.println((int)q.getCharacter().charAt(0) + "       " +q.getFrequency());
                                totalNumberOfbits += q.getFrequency()*q.getCode().length();
	    } 
            for (int i = 0; i < text.length(); i++)
		BinaryString += CharactersCodes.get(Character.toString(text.charAt(i)));
            
            compressionRatio = totalNumberOfbits/totalFrequencies;
            System.out.println("Compression Ratio is "+compressionRatio);
            
            out.write("*********************Starting Header*********************\n\n");
            out.write("Byte                    Code                    Newcode\n");
                  NumberOfNodes = copy.size();
			for (int i = 0; i < NumberOfNodes; i++) {
				Node q = copy.poll();
                                String character = q.getCharacter();                               
                                int decimalChar = (int)character.charAt(0);
                                int limit = 24-String.valueOf(decimalChar).length();
                                
                                out.write(decimalChar+"");
                                for(int j = 0 ; j < limit ; j++){
                                   out.write(" ");   
                                }
                                
                                String binarycode = Integer.toBinaryString(decimalChar);
                                int len = binarycode.length();
                                if (len<8){
                                    for (int j = 0 ; j <(8-len); j++){
                                        binarycode = "0" + binarycode;
                                    }
                                }
                                out.write(binarycode);
                                for (int j = 0 ; j < (24-binarycode.length());j++){
                                   out.write(" ");  
                                }
                                
                                out.write(q.getCode()+"\n");
			}
            out.write("\n**********************Ending Header**********************\n");
            out.write("\n"+BinaryString + "\n");
            
            out.close();
            
        } catch (Exception e) {
	     System.err.println("Error while writing to file: " + e.getMessage());
        }
    }   
}
