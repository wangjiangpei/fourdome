package com.example.fragment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.SignalStrength;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import com.example.slidingmenu.BaseApplication;
import com.example.slidingmenu.BaseFragment;
import com.example.slidingmenu.R;
import com.example.slidingmenu.SigninShopActivity;
import com.fourdome.bean.User;
import com.fourdome.bitmaputils.ImageUtils;

/**
 * 左侧滑菜单
 * */
public class MainLeftFragment extends BaseFragment {
	// 相机返回的
	private static final int CAMERA_WITH_DATA = 3023;

	/* 用来标识请求gallery的activity */
	private static final int PHOTO_PICKED_WITH_DATA = 3021;

	/* 拍照的照片存储位置 */
	private static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");
	// 照相机拍照得到的图片
	private File mCurrentPhotoFile;
	ImageView myHead;
	private View view;
	private OnClickListener l;
	private TextView userName;
	private TextView menu_doKeeper;

	private OnClickListener menuListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.hsy_main_left_fragment, container,
				false);
		initViews();
		initDates();
		initOpers();
		loadimage();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		loadimage();
	}

	private void loadimage() {
		Log.i("show", "mainleftresume");
		if (BaseApplication.user != null
				&& BaseApplication.user.getMyHead() != null) {
			loadImage(getActivity(), myHead, BaseApplication.user.getMyHead());
			BaseApplication.main.imageLoader();
			if (BaseApplication.user.getUsername() != null) {
				userName.setText(BaseApplication.user.getUsername());
			}
		}
	}

	private void initViews() {
		userName = (TextView) view.findViewById(R.id.mainleft_name);
		menu_doKeeper = (TextView) view.findViewById(R.id.mainleft_send);
		myHead = (ImageView) view.findViewById(R.id.left_fragment_usernick);
	}

	private void initOpers() {
		myHead.setOnClickListener(l);
		menu_doKeeper.setOnClickListener(menuListener);
	}

	private void initDates() {
		menuListener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				switch (view.getId()) {
				case R.id.mainleft_send:
					//跳转到商家注册
					getActivity()
							.startActivity(
									new Intent(getActivity(),
											SigninShopActivity.class));
					break;

				}
			}
		};

		// 头像点击事件
		l = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String status = Environment.getExternalStorageState();
				// 判断是否有SD卡
				if (status.equals(Environment.MEDIA_MOUNTED)) {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setTitle("提示")
							.setMessage("请选择")
							.setPositiveButton("从相机获取",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// 用户点击了从相机获取
											doTakePhoto();
										}
									})
							.setNegativeButton("从相册获取",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// 用户点击了从相册获取
											doPickPhotoFromGallery();
										}
									}).show();

				} else {
					Toast.makeText(MainLeftFragment.this.getActivity(),
							"没有SD卡", 0).show();
				}
			}
		};
		// -------------------------
	}

	// 调用相册 获取头像图片
	protected void doPickPhotoFromGallery() {
		try {
			// Launch picker to choose photo for selected contact
			final Intent intent = getPhotoPickIntent();
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this.getActivity(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}

	// 封装请求Gallery的intent
	public static Intent getPhotoPickIntent() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setType("image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 180);
		intent.putExtra("outputY", 180);
		intent.putExtra("return-data", true);
		return intent;
	}

	// 因为调用了Camera和Gally所以要判断他们各自的返回情况,他们启动时是这样的startActivityForResult

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != getActivity().RESULT_OK)
			return;

		switch (requestCode) {
		// 调用Gallery返回的
		case PHOTO_PICKED_WITH_DATA: {
			final Bitmap photo = data.getParcelableExtra("data");
			sendUserHeadImage(photo);

			break;
		}
		case CAMERA_WITH_DATA: {
			// 照相机程序返回的,再次调用图片剪辑程序去修剪图片
			doCropPhoto(mCurrentPhotoFile);
			break;
		}
		}
	}

	private void sendUserHeadImage(final Bitmap photo) {
		// File file = new File("/storage/sdcard0/DCIM/Camera/xx.jpg");
		// Log.i("show", "file为"+file.exists()+"是文件");
		// BmobFile bmobFile = new BmobFile(file);

		final BmobFile bmobFile = ImageUtils.bitmapTopath(photo);
		bmobFile.uploadblock(getActivity(), new UploadFileListener() {

			@Override
			public void onSuccess() {
				toasts("上传头像成功");
				User myuser = new User();
				myuser.setMyHead(bmobFile);
				myuser.update(MainLeftFragment.this.getActivity(),
						BaseApplication.user.getObjectId(),
						new UpdateListener() {

							@Override
							public void onSuccess() {

								myHead.setImageBitmap(photo);

							}

							@Override
							public void onFailure(int arg0, String arg1) {

							}

						});
			}

			@Override
			public void onFailure(int arg0, String arg1) {
				toasts("上传头像失败" + arg1);
			}
		});

	}

	private void toasts(String string) {
		Toast.makeText(MainLeftFragment.this.getActivity(), string,
				Toast.LENGTH_SHORT).show();
	}

	protected void doTakePhoto() {
		try {
			// Launch camera to take photo for selected contact
			PHOTO_DIR.mkdirs();// 创建照片的存储目录

			String path = getPhotoFileName();// 给新照的照片文件命名
			mCurrentPhotoFile = new File(Environment
					.getExternalStorageDirectory().getAbsolutePath().toString()
					+ "/path");

			// mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());//
			// 给新照的照片文件命名
			final Intent intent = getTakePickIntent(mCurrentPhotoFile);
			startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(MainLeftFragment.this.getActivity(), e.toString(),
					Toast.LENGTH_LONG).show();
		}
	}

	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.setAction("android.media.action.IMAGE_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
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
				"'IMG'_yyyy-MM-dd-HH-mm-ss");
		return dateFormat.format(date) + ".jpg";
	}

	protected void doCropPhoto(File f) {
		try {
			// 启动gallery去剪辑这个照片
			final Intent intent = getCropImageIntent(Uri.fromFile(f));
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			Toast.makeText(this.getActivity(), e.toString(), Toast.LENGTH_LONG)
					.show();
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
