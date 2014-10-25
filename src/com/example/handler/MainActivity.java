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

	/* 主UI线程相关view组件句柄 */
	private Button getSubID;
	private TextView mainThreadID, subThreadID;

	/* 涉及通信的桥接资源的引用句柄 */
	private long subID;// 子线程ID
	private Handler myHandler;// 用于通信的handler

	// 待looper的runnable
	private Runnable runnable;

	// 待looper的runnable的构造
	class myRunnable implements Runnable {

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			subThreadID.setText("此信息由子线程输出--> 子线程的ID ： " + subID);
		}

	}

	// 操作handler的子线程的构造
	class MySubThread extends Thread {
		@Override
		public void run() {
			// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		getSubID = (Button) findViewById(R.id.btn_get_sub_thread_id);
		mainThreadID = (TextView) findViewById(R.id.label_main_thread_id);
		subThreadID = (TextView) findViewById(R.id.label_sub_thread_id);

		mainThreadID.setText("main thread ID : "
				+ Thread.currentThread().getId());

		getSubID.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO 自动生成的方法存根
		if (view == getSubID) {

			new MySubThread().start();
		}

	}

}
