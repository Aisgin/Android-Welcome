package com.zhbit.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class CircleImageView extends ImageView {

	/**
	 * �ؼ���ǩ
	 * ��ͼƬ��ȡ��Բ�α߿�
	 */
	private Paint mPaint; // ����

	private int mRadius; // Բ��ͼƬ�İ뾶

	private float mScale; // ͼƬ�����ű���

	public CircleImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ��Ϊ��Բ��ͼƬ������Ӧ���ÿ�߱���һ��
		int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
		mRadius = size / 2;
		setMeasuredDimension(size, size);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		mPaint = new Paint();
		Bitmap bitmap = drawableToBitmap(getDrawable());

		// ��ʼ��BitmapShader������bitmap����
		BitmapShader bitmapShader = new BitmapShader(bitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);

		// �������ű���
		mScale = (mRadius * 2.0f)
				/ Math.min(bitmap.getHeight(), bitmap.getWidth());

		Matrix matrix = new Matrix();
		matrix.setScale(mScale, mScale);
		bitmapShader.setLocalMatrix(matrix);

		mPaint.setShader(bitmapShader);

		// ��Բ�Σ�ָ�������ĵ����ꡢ�뾶������
		canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
	}

	// дһ��drawbleתBitMap�ķ���
	private Bitmap drawableToBitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			BitmapDrawable bd = (BitmapDrawable) drawable;
			return bd.getBitmap();
		}
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}

}
