import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class typingtutorgame extends JFrame implements ActionListener {
	private JLabel lbltimer;
	private JLabel lblscore;
	private JLabel lblWord;
	private JTextField txtWord;
	private JButton btnStart;
	private JButton btnStop;
	Timer timer = null;
	int timeremaining = 0;
	int score = 0;
	boolean running = false;
	String[] words = null;

	public typingtutorgame(String[] words) {
		this.words = words;
		GridLayout Layout = new GridLayout(3, 2);
		super.setLayout(Layout);

		Font font = new Font("Comic Sans MS", 1, 150);

		lbltimer = new JLabel("TIMER");
		lbltimer.setFont(font);
		lbltimer.setForeground(Color.white);
		lbltimer.setBackground(Color.red);
		lbltimer.setOpaque(true);
		super.add(lbltimer);

		lblscore = new JLabel("SCORE");
		lblscore.setFont(font);
		lblscore.setForeground(Color.white);
		lblscore.setBackground(Color.black);
		lblscore.setOpaque(true);
		super.add(lblscore);

		lblWord = new JLabel("");
		lblWord.setFont(font);
		lblWord.setForeground(Color.white);
		lblWord.setBackground(Color.gray);
		lblWord.setOpaque(true);
		super.add(lblWord);

		txtWord = new JTextField("");
		txtWord.setFont(font);
		txtWord.setFont(font);
		txtWord.setForeground(Color.white);
		txtWord.setBackground(Color.gray);
		txtWord.setOpaque(true);
		super.add(txtWord);

		btnStart = new JButton("START");
		btnStart.setFont(font);
		
		btnStart.setForeground(Color.white);
		btnStart.setBackground(Color.red);
		btnStart.setOpaque(true);
		btnStart.addActionListener(this);
		super.add(btnStart);

		btnStop = new JButton("STOP");
		btnStop.setFont(font);
		btnStop.setForeground(Color.white);
		btnStop.setBackground(Color.black);
		btnStop.setOpaque(true);
		super.add(btnStop);
		btnStop.addActionListener(this);

		super.setTitle("Typing Tutor");
		super.setSize(500, 500);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		;
		super.setVisible(true);
		setupthegame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnStart) {
			handlestart();

		} else if (e.getSource() == btnStop) {
			handlestop();
		} else if (e.getSource() == timer) {
			handletimer();
		}

	}

	private void handlestart() {
		if (running == false) {
			running = true;
			timer.start();
			btnStart.setText("pause");
			btnStop.setEnabled(true);
			txtWord.setEnabled(true);

		} else {
			running = false;
			timer.stop();
			btnStart.setText("start");
			btnStop.setEnabled(false);
			txtWord.setEnabled(false);
		}
	}

	private void handlestop() {
		timer.stop();
		int choice=JOptionPane.showConfirmDialog(this, "WANT TO REPLAY?");
		if(choice==JOptionPane.YES_OPTION)
		{
			setupthegame();
		}else if(choice==JOptionPane.NO_OPTION)
		{
			super.dispose();
		}else if(choice==JOptionPane.CANCEL_OPTION)
		{
			if(timeremaining>0)
			{
				timer.start();
			}else
			{
				setupthegame();
			}
		}

	}

	private void handletimer() {

		timeremaining--;
		String actual, expected;
		actual = txtWord.getText();
		expected = lblWord.getText();
		if (expected.length() > 0 && actual.equals(expected)) {
			score++;
		}
if(timeremaining==-1)
{
	handlestop();
	return;
}
		lbltimer.setText("time:" + timeremaining);
		lblscore.setText("score:" + score);
		int ridx=(int)(Math.random()*words.length);
		
		lblWord.setText(words[ridx]);
		txtWord.setText("");

	}

	private void setupthegame() {
		timer = new Timer(2000, this);
		timeremaining = 50;
		score = 0;
		lbltimer.setText("Time :" + timeremaining);
		lblscore.setText("Score:" + score);
		lblWord.setText("");
		txtWord.setEnabled(false);
		btnStop.setEnabled(false);
		btnStart.setText("Start");

	}

}
