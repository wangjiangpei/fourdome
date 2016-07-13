package com.fourdome.utils;


import java.io.File;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageLoaderUtils {
	private static ImageLoader loader;
	private static ImageLoaderConfiguration.Builder confbuilder;
	private static ImageLoaderConfiguration conf;
	private static DisplayImageOptions.Builder optbuilder;
	private static DisplayImageOptions opt;

	// ����ע��ŵ�imageloader����
	/**
	 * ˫�������
	 * 
	 * @param context
	 * @return
	 */
	@SuppressLint("SdCardPath") @SuppressWarnings("deprecation")
	public static ImageLoader getInstance(Context context) {

		loader = ImageLoader.getInstance();
		confbuilder = new ImageLoaderConfiguration.Builder(context);
		File file = new File("/mnt/sdcard/userhead/imageloader");
		// �ƶ�sdcard�����·��
		confbuilder.discCache(new UnlimitedDiscCache(file));
		// �����ͼƬ����
		confbuilder.discCacheFileCount(100);
		// ������������
		confbuilder.discCacheSize(1024 * 1024 * 20 * 10);
		conf = confbuilder.build();
		loader.init(conf);
		return loader;
	}

	// ������ʾͼƬʹ��opt(listview ����Ļ�ķ���ͼƬ)
	@SuppressWarnings("deprecation")
	public static DisplayImageOptions getOpt() {
		optbuilder = new DisplayImageOptions.Builder();
		// uri ����Ĭ��ͼƬ
		optbuilder.showImageForEmptyUri(R.drawable.sym_def_app_icon);
		// ͼƬ��ȡʧ�� ���ص�Ĭ��ͼƬ
		optbuilder.showImageOnFail(R.drawable.sym_def_app_icon);
		optbuilder.cacheInMemory(true);// ���ڴ滺��
		optbuilder.cacheOnDisc(true);// ��Ӳ�̻���
		opt = optbuilder.build();
		return opt;
	}

	// ������ʾͼƬʹ��opt ListView�� �û���ͷ�񣨻����������û�ͷ��
	public static DisplayImageOptions getOpt2() {
		optbuilder = new DisplayImageOptions.Builder();
		// uri ����Ĭ��ͼƬ
		optbuilder.showImageForEmptyUri(R.drawable.sym_def_app_icon);
		// ͼƬ��ȡʧ�� ���ص�Ĭ��ͼƬ
		optbuilder.showImageOnFail(R.drawable.sym_def_app_icon);
		optbuilder.cacheInMemory(true);// ���ڴ滺��
		optbuilder.cacheOnDisc(true);// ��Ӳ�̻���
		opt = optbuilder.build();
		return opt;
	}
}
