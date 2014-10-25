package com.example.handler;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	/* ��UI�߳����view������ */
	private Button getSubID;
	private TextView mainThreadID, subThreadID;

	/* �漰ͨ�ŵ��Ž���Դ�����þ�� */
	private long subID;// ���߳�ID
	private Handler myHandler;// ����ͨ�ŵ�handler

	// ��looper��runnable
	private Runnable runnable;

	// ��looper��runnable�Ĺ���
	class myRunnable implements Runnable {

		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			subThreadID.setText("����Ϣ�����߳����--> ���̵߳�ID �� " + subID);
		}

	}

	// ����handler�����̵߳Ĺ���
	class MySubThread extends Thread {
		@Override
		public void run() {
			// TODO �Զ����ɵķ������
			subID = Thread.currentThread().getId();
			myHandler.post(runnable);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// init handler
		myHandler = new Handler();
		// init runnable
		runnable = new myRunnable();

		setupView();

	}

	private void setupView() {
		// TODO �Զ����ɵķ������
		getSubID = (Button) findViewById(R.id.btn_get_sub_thread_id);
		mainThreadID = (TextView) findViewById(R.id.label_main_thread_id);
		subThreadID = (TextView) findViewById(R.id.label_sub_thread_id);

		mainThreadID.setText("main thread ID : "
				+ Thread.currentThread().getId());

		getSubID.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO �Զ����ɵķ������
		if (view == getSubID) {

			new MySubThread().start();
		}

	}

}
