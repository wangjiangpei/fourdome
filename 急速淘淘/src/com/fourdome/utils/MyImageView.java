package com.fourdome.utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

/**
 * 
 * 
 * * 自定义的圆形ImageView，可以直接当组件在布局中使用
 * 
 * 
 * 
 * */
public class MyImageView extends ImageView {
	/*用来标识请求照相功能的activity*/ 
    private static final int CAMERA_WITH_DATA = 3023; 
 
    /*用来标识请求gallery的activity*/ 
    private static final int PHOTO_PICKED_WITH_DATA = 3021; 
 
    /*拍照的照片存储位置*/ 
    private static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera"); 
 
    private File mCurrentPhotoFile;//照相机拍照得到的图片 

	private ImageView image;
	private Paint paint;
	private Context context;

	public MyImageView(Context context) {
		this(context, null);
	}

	public MyImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		paint = new Paint();

	}

	/**
	 * * 绘制圆形图片
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();
		if (null != drawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			Bitmap b = getCircleBitmap(bitmap, 14);
			final Rect rectSrc = new Rect(0, 0, b.getWidth(), b.getHeight());
			final Rect rectDest = new Rect(0, 0, getWidth(), getHeight());
			paint.reset();
			canvas.drawBitmap(b, rectSrc, rectDest, paint);

		} else {
			super.onDraw(canvas);
		}
	}

	/**
	 * * 获取圆形图片方法
	 * 
	 * @param bitmap
	 * @param pixels
	 * @return Bitmap
	 */
	private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		int x = bitmap.getWidth();

		canvas.drawCircle(x / 2, x / 2, x / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void doPickPhotoAction(final Context context) {
		this.context=context;
		// Wrap our context to inflate list items using correct theme
		final Context dialogContext = new ContextThemeWrapper(context,
				android.R.style.Theme_Light);
		String cancel = "返回";
		String[] choices;
		choices = new String[2];
		choices[0] = "从相机获取"; // 拍照
		choices[1] = "从相册获取"; // 从相册中选择
		final ListAdapter adapter = new ArrayAdapter<String>(dialogContext,
				android.R.layout.simple_list_item_1, choices);

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				dialogContext);
		builder.setTitle("选择要使用的程序");
		builder.setSingleChoiceItems(adapter, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						switch (which) {
						case 0: {
							String status = Environment
									.getExternalStorageState();
							if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡
								doTakePhoto();// 用户点击了从照相机获取
							} else {
								Toast.makeText(context, "没有内存卡",
										Toast.LENGTH_SHORT);
							}
							break;

						}
						case 1:
							doPickPhotoFromGallery();// 从相册中去获取
							break;
						}
					}
				});
		builder.setNegativeButton(cancel,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});
		builder.create().show();
	}

	/**
	 * 拍照获取图片
	 * 
	 */
	protected void doTakePhoto() {
		try {
			// Launch camera to take photo for selected contact
			PHOTO_DIR.mkdirs();// 创建照片的存储目录
			mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
			final Intent intent = getTakePickIntent(mCurrentPhotoFile);
			((Activity) context).startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}

	/**
	 * 用当前时间给取得的图片命名
	 * 
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date) + ".jpg";
	}

	// 请求Gallery程序
	protected void doPickPhotoFromGallery() {
		try {
			// Launch picker to choose photo for selected contact
			final Intent intent = getPhotoPickIntent();
			((Activity) context).startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	// 封装请求Gallery的intent
	public static Intent getPhotoPickIntent() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		return intent;
	}

	// 因为调用了Camera和Gally所以要判断他们各自的返回情况,他们启动时是这样的startActivityForResult
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode !=new Activity().RESULT_OK)
			return;
		switch (requestCode) {
		case PHOTO_PICKED_WITH_DATA: {// 调用Gallery返回的
			final Bitmap photo = data.getParcelableExtra("data");
			// 下面就是显示照片了
			image.setImageBitmap(photo);
			// 缓存用户选择的图片
			// img = getBitmapByte(photo);
			// mEditor.setPhotoBitmap(photo);
			break;
		}
		case CAMERA_WITH_DATA: {// 照相机程序返回的,再次调用图片剪辑程序去修剪图片
			doCropPhoto(mCurrentPhotoFile);
			break;
		}
		}
	}

	protected void doCropPhoto(File f) {
		try {
			// 启动gallery去剪辑这个照片
			final Intent intent = getCropImageIntent(Uri.fromFile(f));
			((Activity) context).startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Constructs an intent for image cropping. 调用图片剪辑程序
	 */
	public static Intent getCropImageIntent(Uri photoUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(photoUri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80);
		intent.putExtra("outputY", 80);
		intent.putExtra("return-data", true);
		return intent;
	}
}