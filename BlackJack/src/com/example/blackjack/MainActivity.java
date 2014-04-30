package com.example.blackjack;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements OnClickListener {
        TextView nameTextView,playerTextView,conQuitTextView;
        EditText inputname;
        Button okbutton,hitbutton,staybutton,continuebutton,quitbutton;
        ArrayList<ImageView> dealercards,playercards;
        Blackjack game;
		public PlaceholderFragment() {
		}
		public int getIndentifierByString(String str){
			int id = getActivity().getResources().getIdentifier(str,"id",getActivity().getPackageName());
			return id;
		}
		public int getCardDrawableByString(String suit,String face){
			game.money=500;
			game.de=50;
			int id = getActivity().getResources().getIdentifier(suit+"_"+face,"drawable",getActivity().getPackageName());
			return id;
		}
		public void onClick(View v){
			if(v==okbutton)
			{
				InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(inputname.getWindowToken(),0);
				nameTextView.setVisibility(View.INVISIBLE);
				inputname.setVisibility(View.INVISIBLE);
				okbutton.setVisibility(View.INVISIBLE);
				hitbutton.setVisibility(View.VISIBLE);
				staybutton.setVisibility(View.VISIBLE);
				playerTextView.setVisibility(View.VISIBLE);
				conQuitTextView.setVisibility(View.VISIBLE);
				
				game = new Blackjack(inputname.getText().toString());
		        Animation animFadein;
		        animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);
				for(int i=0;i<2;i++)
				{
					dealercards.get(i).setVisibility(View.VISIBLE);		
					Card card = game.player.card(i);
					ImageView cardView = playercards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.startAnimation(animFadein);
					cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));				
				}
				playerTextView.setText(inputname.getText().toString()+"' cards:"+game.player.point()+"points"+game.player.state());
			}
			if(v==hitbutton)
			{
		        Animation animFadein;
		        animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);

				int i=game.player.cardCount();
				if(i>=10)
					return;
				ImageView cardView=playercards.get(i);
				game.hit();
				Card card =game.player.card(i);
				cardView.setVisibility(View.VISIBLE);
				cardView.startAnimation(animFadein);
				cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));
				playerTextView.setText(inputname.getText().toString()+"' cards:"+game.player.point()+"points "+game.player.state());
				if(game.player.point()>=21)
					v=staybutton;
			}
			if(v==staybutton)
			{
		        Animation animFadein;
		        animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);
				int m=game.dealer.cardCount();
				while(game.dealer.point()<18)
				{
					game.dealer.deal(game.dealer);
				}
				for(int i=0;i<m;i++)
				{
					Card card = game.dealer.card(i);
					ImageView cardView = dealercards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.startAnimation(animFadein);
					cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));				
				}
				conQuitTextView.setText("dealer' cards:"+game.dealer.point()+"points "+game.dealer.state());
				hitbutton.setVisibility(View.INVISIBLE);
				staybutton.setVisibility(View.INVISIBLE);
				continuebutton.setVisibility(View.VISIBLE);
				quitbutton.setVisibility(View.VISIBLE);
				nameTextView.setVisibility(View.VISIBLE);
				if(game.compete()==-1)
				{
					game.money=game.money-game.de;
					nameTextView.setText("player lose! money:"+game.money);
				}
				if(game.compete()==1)
				{
					game.money=game.money+game.de;
					nameTextView.setText("player win! money:"+game.money);
				}
			}
			if(v==continuebutton)
			{
		        Animation animFadein;
		        animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);
				InputMethodManager imm=(InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(inputname.getWindowToken(),0);
				nameTextView.setVisibility(View.INVISIBLE);
				inputname.setVisibility(View.INVISIBLE);
				okbutton.setVisibility(View.INVISIBLE);
				hitbutton.setVisibility(View.VISIBLE);
				staybutton.setVisibility(View.VISIBLE);
				continuebutton.setVisibility(View.INVISIBLE);
				quitbutton.setVisibility(View.INVISIBLE);
				playerTextView.setVisibility(View.VISIBLE);
				conQuitTextView.setVisibility(View.VISIBLE);
				
				game = new Blackjack(inputname.getText().toString());
				
				for(int i=0;i<2;i++)
				{
					dealercards.get(i).setVisibility(View.VISIBLE);		
					Card card = game.player.card(i);
					ImageView cardView = playercards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.startAnimation(animFadein);
					cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));				
				}
				playerTextView.setText(inputname.getText().toString()+"' cards:"+game.player.point()+"points"+game.player.state());
			}
			if(v==quitbutton)
			{
				nameTextView.setVisibility(View.VISIBLE);
				inputname.setVisibility(View.VISIBLE);
				okbutton.setVisibility(View.VISIBLE);
				playerTextView.setVisibility(View.INVISIBLE);
				conQuitTextView.setVisibility(View.INVISIBLE);
				continuebutton.setVisibility(View.INVISIBLE);
				quitbutton.setVisibility(View.INVISIBLE);
				nameTextView.setText("name:");
				for(int i=0;i<game.dealer.cardCount();i++)
				    dealercards.get(i).setVisibility(View.INVISIBLE);
				for(int i=0;i<game.player.cardCount();i++)
				    playercards.get(i).setVisibility(View.INVISIBLE);	
			}
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			nameTextView=(TextView)rootView.findViewById(R.id.textView2);
			playerTextView=(TextView)rootView.findViewById(R.id.textView3);
			conQuitTextView=(TextView)rootView.findViewById(R.id.TextView01);
			inputname=(EditText)rootView.findViewById(R.id.editText1);
			okbutton=(Button)rootView.findViewById(R.id.button1);
			hitbutton=(Button)rootView.findViewById(R.id.button2);
			staybutton=(Button)rootView.findViewById(R.id.button3);
			continuebutton=(Button)rootView.findViewById(R.id.button4);
			quitbutton=(Button)rootView.findViewById(R.id.button5);
			dealercards = new ArrayList<ImageView>();
			playercards = new ArrayList<ImageView>();
			for(int i=1;i<=10;i++)
			{
				int id1=getIndentifierByString("ImageView"+i);
				int id2;
				if(i<10)
					id2 = getIndentifierByString("imageView0"+i);
				else
					id2 = getIndentifierByString("imageView"+i);
				
				ImageView v1=(ImageView)rootView.findViewById(id1);
				ImageView v2=(ImageView)rootView.findViewById(id2);
				v1.setVisibility(View.INVISIBLE);
				v2.setVisibility(View.INVISIBLE);
				dealercards.add(v1);
				playercards.add(v2);
			}
			okbutton.setOnClickListener(this);
			hitbutton.setOnClickListener(this);
			staybutton.setOnClickListener(this);
			continuebutton.setOnClickListener(this);
			quitbutton.setOnClickListener(this);
			hitbutton.setVisibility(View.INVISIBLE);
			staybutton.setVisibility(View.INVISIBLE);
			continuebutton.setVisibility(View.INVISIBLE);
			quitbutton.setVisibility(View.INVISIBLE);
			playerTextView.setVisibility(View.INVISIBLE);
			conQuitTextView.setVisibility(View.INVISIBLE);

			return rootView;
		}
	}

}
