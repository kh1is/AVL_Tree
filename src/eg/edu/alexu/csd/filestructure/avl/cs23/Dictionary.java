package eg.edu.alexu.csd.filestructure.avl.cs23;

import eg.edu.alexu.csd.filestructure.avl.IDictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary implements IDictionary {

	private AVLTree<String> treeDictionary = new AVLTree<>();

	@Override
	public void load(File file){
		// TODO Auto-generated method stub
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String st;
			while ((st = br.readLine()) != null)
				treeDictionary.insert(st);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public boolean insert(String word) {
		// TODO Auto-generated method stub
		try{
			treeDictionary.insert(word);
			return true;
		}
		catch(Exception e){
			return false;
		}

	}
	@Override
	public boolean exists(String word) {
		// TODO Auto-generated method stub
		return treeDictionary.search(word);
	}
	@Override
	public boolean delete(String word) {
		// TODO Auto-generated method stub
		return treeDictionary.delete(word);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return treeDictionary.size;
	}
	@Override
	public int height() {
		// TODO Auto-generated method stub
		return treeDictionary.height();
	}

	public void printDictionary(){
		treeDictionary.printPreorder(treeDictionary.root);
	}
}