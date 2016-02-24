package com.ocean.anim.layoutTransition;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.TextView;

import com.ocean.anim.R;

public class LikeTopTags extends Activity implements OnClickListener {

	private TextView tv_one;
	private TextView tv_two;
	private TextView tv_three;
	private TextView tv_four;
	private TextView tv_five;
	private TextView tv_six;
	private TextView tv_seven;
	private TextView tv_eight;
	private TextView tv_nine;
	private TextView tv_ten;
	private GridLayout gl_wrapper;
	private LayoutTransition transition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tags);
		findView();
		setOnClick();
		initTransition();
	}

	private void findView() {
		tv_one = (TextView) findViewById(R.id.tv_one);
		tv_two = (TextView) findViewById(R.id.tv_two);
		tv_three = (TextView) findViewById(R.id.tv_three);
		tv_four = (TextView) findViewById(R.id.tv_four);
		tv_five = (TextView) findViewById(R.id.tv_five);
		tv_six = (TextView) findViewById(R.id.tv_six);
		tv_seven = (TextView) findViewById(R.id.tv_seven);
		tv_eight = (TextView) findViewById(R.id.tv_eight);
		tv_nine = (TextView) findViewById(R.id.tv_nine);
		tv_ten = (TextView) findViewById(R.id.tv_ten);
		gl_wrapper = (GridLayout) findViewById(R.id.gl_tag);
	}

	private void setOnClick() {
		tv_one.setOnClickListener(this);
		tv_two.setOnClickListener(this);
		tv_three.setOnClickListener(this);
		tv_four.setOnClickListener(this);
		tv_five.setOnClickListener(this);
		tv_six.setOnClickListener(this);
		tv_seven.setOnClickListener(this);
		tv_eight.setOnClickListener(this);
		tv_nine.setOnClickListener(this);
		tv_ten.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_one:
			case R.id.tv_two:
			case R.id.tv_three:
			case R.id.tv_four:
			case R.id.tv_five:
			case R.id.tv_six:
			case R.id.tv_seven:
			case R.id.tv_eight:
			case R.id.tv_nine:
			case R.id.tv_ten:
			default:
				if (v instanceof TextView) {
					String text = ((TextView) v).getText().toString();
					final TextView tv_tag = new TextView(this);
					tv_tag.setTextColor(0xff000000);
					tv_tag.setBackgroundResource(R.drawable.bg_text);
					tv_tag.setGravity(Gravity.CENTER);
					tv_tag.setText(text);
					gl_wrapper.addView(tv_tag , Math.max(0, gl_wrapper.getChildCount()));
					tv_tag.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							gl_wrapper.removeView(tv_tag);
						}
					});
				}
				break;
		}
	}

	@SuppressLint("NewApi")
	private void initTransition(){

		transition = new LayoutTransition();//这里注意，如果想让GridLayout重新设置动画，必须新建一个LayoutTrasition。

		transition.setAnimator(LayoutTransition.APPEARING,  LayoutAnim.getTransition(transition,
				ObjectAnimator.ofFloat(this, "scaleX", 0, 1) ,LayoutTransition.APPEARING ));
		transition.setAnimator(LayoutTransition.CHANGE_APPEARING,transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
		transition.setAnimator(LayoutTransition.DISAPPEARING,LayoutAnim.getTransition(transition,
				ObjectAnimator.ofFloat(this, "rotationX", 0.0f,360.0f), LayoutTransition.DISAPPEARING));
		transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING,transition.getAnimator(LayoutTransition.CHANGE_DISAPPEARING));
		transition.setAnimator(LayoutTransition.CHANGING,transition.getAnimator(LayoutTransition.CHANGING));

		transition.setDuration(500);

		gl_wrapper.setLayoutTransition(transition);
	}
}
