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
		BufferedWriter bw1 = null;
		FileWriter fw1 = null;
		ArrayList<Transaction> allRecords = new ArrayList<>();
		ArrayList<CharityEvent> allCharity = new ArrayList<>();
		try
		{
			fr = new FileReader(inputFilename);
			br = new BufferedReader(fr);
			
			fw = new FileWriter(zipFilename);
			bw = new BufferedWriter(fw);
			
			fw1 = new FileWriter(dateFilename);
			bw1 = new BufferedWriter(fw1);
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
				int flag = 0;
				for(CharityEvent c : allCharity)
				{
					if(t.getRecepient_ID().equals(c.getRecepient_ID()))
					{
						if(t.getTransaction_DT().equals(c.getTransaction_DT()))
						{
							flag = 1;
							c.contributions.add(trans_amt);
						}
					}
				}
				//This is the case that this pair of recipient and date is new,
				//so we create a new CharityEvent
				if(flag==0)
				{
					ArrayList<Float> contributions = new ArrayList<>();
					contributions.add(trans_amt);
					CharityEvent newCE = new CharityEvent(rec_ID, trans_date, contributions);
					allCharity.add(newCE);
				}
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
			for(CharityEvent c : allCharity)
			{
				int count = c.contributions.size();
				Collections.sort(c.contributions);
				float median = 0;
				if(count%2 == 0)
				{
					median = (c.contributions.get(count/2) + c.contributions.get(count/2 - 1)) / 2;
					median = Math.round(median);
				}
				
				else
				{
					median = Math.round(c.contributions.get(count/2));
				}
				float total = 0;
				for(float val : c.contributions)
				{
					total = total + val;
				}
				String toWrite = c.getRecepient_ID()+ "|" + c.getTransaction_DT() + "|" + (int)median + "|" + count + "|" + (int)total+"\n";
				bw1.write(toWrite);
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
				
				if(bw1 != null)
					bw1.close();
				
				if(fw1 != null)
					fw1.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}

}
