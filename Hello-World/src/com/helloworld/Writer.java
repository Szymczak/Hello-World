package com.helloworld;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class Writer
{

	public void writeFile(Context context, String result, String testname, String testnumber) {
						
		try {
			
			boolean mExternalStorageAvailable = false;
			boolean mExternalStorageWriteable = false;
			String state = Environment.getExternalStorageState();

			if (Environment.MEDIA_MOUNTED.equals(state)) {
			    // We can read and write the media
			    mExternalStorageAvailable = mExternalStorageWriteable = true;
			
			
			GregorianCalendar cal = new GregorianCalendar();
	        //String filename = "TEST"+"_"+Integer.toString(cal.get(Calendar.DATE))+"_"+cal.getTime().toString()+".xml";
	        //String filename = "TEST RESULTS"+"_"+Integer.toString(cal.get(Calendar.DATE))+".xml";
			String filename = "TEST RESULTS.xml";
			
	        filename=FormatWhiteSpace(filename);
	        Log.i("FILNAME:",filename);
			//File file = new File(context.getExternalFilesDir(null), "TEST"+"_"+cal.get(Calendar.DATE)+"_"+cal.getTime()+".xml");
	        
	        String packageName = context.getPackageName();
	        File externalPath = Environment.getExternalStorageDirectory();
	        //String s =externalPath.getAbsolutePath() + "/Android/data/" + packageName + "/files";
	        File file = new File(externalPath.getAbsolutePath() + "/Android/data/"+filename);
	        
			//File file = new File(context.getExternalFilesDir(null), filename);
			FileWriter fos = new FileWriter(file,true);
			fos.write("Time run: " + cal.get(Calendar.DATE)+" "+cal.getTime());
			fos.write("<br/>");
			fos.write("<b>"+"TESTCASE "+testnumber+"</b>");
			fos.write("<br/>");
			fos.write("Name: "+testname);
			fos.write("<br/>");
			fos.write("Test result: "+result);
			fos.write("<br/>");
			fos.write("<br/>");
			Log.i("Write to File","Writing");
			fos.close();
			
			} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			    // We can only read the media
			    mExternalStorageAvailable = true;
			    mExternalStorageWriteable = false;
			
			} else {
			    // Something else is wrong. It may be one of many other states, but all we need
			    //  to know is we can neither read nor write
			    mExternalStorageAvailable = mExternalStorageWriteable = false;
			}
		}
		catch (IOException e) {
		    Log.i("Exception", e + "");
		}
	}
 


	public String FormatWhiteSpace(String s)
	{
    	s = s.replaceAll(" ", "_");
    	s = s.replaceAll(":","_");
    	//s = s.replaceAll("+","_");
    	return s;
	}

	
	
}

