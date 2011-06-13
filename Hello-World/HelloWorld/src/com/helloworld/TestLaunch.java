package com.helloworld;

import junit.framework.TestCase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.res.Resources;
import android.os.Environment;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressWarnings("unchecked")
public class TestLaunch extends Test
{
	private static final String TESTPACKAGE = "com.helloworld";
	private static final Class LAUNCH_ACTIVITY_CLASS = Launch.class;
	protected static final String PASS = "PASS";
	protected static final String FAIL = "FAIL";
	//protected String RESULT = FAIL;
	private static int TESTCASEINCREMENTOR = 0; // used for reporting
	 

	@SuppressWarnings("unchecked")
	public TestLaunch() throws ClassNotFoundException
	{
		super(TESTPACKAGE, LAUNCH_ACTIVITY_CLASS);
	}

	

	// public int TopNavMenu = 7; // button for top nav menu if on main screen
	public int SaveArticleMenu = 5; // save button in bottom nav menu in article

	
	
	public void testCase1()
	{
		solo.sleep(2000);
		assertTrue(solo.searchText("Hello World!"));
		assertTrue(solo.searchText("bomb ()"));
		
		solo.pressMenuItem(0);
		
		solo.sleep(2000);
		assertTrue(solo.searchText("BOOOM!!!"));
		solo.sleep(2000);
		RESULT=PASS;
	}
	
	
	

	



	

}
