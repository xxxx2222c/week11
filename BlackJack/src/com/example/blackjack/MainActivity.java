package com.example.blackjack;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.Toast;

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
	public static class PlaceholderFragment extends Fragment implements OnClickListener{

		Button okButton,okButton2,okButton3,hitButton,stayButton,continueButton,quitButton;
		TextView nameTextView,playerTextView,conQuitTextView,m1,m2;
		EditText inputName;
		ArrayList<ImageView> dealerCards,playerCards;
		Blackjack game;
		RelativeLayout layout; 
		Animation animFadein;
		


		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			nameTextView=(TextView)rootView.findViewById(R.id.textView1);
			playerTextView=(TextView)rootView.findViewById(R.id.textView2);
			conQuitTextView=(TextView)rootView.findViewById(R.id.textView3);
			m1=(TextView)rootView.findViewById(R.id.textView4);
			m2=(TextView)rootView.findViewById(R.id.textView5);
			inputName=(EditText)rootView.findViewById(R.id.editText1);
			okButton=(Button)rootView.findViewById(R.id.button1);
			okButton2=(Button)rootView.findViewById(R.id.button6);
			okButton3=(Button)rootView.findViewById(R.id.button7);
			hitButton=(Button)rootView.findViewById(R.id.button2);
			stayButton=(Button)rootView.findViewById(R.id.button3);
			continueButton=(Button)rootView.findViewById(R.id.button4);
			quitButton=(Button)rootView.findViewById(R.id.button5);
			layout =(RelativeLayout)rootView.findViewById(R.id.background);
			animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.myanim);
			
			layout.setBackgroundColor(0x88BBBBFF);
			dealerCards=new ArrayList<ImageView>();
			playerCards=new ArrayList<ImageView>();
			
			okButton.setOnClickListener(this);
			okButton2.setOnClickListener(this);
			okButton3.setOnClickListener(this);
			hitButton.setOnClickListener(this);
			stayButton.setOnClickListener(this);
			continueButton.setOnClickListener(this);
			quitButton.setOnClickListener(this);
			
			okButton2.setVisibility(View.INVISIBLE);
			okButton3.setVisibility(View.INVISIBLE);
			hitButton.setVisibility(View.INVISIBLE);
			stayButton.setVisibility(View.INVISIBLE);
			continueButton.setVisibility(View.INVISIBLE);
			quitButton.setVisibility(View.INVISIBLE);
			playerTextView.setVisibility(View.INVISIBLE);
			conQuitTextView.setVisibility(View.INVISIBLE);
			for(int i=1;i<=10;i++){
				int id1=getIdentifierByString("ImageView"+i);
				int id2;
				if(i<10)
					id2=getIdentifierByString("imageView0"+i);
				else
					id2=getIdentifierByString("imageView"+i);
				
				ImageView v1=(ImageView)rootView.findViewById(id1);
				ImageView v2=(ImageView)rootView.findViewById(id2);
				v1.setVisibility(View.INVISIBLE);
				v2.setVisibility(View.INVISIBLE);
				dealerCards.add(v1);
				playerCards.add(v2);
			}
			return rootView;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int money1=0,money2=0;
			Pattern p=Pattern.compile("[0-9]*");
			Matcher m;
			String s;
			if(v==okButton){
				inputName.setText("");
				nameTextView.setText("請輸入擁有的賭金");
				okButton.setVisibility(View.INVISIBLE);
				okButton2.setVisibility(View.VISIBLE);
			}
			if(v==okButton2){			
				s=inputName.getText().toString();
				m = p.matcher(s);
				inputName.setText("");
			    if(!m.matches() || s.equals("0")){
			        Toast.makeText(getActivity(),"請输入数字或金額必須大於零", Toast.LENGTH_SHORT).show();
			        return;
			    } 
			    if(s.equals("")){
			    	return;
			    }
			    m1.setText(s);
				nameTextView.setText("請輸入本次下注的金額");
				okButton2.setVisibility(View.INVISIBLE);
				okButton3.setVisibility(View.VISIBLE);
			}
			if(v==okButton3){
				s=inputName.getText().toString();
			    m = p.matcher(s);
			    if(!m.matches()  || s.equals("0")){
			        Toast.makeText(getActivity(),"請输入数字或金額必須大於零", Toast.LENGTH_SHORT).show();
			        return;
			    } 
			    if(s.equals("")){
			    	return;
			    }	
			    m2.setText(s);
			    money1=Integer.valueOf(m1.getText().toString());
			    money2=Integer.valueOf(m2.getText().toString());
			    if(money2>money1){
			    	Toast.makeText(getActivity(),"目前擁有金額只剩:"+money1, Toast.LENGTH_SHORT).show();
			    }
			    else{
				InputMethodManager imm=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(inputName.getWindowToken(),0);
				
				nameTextView.setVisibility(View.INVISIBLE);
				
				inputName.setVisibility(View.INVISIBLE);
				okButton3.setVisibility(View.INVISIBLE);
				hitButton.setVisibility(View.VISIBLE);				
				stayButton.setVisibility(View.VISIBLE);
				continueButton.setVisibility(View.INVISIBLE);
				quitButton.setVisibility(View.INVISIBLE);
				playerTextView.setVisibility(View.VISIBLE);
				
				game=new Blackjack(inputName.getText().toString());
				
				for(int i=0;i<2;i++){
					Card card=game.player.card(i);
					ImageView cardView=playerCards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));
					cardView=dealerCards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.setImageResource(R.drawable.cover);
				}
				nameTextView.setText("Dealer's cards: ");
				playerTextView.setText("Player's cards: "+game.player.point()+" points "+game.player.state());
				}
			}
			if(v==hitButton){
				int i=game.player.cardCount();
				if(i>10)
					return;
				ImageView cardView=playerCards.get(i);
				game.hit();
				Card card=game.player.card(i);
				cardView.setVisibility(View.VISIBLE);
				cardView.startAnimation(animFadein);
				cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));
				playerTextView.setText("Player's cards: "+game.player.point()+" points "+game.player.state());
				
				if (game.player.point() > 21){
					v=stayButton;
				}
			}
			if(v==stayButton){
				while(game.dealer.point()<17){
					game.dealer.deal(game.dealer);
				}
				for(int i=0;i<game.dealer.cardCount();i++){
					Card card=game.dealer.card(i);
					ImageView cardView=dealerCards.get(i);
					cardView.setVisibility(View.VISIBLE);
					cardView.startAnimation(animFadein);
					cardView.setImageResource(getCardDrawableByString(card.suit(),card.face()));
				}
				nameTextView.setVisibility(View.VISIBLE);
				nameTextView.setText("Dealer's cards: "+game.dealer.point()+" points "+game.dealer.state());
				conQuitTextView.setVisibility(View.VISIBLE);
				if(game.compete()==1){
					money1=Integer.valueOf(m1.getText().toString());
					money2=Integer.valueOf(m2.getText().toString());
					money1+=money2;
					m1.setText(Integer.toString(money1));
					conQuitTextView.setText("You win!!!  目前還剩金額為:"+m1.getText());
					hitButton.setVisibility(View.INVISIBLE);				
					stayButton.setVisibility(View.INVISIBLE);
					continueButton.setVisibility(View.VISIBLE);
					quitButton.setVisibility(View.VISIBLE);
				}
				else if(game.compete()==-1){
					money1=Integer.valueOf(m1.getText().toString());
					money2=Integer.valueOf(m2.getText().toString());
					money1-=money2;
					m1.setText(Integer.toString(money1));
					conQuitTextView.setText("You lose!!!  目前還剩金額為:"+m1.getText());
					hitButton.setVisibility(View.INVISIBLE);				
					stayButton.setVisibility(View.INVISIBLE);
					continueButton.setVisibility(View.VISIBLE);
					quitButton.setVisibility(View.VISIBLE);
				}
				else{
					conQuitTextView.setText("draw!!!");
					hitButton.setVisibility(View.INVISIBLE);				
					stayButton.setVisibility(View.INVISIBLE);
					continueButton.setVisibility(View.VISIBLE);
					quitButton.setVisibility(View.VISIBLE);
				}
			}
			if(v==continueButton){
				if(m1.getText().equals("0")){
					Toast.makeText(getActivity(),"目前擁有金額只剩:"+money1, Toast.LENGTH_SHORT).show();
					return;
				}
				inputName.setText("");
				nameTextView.setText("請輸入本次下注的金額");
				okButton2.setVisibility(View.INVISIBLE);
				okButton3.setVisibility(View.VISIBLE);			
				for(int i=0;i<10;i++){
					dealerCards.get(i).setVisibility(View.INVISIBLE);
					playerCards.get(i).setVisibility(View.INVISIBLE);
				}
				conQuitTextView.setVisibility(View.INVISIBLE);
				playerTextView.setVisibility(View.INVISIBLE);
				nameTextView.setVisibility(View.VISIBLE);
				inputName.setVisibility(View.VISIBLE);
				okButton3.setVisibility(View.VISIBLE);
				continueButton.setVisibility(View.INVISIBLE);
				quitButton.setVisibility(View.INVISIBLE);
			}
			if(v==quitButton){
				okButton.setVisibility(View.VISIBLE);
				hitButton.setVisibility(View.INVISIBLE);
				stayButton.setVisibility(View.INVISIBLE);
				continueButton.setVisibility(View.INVISIBLE);
				quitButton.setVisibility(View.INVISIBLE);
				playerTextView.setVisibility(View.INVISIBLE);
				conQuitTextView.setVisibility(View.INVISIBLE);
				nameTextView.setVisibility(View.VISIBLE);
				inputName.setVisibility(View.VISIBLE);
				nameTextView.setText("Please input your name");
				for(int i=0;i<10;i++){
					dealerCards.get(i).setVisibility(View.INVISIBLE);
					playerCards.get(i).setVisibility(View.INVISIBLE);
				}
				inputName.setText("");
			}
			
		}
		public int getIdentifierByString(String str){
			int id=getActivity().getResources().getIdentifier(str,"id",getActivity().getPackageName());
			return id;
		}
		public int getCardDrawableByString(String suit,String face){
			int id=getActivity().getResources().getIdentifier(suit+"_"+face,"drawable",getActivity().getPackageName());
			return id;
		}
		
	}
	


}

