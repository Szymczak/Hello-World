package com.helloworld;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestResult;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Environment;
import android.os.SystemClock;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayway.android.robotium.solo.Solo;

@SuppressWarnings("unchecked")
public class Test extends ActivityInstrumentationTestCase2
{

	protected static final String PASS = "PASS";
	protected static final String FAIL = "FAIL";
	protected String RESULT = FAIL;
	private static int TESTCASEINCREMENTOR = 0; // used for reporting
	protected Solo solo;
	 

	@SuppressWarnings("unchecked")
	public Test(String target_package_id,Class<Activity> launcherActivityClass) throws ClassNotFoundException
	{
		super(target_package_id, launcherActivityClass);
	}

	

	// public int TopNavMenu = 7; // button for top nav menu if on main screen
	public int SaveArticleMenu = 5; // save button in bottom nav menu in article

	
	
	@Override
	protected void setUp() throws Exception
	{
		solo = new Solo(getInstrumentation(), getActivity());
		
		solo.sleep(2000);
		
	}
	
	@Override
	public void tearDown() throws Exception
	{
	
		try
		{
			//reportResult(RESULT,this.getName());
			solo.finalize();
			
		} catch (Throwable e)
		{
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
			
	}
	

	// clicks in the middle of the screen
	public void clickOnSpaceBelowSectionName()
	{
		float screenWidth = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		float screenHeight = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getHeight();
		float x = screenWidth / 2;
		float y = screenHeight / 5;
		solo.clickOnScreen(x, y);
	}
	

	// method to click in the top right corner on the menu nav arrow
	public void clickOnTopNavArrow()
	{
		float screenWidth = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		float screenHeight = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getHeight();
		float x = screenWidth - (screenWidth / 11);
		float y = screenHeight / 16;
		solo.clickOnScreen(x, y);

	}

	public void clickOnTopLeftArticle()
	{
		float screenWidth = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		float screenHeight = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getHeight();
		float x = screenWidth - (screenWidth / 3) * 2;
		float y = screenHeight / 3;
		solo.clickOnScreen(x, y);

	}

	public String removeSpecialChars(String s)
	{

		// s = s.replace("Court &amp; Social","Court[^ ]Social");
		if (s.contains("Court"))
		{
			s = "Court[^ ]Social";
		}

		s = s.replace("$", "[^ ]");
		s = s.replace("*", "[^ ]");
		s = s.replace("&", "[^ ]");
		s = s.replace("£", "[^ ]");
		s = s.replace("@", "[^ ]");
		return s;
	}

	public String removeSpecialChars(String s, Boolean b)
	{

		// s = s.replace("Court &amp; Social","Court[^ ]Social");
		s = s.replace("$", "[^ ]");
		s = s.replace("*", "[^ ]");
		s = s.replace("&", "[^ ]");
		s = s.replace("£", "[^ ]");
		s = s.replace("@", "[^ ]");
		return s;
	}

	// this method will return a list of all the top stories article titles
	public ArrayList<String> getAllTopStoriesTitles()
	{

		ArrayList<String> ArticleTitles = new ArrayList<String>();
		ArrayList<TextView> TextViewsFromFirstScreen = getTextViews();

		for (int i = 0; i < TextViewsFromFirstScreen.size(); i++)
		{

			Log.i("Robotium found TEXT :", TextViewsFromFirstScreen.get(i)
					.getText().toString());

			// Atricle title should appear under top stories if it sees a view
			// with top stories
			if (TextViewsFromFirstScreen.get(i).getText().toString()
					.equals("Top Stories"))
			{
				// then the one below it must be the title if so add it to all
				// titles
				ArticleTitles.add(TextViewsFromFirstScreen.get(i + 1).getText()
						.toString());
			}
		}

		// Scroll down to see more articles
		solo.scrollDown();
		solo.sleep(1000);
		// last string under the Top stories is not an article title so remove
		// it
		ArticleTitles.remove(ArticleTitles.size() - 1);

		ArrayList<TextView> TextViewsAfterScrolledDown = getTextViews();

		for (int i = 0; i < TextViewsAfterScrolledDown.size(); i++)
		{
			// Atricle title should appear under top stories if it sees a view
			// with top stories
			if (TextViewsAfterScrolledDown.get(i).getText().toString()
					.equals("Top Stories"))
			{
				// then the one below it must be the title if so add it to all
				// titles
				ArticleTitles.add(TextViewsAfterScrolledDown.get(i + 1)
						.getText().toString());
			}
		}

		// last string under the Top stories is not an article title so remove
		// it
		ArticleTitles.remove(ArticleTitles.size() - 1);

		for (int i = 0; i < ArticleTitles.size(); i++)
		{
			Log.i("Robotium found those titles u muppet :",
					ArticleTitles.get(i) + "  -article tile num: "
							+ Integer.toString(i));
		}

		// Scroll back up to "entry screen"
		solo.scrollUp();

		return ArticleTitles;

	}

	public ArrayList<View> getCustomViews(String s)
	{

		ArrayList<View> viewList = solo.getCurrentViews();
		ArrayList<View> ViewList = new ArrayList<View>();
		Iterator<View> iterator = viewList.iterator();

		while (iterator.hasNext() && viewList != null)
		{

			View view = iterator.next();

			if (view.getClass().getName().equals(s) && view.isShown())
			{
				ViewList.add((View) view);
			}

		}

		return ViewList;

	}

	public ArrayList getAllCustomViews(String s)
	{
		ArrayList<View> CustomViews = new ArrayList<View>();
		ArrayList<View> CurrentViewsScreenOne = getCustomViews(s);
		solo.scrollDown();
		ArrayList<View> CurrentViewsScreenTwo = getCustomViews(s);

		for (int i = 0; i < CurrentViewsScreenOne.size(); i++)
		{
			CustomViews.add(CurrentViewsScreenOne.get(i));
		}

		for (int i = 0; i < CurrentViewsScreenTwo.size(); i++)
		{
			CustomViews.add(CurrentViewsScreenTwo.get(i));
		}
		solo.scrollUp();

		return CustomViews;
	}

	public ArrayList getAllCustomViews(String s, int TrimSize)
	{
		ArrayList<View> CustomViews = new ArrayList<View>();
		ArrayList<View> CurrentViewsScreenOne = getCustomViews(s);
		TrimSize = TrimSize - 2;

		// Log.i("Trimming: ",Integer.toString(TrimSize));

		for (int i = TrimSize; i > 0; i--)
		{
			// Log.i("Trimming: ",Integer.toString(i));
			// Log.i("TRIMMING: ",CurrentViewsScreenOne.get(CurrentViewsScreenOne.size()-1).getText().toString);
			// CurrentViewsScreenOne.remove(CurrentViewsScreenOne.size()-1);

		}

		CurrentViewsScreenOne.remove(CurrentViewsScreenOne.size() - 1);

		solo.scrollDown();

		ArrayList<View> CurrentViewsScreenTwo = getCustomViews(s);

		for (int i = TrimSize; i > 0; i--)
		{
			Log.i("Trimming: ", Integer.toString(i));
			// CurrentViewsScreenTwo.remove(CurrentViewsScreenTwo.size()-1);
		}

		CurrentViewsScreenTwo.remove(CurrentViewsScreenTwo.size() - 1);
		CurrentViewsScreenTwo.remove(CurrentViewsScreenTwo.size() - 1);

		for (int i = 0; i < CurrentViewsScreenOne.size(); i++)
		{
			CustomViews.add(CurrentViewsScreenOne.get(i));
		}

		for (int i = 0; i < CurrentViewsScreenTwo.size(); i++)
		{
			CustomViews.add(CurrentViewsScreenTwo.get(i));
		}
		solo.scrollUp();

		return CustomViews;
	}

	// method to get all the text views from the current screen
	public ArrayList<TextView> getTextViews()
	{

		ArrayList<View> viewList = solo.getCurrentViews();

		ArrayList<TextView> textViewList = new ArrayList<TextView>();

		Iterator<View> iterator = viewList.iterator();

		while (iterator.hasNext() && viewList != null)
		{

			View view = iterator.next();

			if (view.getClass().getName().equals("android.widget.TextView")
					&& view.getVisibility() != View.INVISIBLE && view.isShown())
			{
				textViewList.add((TextView) view);
			}

		}

		return textViewList;

	}

	public void swipe(String direction)
	{

		float screenWidth = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getWidth();
		float screenHeight = solo.getCurrentActivity().getWindowManager()
				.getDefaultDisplay().getHeight();

		Log.d("Robotium sceenWidth", Float.toString(screenWidth));
		Log.d("Robotium screenHeight", Float.toString(screenHeight));

		long downTime = SystemClock.uptimeMillis();
		// event time MUST be retrieved only by this way!
		long eventTime = SystemClock.uptimeMillis();
		Instrumentation inst = getInstrumentation();

		float xStart = 0;
		float yStart = 0;
		float x0 = 0;
		float y0 = 0;
		float x9 = 0;
		float y9 = 0;
		float x10 = 0;
		float y10 = 0;

		// /////
		float xStartForward = screenWidth - 50;
		float yStartForward = 200;
		float x0Forward = screenWidth - 80;
		float y0Forward = 200;
		float x9Forward = 10;
		float y9Forward = 200;
		float x10Forward = 10;
		float y10Forward = 200;
		// /////
		float xStartBackward = 10;
		float yStartBackward = 200;
		float x0Backward = 15;
		float y0Backward = 200;
		float x9Backward = screenWidth - 50;
		float y9Backward = 200;
		float x10Backward = screenWidth - 50;
		float y10Backward = 200;
		// /////
		float xStartUp = 10;
		float yStartUp = screenHeight - 20;
		float x0Up = 10;
		float y0Up = screenHeight - 50;
		float x9Up = 10;
		float y9Up = 0;
		float x10Up = 10;
		float y10Up = 0;

		if (direction.equals("FORWARD"))
		{
			// swiping right to left
			xStart = xStartForward;
			yStart = yStartForward;
			// simulating thick finger touch
			x0 = x0Forward;
			y0 = y0Forward;
			x9 = x9Forward;
			y9 = y9Forward;
			// release finger, logically to use coords from last movent
			x10 = x10Forward;
			y10 = y10Forward;
		} else if (direction.equals("BACKWARD"))
		{
			// swiping right to left
			xStart = xStartBackward;
			yStart = yStartBackward;
			// simulating thick finger touch
			x0 = x0Backward;
			y0 = y0Backward;
			x9 = x9Backward;
			y9 = y9Backward;
			// release finger, logically to use coords from last movent
			x10 = x10Backward;
			y10 = y10Backward;
		} else if (direction.equals("UP"))
		{
			// swiping right to left
			xStart = xStartUp;
			yStart = yStartUp;
			// simulating thick finger touch
			x0 = x0Up;
			y0 = y0Up;
			x9 = x9Up;
			y9 = y9Up;
			// release finger, logically to use coords from last movent
			x10 = x10Up;
			y10 = y10Up;
		}

		Log.d("Robotium sceenWidth 2 ", Float.toString(screenWidth));
		Log.d("Robotium screenHeight 2", Float.toString(screenHeight));

		try
		{
			// sending event - finger touched the screen
			MotionEvent event = MotionEvent.obtain(downTime, eventTime,
					MotionEvent.ACTION_DOWN, xStart, yStart, 0);
			inst.sendPointerSync(event);
			// sending events - finger is moving over the screen
			eventTime = SystemClock.uptimeMillis();
			event = MotionEvent.obtain(downTime, eventTime,
					MotionEvent.ACTION_MOVE, x0, y0, 0);
			inst.sendPointerSync(event);
			event = MotionEvent.obtain(downTime, eventTime,
					MotionEvent.ACTION_MOVE, x9, y9, 0);
			inst.sendPointerSync(event);
			// release finger, gesture is finished
			event = MotionEvent.obtain(downTime, eventTime,
					MotionEvent.ACTION_UP, x10, y10, 0);
			inst.sendPointerSync(event);

		} catch (Exception ignored)
		{
			Log.d("Robotium tried to swipe", "Swiping exception");
		}
	}

	public void ViewHelper(String Message)
	{
		ArrayList<View> Current = solo.getCurrentViews();

		for (int i = 0; i < Current.size(); i++)
		{
			Log.i("ROBOPS: " + Message, Current.get(i).getClass().getName()
					.toString());
		}
	}

	public void ViewHelper(String Message, Boolean t)
	{
		ArrayList<ImageView> Current = solo.getCurrentImageViews();

		for (int i = 0; i < Current.size(); i++)
		{
			Log.i("ROBOPS: " + Message, Current.get(i).getClass().getName()
					.toString());
		}
	}

	public void checkForProgressBar()
	{
		ArrayList<TextView> CurrentTextView = getTextViews();
		TextView LastTextView = CurrentTextView.get(CurrentTextView.size() - 1);
		TextView ProgressBar = null;

		if (LastTextView.getText().toString()
				.equals("Downloading latest content..."))
		{
			ProgressBar = LastTextView;
			Log.i("This is the last text", ProgressBar.getText().toString());
		}

		if (ProgressBar != null)
		{
			while (ProgressBar.isShown())
			{
				Log.i("IS SHOWN", "IS SHOWN");
				solo.sleep(1000);
			}
		}
	}

	public void scrollToBottom()
	{
		checkForProgressBar();
		solo.scrollDown();
		while (solo.scrollDown())
		{
			solo.scrollDown();
		}

	}

	public static String removeDigits(String text)
	{
		int length = text.length();
		StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++)
		{
			char ch = text.charAt(i);
			if (!Character.isDigit(ch))
			{
				buffer.append(ch);
			}
		}
		return buffer.toString();
	}

	public void running()
	{
		
	//	runOnUiThread();
		
	}
	
	public static boolean containsString(ArrayList<String> List, String s)
	{
		if (List.contains(s))
			return true;
		else
			return false;
	}

	private void reportResult(String result,String name)
	{
		Environment.getExternalStorageState();
		TESTCASEINCREMENTOR++;
		Writer writer = new Writer();
		writer.writeFile(solo.getCurrentActivity().getApplicationContext(), result,name, Integer.toString(TESTCASEINCREMENTOR));

		Log.i("TESTCASE", "TEST " + result + " " + name);
		RESULT = FAIL;
	}
	
	

}

