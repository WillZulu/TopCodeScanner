package com.example.scanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.compile.Main2;
import com.compile.Program;
import com.compile.Statement;
import com.compile.StatementFactory;
import com.compile.TopCode;
import com.server.Channel;
import com.statements.Box;
import com.statements.Drone;
import com.statements.PotionEffect;
import com.statements.Repeat;
import com.statements.Summon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateModFragment extends Fragment implements OnClickListener
{
	private String tag = "HI";
	private int PHOTO_REQUEST_CODE = 0;
	private int EXTRA_REQUEST_CODE = 1;
	private String modName;
	private ArrayList<Statement> statements;
	private ImageView mImageView;
	private Bitmap bitmap;
	private Uri mImageUri;
	private boolean picTaken = false;
	private Button mCompileButton;
	private Button mModButton;
	private Button mTakePicButton;
	private ImageButton mNextButton;
	private ImageButton mPrevButton;
	private Canvas mCanvas;
	private Statement lastStatement;
	private CustomViewPager mPage;
	private Picture picture = new Picture();
	public boolean isStarted = false;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.create_mod, parent, false);
		setHasOptionsMenu(true);

		mImageView = (ImageView) v.findViewById(R.id.pictureView);
		mTakePicButton = (Button) v.findViewById(R.id.takePicture);
		mModButton = (Button) v.findViewById(R.id.sendModButton);
		mCompileButton = (Button) v.findViewById(R.id.compileCodes);
		mNextButton = (ImageButton) v.findViewById(R.id.nextButton);
		mPrevButton = (ImageButton) v.findViewById(R.id.prevButton);
		mPage = (CustomViewPager) getActivity().findViewById(R.id.viewpager);

		mTakePicButton.setOnClickListener(this);
		mModButton.setOnClickListener(this);
		mCompileButton.setOnClickListener(this);
		mNextButton.setOnClickListener(this);
		mPrevButton.setOnClickListener(this);

		ViewTreeObserver vto = mNextButton.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{
			@Override
			public void onGlobalLayout()
			{
				int bheight = mTakePicButton.getHeight() / 2;
				Bitmap nextMap = BitmapFactory.decodeResource(getResources(), R.drawable.nextarrow);
				Bitmap prevMap = BitmapFactory.decodeResource(getResources(), R.drawable.prevarrow);
				mNextButton.setImageBitmap(Bitmap.createScaledBitmap(nextMap, bheight, bheight, false));
				mPrevButton.setImageBitmap(Bitmap.createScaledBitmap(prevMap, bheight, bheight, false));
				mNextButton.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});

		EditText edit = (EditText) v.findViewById(R.id.modName);
		edit.addTextChangedListener(new TextWatcher()
		{
			public void onTextChanged(CharSequence c, int start, int before, int count)
			{
				modName = c.toString();
			}

			public void beforeTextChanged(CharSequence c, int start, int count, int after)
			{
			}

			public void afterTextChanged(Editable c)
			{
			}
		});

		mImageView.setOnTouchListener(new View.OnTouchListener()
		{
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_DOWN)
					if (picTaken)
					{
						int x = (int) event.getRawX();
						int y = (int) event.getRawY();
						for (Statement s : statements)
						{
							checkForClick(s, x, y);
						}
					}
				return true;
			}
		});
		return v;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.takePicture:
				takePicture();
				isStarted = true;
				break;
			case R.id.sendModButton:
				mod();
				mModButton.setText("Modded");
				break;
			case R.id.compileCodes:
				if (statements.size() > 0)
				{
					mPage.compile();
					mCompileButton.setText("Compiled");
				}
				break;
			case R.id.nextButton:
				mPage.setCurrentItem(mPage.getNextIndex());
				break;
			case R.id.prevButton:
				mPage.setCurrentItem(mPage.getPrevIndex());
				break;
		}

	}

	public ArrayList<Statement> generateStatements(List<TopCode> codes)
	{
		ArrayList<Statement> statements = new ArrayList<Statement>();
		for (TopCode tc : codes)
			statements.add(StatementFactory.createStatement(tc));
		return statements;
	}

	private void takePicture()
	{
		Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
		File file;
		file = picture.createTemporaryFile("picture", ".jpg");
		file.delete();
		mImageUri = Uri.fromFile(file);
		i.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);
		i.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		startActivityForResult(i, PHOTO_REQUEST_CODE);
	}

	public Bitmap grabImage()
	{
		getActivity().getContentResolver().notifyChange(mImageUri, null);
		ContentResolver cr = getActivity().getContentResolver();
		Bitmap btmap = null;
		try
		{
			btmap = android.provider.MediaStore.Images.Media.getBitmap(cr, mImageUri);
		} catch (Exception e)
		{
			Log.e("HI", "ERROR " + e);
		}
		return btmap;
	}

	public void printGatheredCode(ArrayList<String> gatheredCode)
	{
		for (String s : gatheredCode)
		{
			Log.i(tag, s);
		}
	}

	public String compileCodes()
	{
		ArrayList<String> gatheredCode = new ArrayList<String>();
		statements.get(0).getExtras().setName("main");//TODO add naming to function
		Program p = new Program();
		Main2 m2 = new Main2();
		p = m2.compileCodes(statements);
		gatheredCode = p.getCode();
		String allCode = "";
		for (String s : gatheredCode)
			allCode += s;

		printGatheredCode(gatheredCode);
		mModButton.setVisibility(Button.VISIBLE);
		return allCode;
	}

	private void mod()
	{
		if (modName != null)
		{
			new Thread()
			{
				@Override
				public void run()
				{
					UserLoginData data = new UserLoginData();
					Channel channel = new Channel(data.getPasskey(), data.getUsername());
					Log.i(tag, mPage.hold.getCode());
					channel.sendScript(modName, mPage.hold.getCode());
				}
			}.start();
		} else
		{
			Toast.makeText(getActivity(), "Please set a mod name first!", Toast.LENGTH_SHORT).show();
		}
	}

	@SuppressLint("NewApi")
	private void decodeImage(Bitmap photo)// get the codes
	{
		Main2 m2 = new Main2();
		List<TopCode> topCodes = m2.getTopCodes(photo);

		statements = generateStatements(topCodes);

		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		if (android.os.Build.VERSION.SDK_INT >= 13)// newer devices only
			display.getSize(size);
		float width = size.x;
		Bitmap scaledMap = picture.scaleToWidth(bitmap, width);
		Bitmap tempBitmap = Bitmap.createBitmap(scaledMap.getWidth(), scaledMap.getHeight(), Bitmap.Config.RGB_565);

		mCanvas = new Canvas(tempBitmap);
		mCanvas.drawBitmap(scaledMap, 0, 0, null);
		Paint p = new Paint();
		p.setStyle(Paint.Style.STROKE);
		p.setColor(Color.BLACK);

		for (TopCode t : topCodes)// Draw to screen
		{
			float dif = picture.getDifference();
			mCanvas.drawCircle(t.getCenterX() * dif, t.getCenterY() * dif, (t.getDiameter() / 2) * dif, p);
		}

		mImageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
		mCompileButton.setVisibility(Button.VISIBLE);
	}

	@SuppressLint("NewApi")
	public void checkForClick(Statement s, int x, int y)
	{
		TopCode topcode = s.getTopCode();
		float dif = picture.getDifference();
		float cx = (int) topcode.getCenterX() * dif;
		float cy = (int) topcode.getCenterY() * dif;

		Display display = getActivity().getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int sub = size.y - mImageView.getHeight();
		if (Math.pow((x - cx), 2) + Math.pow(((y - sub) - cy), 2) < Math.pow((topcode.getDiameter() / 2) * dif, 2))
		{
			lastStatement = s;
			Intent i = new Intent(getActivity(), InfoActivity.class);
			Bundle b = new Bundle();
			b.putInt("frag_index", mPage.getCurrentItem());
			b.putSerializable("Statement", s);
			i.putExtras(b);
			startActivityForResult(i, EXTRA_REQUEST_CODE);
		}
	}

	public void placeMob(Bitmap mobHead, TopCode tc, String mobName)
	{
		if (mobHead == null)
			return;

		float dif = picture.getDifference();
		float cx = (int) tc.getCenterX() * dif;
		float cy = (int) tc.getCenterY() * dif;
		float size = (tc.getDiameter() * dif) / 2;
		double offset = (Math.abs(Math.toDegrees(tc.getOrientation()) - 90));
		
		Paint p = new Paint();

		Bitmap scaled = Bitmap.createScaledBitmap(mobHead, (int) size, (int) size, false);

		Matrix matrix = new Matrix();
		matrix.postRotate(0f - (float) offset);
		Bitmap rotatedBlock = Bitmap.createBitmap(scaled, 0, 0, scaled.getWidth(), scaled.getHeight(), matrix, true);
		Paint textPaint = new Paint();
		textPaint.setTextSize((tc.getDiameter() * dif) / 3);
		textPaint.setColor(Color.WHITE);
		mCanvas.drawText(mobName, cx + (tc.getDiameter() * dif) * 1.3f, cy + ((tc.getDiameter() * dif) / 4), textPaint);
		if (rotatedBlock != null)
			mCanvas.drawBitmap(rotatedBlock, cx + (tc.getDiameter() * dif), cy + ((tc.getDiameter() * dif) / 4), p);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode != Activity.RESULT_OK)
			return;

		if (requestCode == PHOTO_REQUEST_CODE)
		{
			bitmap = grabImage();
			decodeImage(bitmap);
			picTaken = true;
		}

		if (requestCode == EXTRA_REQUEST_CODE && data != null)
		{
			int id = data.getIntExtra("EXTRA_TYPE", 1000);
			if (id != 1000)
			{
				lastStatement.unpack(data);
				if (id == Repeat.BEGIN)
				{
					placeMob(null, lastStatement.getTopCode(), "" + lastStatement.getExtras().getRepeats());
				} else if (id == Summon.CODE)
				{
					int path = data.getIntExtra("RESULT_ID", R.drawable.pig);
					BitmapDrawable bd = (BitmapDrawable) (getActivity().getResources().getDrawable(path));
					placeMob(bd.getBitmap(), lastStatement.getTopCode(), lastStatement.getExtras().getMob());
				} else if (id == Drone.CODE)
				{
					int path = data.getIntExtra("RESULT_PATH", R.drawable.uparrowicon);
					Bitmap b = ((BitmapDrawable) getActivity().getResources().getDrawable(path)).getBitmap();
					placeMob(b, lastStatement.getTopCode(), "distance = " + lastStatement.getExtras().getDroneDistance());
				} else if (id == Box.CODE)
				{
					int path = data.getIntExtra("RESULT_BLOCK_PATH", R.drawable.grass);
					Bitmap b = ((BitmapDrawable) getActivity().getResources().getDrawable(path)).getBitmap();
					placeMob(b, lastStatement.getTopCode(), lastStatement.getExtras().getBlock());
				} else if (id == PotionEffect.CODE)
				{
					int path = data.getIntExtra("RESULT_POTION_PATH", R.drawable.speed);
					Bitmap b = ((BitmapDrawable) getActivity().getResources().getDrawable(path)).getBitmap();
					placeMob(b, lastStatement.getTopCode(), lastStatement.getExtras().getPotionType());
				}
			}
		}
	}

	public static CreateModFragment newInstance()
	{
		return new CreateModFragment();
	}
}
