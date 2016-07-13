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
 * ��໬�˵�
 * */
public class MainLeftFragment extends BaseFragment {
	// ������ص�
	private static final int CAMERA_WITH_DATA = 3023;

	/* ������ʶ����gallery��activity */
	private static final int PHOTO_PICKED_WITH_DATA = 3021;

	/* ���յ���Ƭ�洢λ�� */
	private static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");
	// ��������յõ���ͼƬ
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
					//��ת���̼�ע��
					getActivity()
							.startActivity(
									new Intent(getActivity(),
											SigninShopActivity.class));
					break;

				}
			}
		};

		// ͷ�����¼�
		l = new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String status = Environment.getExternalStorageState();
				// �ж��Ƿ���SD��
				if (status.equals(Environment.MEDIA_MOUNTED)) {

					AlertDialog.Builder builder = new AlertDialog.Builder(
							getActivity());
					builder.setTitle("��ʾ")
							.setMessage("��ѡ��")
							.setPositiveButton("�������ȡ",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// �û�����˴������ȡ
											doTakePhoto();
										}
									})
							.setNegativeButton("������ȡ",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// �û�����˴�����ȡ
											doPickPhotoFromGallery();
										}
									}).show();

				} else {
					Toast.makeText(MainLeftFragment.this.getActivity(),
							"û��SD��", 0).show();
				}
			}
		};
		// -------------------------
	}

	// ������� ��ȡͷ��ͼƬ
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

	// ��װ����Gallery��intent
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

	// ��Ϊ������Camera��Gally����Ҫ�ж����Ǹ��Եķ������,��������ʱ��������startActivityForResult

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != getActivity().RESULT_OK)
			return;

		switch (requestCode) {
		// ����Gallery���ص�
		case PHOTO_PICKED_WITH_DATA: {
			final Bitmap photo = data.getParcelableExtra("data");
			sendUserHeadImage(photo);

			break;
		}
		case CAMERA_WITH_DATA: {
			// ��������򷵻ص�,�ٴε���ͼƬ��������ȥ�޼�ͼƬ
			doCropPhoto(mCurrentPhotoFile);
			break;
		}
		}
	}

	private void sendUserHeadImage(final Bitmap photo) {
		// File file = new File("/storage/sdcard0/DCIM/Camera/xx.jpg");
		// Log.i("show", "fileΪ"+file.exists()+"���ļ�");
		// BmobFile bmobFile = new BmobFile(file);

		final BmobFile bmobFile = ImageUtils.bitmapTopath(photo);
		bmobFile.uploadblock(getActivity(), new UploadFileListener() {

			@Override
			public void onSuccess() {
				toasts("�ϴ�ͷ��ɹ�");
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
				toasts("�ϴ�ͷ��ʧ��" + arg1);
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
			PHOTO_DIR.mkdirs();// ������Ƭ�Ĵ洢Ŀ¼

			String path = getPhotoFileName();// �����յ���Ƭ�ļ�����
			mCurrentPhotoFile = new File(Environment
					.getExternalStorageDirectory().getAbsolutePath().toString()
					+ "/path");

			// mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());//
			// �����յ���Ƭ�ļ�����
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
	 * �õ�ǰʱ���ȡ�õ�ͼƬ����
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
			// ����galleryȥ���������Ƭ
			final Intent intent = getCropImageIntent(Uri.fromFile(f));
			startActivityForResult(intent, PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			Toast.makeText(this.getActivity(), e.toString(), Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * Constructs an intent for image cropping. ����ͼƬ��������
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
