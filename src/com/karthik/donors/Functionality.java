package com.karthik.donors;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Functionality {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final String inputFilename = "input/itcont.txt";
		final String zipFilename = "output/medianvals_by_zip.txt";
		final String dateFilename = "output/medianvals_by_date.txt";
		BufferedReader br = null;
		FileReader fr = null;
		BufferedWriter bw = null;
		FileWriter fw = null;
		ArrayList<Transaction> allRecords = new ArrayList<>();
		
		try
		{
			fr = new FileReader(inputFilename);
			br = new BufferedReader(fr);
			
			fw = new FileWriter(zipFilename);
			bw = new BufferedWriter(fw);
			String currentLine;
			
			while ((currentLine = br.readLine()) != null) {
				//System.out.println(currentLine);
				String[] tokens = currentLine.split("\\|");
				if( tokens[15].length() > 0)
				{
					continue;
				}
				String rec_ID = tokens[0];
				String zipcode = tokens[10].substring(0, 5);
				String trans_date = tokens[13];
				float trans_amt = Float.parseFloat(tokens[14]);
				//float trans_amt = 5;
				String other_ID = tokens[15];
				Transaction t = new Transaction(rec_ID, zipcode, trans_date, trans_amt, other_ID);
				int count = 1;
				ArrayList<Float> medianCalc = new ArrayList<>();
				medianCalc.add(trans_amt);
				for (Transaction x : allRecords)
				{
					if(t.getRecepient_ID().equals(x.getRecepient_ID()))
					{
						if(t.getZIP_CODE().equals(x.getZIP_CODE()))
						{
							count++;
							medianCalc.add(x.getTransaction_AMT());
						}
					}
				}
				Collections.sort(medianCalc);
				float median = 0;
				if(count%2 == 0)
				{
					median = (medianCalc.get(count/2) + medianCalc.get(count/2 - 1)) / 2;
					median = Math.round(median);
				}
				
				else
				{
					median = Math.round(medianCalc.get(count/2));
				}
				float total = 0;
				for(float val : medianCalc)
				{
					total = total + val;
				}
				
				String toWrite = rec_ID + "|" + zipcode+ "|" + (int)median + "|" + count + "|" + (int)total+"\n";
				bw.write(toWrite);
				allRecords.add(t);
				//System.out.println(t);
				//System.out.println(other_ID);
				//System.out.println(median);
				//System.out.println(toWrite);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();
				
				if(bw != null)
					bw.close();
				
				if(fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}

}
