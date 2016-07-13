package com.fourdome.bitmaputils;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class BitmapUtils {
	public static Bitmap getBitmap100k(Bitmap bit, int i) {
		//�ڴ��� ����ѹ��bitmap������
		ByteArrayOutputStream ARRAY = new ByteArrayOutputStream();
		// ѹ������Ҫ�Ĳ��� ����ʽ����̬���ԣ�Ʒ��
		int quality = 100;
		bit.compress(CompressFormat.JPEG, quality, ARRAY);
		while (ARRAY.toByteArray().length > i * 1024) {
			ARRAY.reset();
			quality = quality * 90 / 100;
			bit.compress(CompressFormat.JPEG, quality, ARRAY);
			if(quality<10){
				break;
			}
		}
		bit = BitmapFactory.decodeByteArray(ARRAY.toByteArray(), 0,
				ARRAY.toByteArray().length);
		return bit;

	}
}
