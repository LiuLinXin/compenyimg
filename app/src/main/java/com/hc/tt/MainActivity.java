package com.hc.tt;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sober_philer.compleximg.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

	private int picNum = 149;

	private ImageView iv;
	private List<Integer> imgRes = new ArrayList<>();
	private int oneHeightWidth;
	private int oneLineNum;
	private Bitmap lastBit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		for(int i=1;i<=picNum;i++){
			imgRes.add(getResources().getIdentifier("tu"+i, "mipmap", getPackageName()));
		}

		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int width = manager.getDefaultDisplay().getWidth();
		lastBit = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
		oneLineNum = (int) Math.ceil(Math.sqrt(picNum));
		oneHeightWidth = width / oneLineNum;
		Log.i("hx", "oneHeightWidth : "+oneHeightWidth);
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i =0, maxI = picNum / oneLineNum+1;i<maxI;i++){
					for(int j=0;j<oneLineNum;j++){
						int nowDex = i*oneLineNum + j;
						Log.i("hx", "nowIndex" + nowDex);
						if(nowDex >= picNum)
							continue;
						decodeOneImg(BitmapFactory.decodeResource(getResources(), imgRes.get(nowDex)), i, j);
					}
				}
				iv.post(new Runnable() {
					@Override
					public void run() {
						try {
						iv.setImageBitmap(lastBit);
						File f = new File(Environment.getExternalStorageDirectory(), "qqfriends.jpg");
						FileOutputStream fos = new FileOutputStream(f);
						lastBit.compress(Bitmap.CompressFormat.JPEG, 100, fos);
							fos.flush();
							fos.close();
						}catch (Exception e){
							Log.e("hx", "exception : "+e.getMessage());
						}
					}
				});
			}
		}).start();
	}

	private void decodeOneImg(Bitmap bitmapT, int row, int line){
		Bitmap bitmap = zoomImg(bitmapT);
		Log.i("hx", "after" + bitmap.getByteCount());

		for(int i=0, maxI = oneHeightWidth;i<maxI;i++){
			for(int j=0, maxJ = oneHeightWidth;j<maxJ;j++){
				Log.i("hx", "y : "+j);
				Log.i("hx", "lastBit height : "+bitmap.getHeight());
				Log.i("hx", "maxJ : "+maxJ);
				int pixel = bitmap.getPixel(i, j);
				lastBit.setPixel(line * oneHeightWidth + i, row * oneHeightWidth + j, pixel);
			}
		}
	}

	private int dp2px(int dp){
		float densityDpi = getResources().getDisplayMetrics().density;
		return (int)(densityDpi*dp+0.5);
	}

	public Bitmap zoomImg(Bitmap bm){
		int width = bm.getWidth();
		int height = bm.getHeight();
		int x=0,y=0;
		if(height > width){
			y = (height - width) / 2;
		}else{
			x = (width - height) / 2;
		}
		// 计算缩放比例
//		float scaleWidth = (float) width / oneHeightWidth;
//		x *= scaleWidth;
//		y *= scaleWidth;
		// 取得想要缩放的matrix参数
//		Matrix matrix = new Matrix();
//		matrix.postScale(scaleWidth, scaleWidth);
		Bitmap scaledBitmap;
		if(bm.getHeight() > bm.getWidth()) {
			scaledBitmap=Bitmap.createScaledBitmap(bm, oneHeightWidth, oneHeightWidth * height / width, true);
		}else{
			scaledBitmap=Bitmap.createScaledBitmap(bm, oneHeightWidth* width / height, oneHeightWidth, true);
		}
//		int i = scaledBitmap.getHeight();
//		int w = scaledBitmap.getWidth();
//		int ss = y + oneHeightWidth;
//		y = (scaledBitmap.getHeight() - scaledBitmap.getWidth()) / 2;
//		// 得到新的图片
//		Bitmap newbm = Bitmap.createBitmap(scaledBitmap, x,y,oneHeightWidth, oneHeightWidth, matrix, true);
		return scaledBitmap;
	}

}
