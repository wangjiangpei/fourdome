package com.fourdome.bitmaputils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




import cn.bmob.v3.datatype.BmobFile;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

public class ImageUtils {

	private static final File PHOTO_DIR = new File(Environment
			.getExternalStorageDirectory().toString()+"/DCIM/Camera");

	public static BmobFile bitmapTopath(Bitmap bitmap)  {
		String status = Environment.getExternalStorageState();
		BmobFile bmobFile = null;
		if (status.equals("mounted")) {
			Log.i("show", "这里执行了"+status);
			if (!PHOTO_DIR.exists()) {
				PHOTO_DIR.mkdirs();
			}
			String path = getPhotoFileName().trim();// 给新照的照片文件命名
			File file = new File(PHOTO_DIR.getAbsolutePath()
					+ File.separator+path);
//			if (file.exists()) {
//				file.delete();
//			}
			

				FileOutputStream fos;
				try {
					fos = new FileOutputStream(file);
				bitmap = BitmapUtils.getBitmap100k(bitmap, 50);
				if (bitmap.compress(Bitmap.CompressFormat.JPEG,80, fos)) {
					Log.i("show", "保存本地成功");
					Log.i("show", "file=" + file.toString());
					bmobFile = new BmobFile(file);
				}else{
					Log.i("show", "保存本地失败");
				}
				fos.flush();
				fos.close();
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}else{
			Log.i("show", "内存卡错误");
		}
		return bmobFile;
	}

	/**
	 * 用当前时间给取得的图片命名
	 * 
	 */
	public static String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyy-MM-dd_HH-mm-ss");
		return dateFormat.format(date) + ".png";
	}
}
