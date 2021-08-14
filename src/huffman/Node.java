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
public class Node {
    
        private String character;
	private int frequency;
	private Node right;
	private Node left;
	private Node parent;
	private String code;
        
        public Node(String character, int frequency, Node right, Node left, Node parent, String code) {
		this.character = character;
		this.frequency = frequency;
		this.right = right;
		this.left = left;
		this.parent = parent;
		this.code = code;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCharacter() {
		return character;
	}

	public int getFrequency() {
		return frequency;
	}

	public Node getRight() {
		return right;
	}

	public Node getLeft() {
		return left;
	}

	public Node getParent() {
		return parent;
	}

	public String getCode() {
		return code;
	}
	
}
