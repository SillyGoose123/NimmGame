import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NimmMain  extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel münzInf;
	private JLabel infStat;
	private int münzen;
	private int humantokens;
	private boolean compwin = false;
	private boolean playerWin = false;
	private boolean Playing = false;
	
	public NimmMain() {
		setTitle("NimmGame");
		setSize(600,400);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Nimmgame.png")));
		setBackground(Color.darkGray);
		setLocationRelativeTo(null);
		setResizable(false);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Layout & Container
		Container pane = getContentPane();
		GridLayout GL = new GridLayout(5,0);
		pane.setLayout(GL);
		
		//Buttons
			
			//Button 1 
				button1 = new JButton("Start");
				button1.setFont(new Font("Bahnschrift Semi Bold", Font.BOLD, 25));
				button1.setFocusable(false);
				pane.add(button1);
				button1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Playing == true) {
							if(münzen != 0) {
								münzen -= 1;
								humantokens = 1; 
								Computer();
							} else {
								compwin = true;
								playerWin = false;
								lock();
							}
							
						} else {
							Play();
						}
							
					}
					
				});
			
			//Button 2
				button2 = new JButton("2");
				button2.setFont(new Font("Bahnschrift Semi Bold", Font.BOLD, 25));
				button2.setFocusable(false);
				pane.add(button2);
				button2.addActionListener(new ActionListener() {
					
					

					@Override
					public void actionPerformed(ActionEvent e) {
						if(Playing == true) {
							if(münzen != 0) {
								münzen -= 2;
								humantokens = 2; 
								Computer();
							} else {
								compwin = true;
								playerWin = false;
								lock();
							}
						
						} else {
							System.exit(0);
						}
						}
				});
				
			//Button 3
				button3 = new JButton("3");
				button3.setFont(new Font("Bahnschrift Semi Bold", Font.BOLD, 25));
				button3.setFocusable(false);
				pane.add(button3);
				button3.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(münzen != 0) {
							münzen -= 3;
							humantokens = 3; 
							Computer();
						} else {
							compwin = true;
							lock();
						}
					}
				});
				
		//Lables
				
				//Label1
				münzInf = new JLabel("", SwingConstants.CENTER);
				münzInf.setAlignmentY(CENTER_ALIGNMENT);
				münzInf.setFont(new Font("Bahnschrift Semi Bold", Font.BOLD, 25));
				pane.add(münzInf);
				
				//Labe2
				infStat = new JLabel("", SwingConstants.CENTER);
				infStat.setAlignmentY(CENTER_ALIGNMENT);
				infStat.setFont(new Font("Bahnschrift Semi Bold", Font.BOLD, 25));
				pane.add(infStat);
				 
		Start();
	}
	
	private void Start() {
		münzen = (int) ((Math.random() * (20 - 10)) + 10);
		MakeLabelText(münzInf, str(münzen));
		visible(false);
	}
	
	private void PlayAgain() {
		münzen = (int) ((Math.random() * (20 - 10)) + 10);
		MakeLabelText(münzInf, str(münzen));
		compwin = false;
		playerWin = false;
		visible(false);
	    button1.setEnabled(true);
	    button1.setText("Play Again");
	    button2.setEnabled(true);
	    button2.setVisible(true);
	    button2.setText("Quit");
	}
	
	private void Update() {
		MakeLabelText(münzInf, str(münzen));
		if(playerWin == true) {
			MakeLabelText(infStat, "Du hast Gewonnen");
			Playing = false;
			PlayAgain();
		} else if(compwin == true) {
			MakeLabelText(infStat, "Du hast Verloren");
			Playing = false;
			PlayAgain();
		}
	}
	
	private void Play() {
		Playing  = true;
		visible(true);
		button1.setText("1");
		button2.setText("2");
		delock();
		}
	
	
	private void Computer() {
		lock();
		if(münzen > 0) {
			int computertokens = 4 - humantokens;
				if(münzen - computertokens > 0) {
					münzen -= computertokens;
					MakeLabelText(infStat, "Computer zieht " + computertokens );
				} else   {
					computertokens = 1;
					while(münzen - computertokens > 0){
						computertokens++;
					}
					münzen -= computertokens;
					MakeLabelText(infStat, "Computer zieht " + computertokens );
				} 
				
				if(münzen == 0) {
					playerWin = true;
					compwin = false;
				}
					
		} else  {
			compwin = true;
			playerWin = false;
		}
			
		delock();
		Update();
	}
	
	private String str(int i) {
		String str = String.valueOf(i);
		return str;
	}
	
	private void visible(boolean b) {
		button2.setVisible(b);
	    button3.setVisible(b);
	    münzInf.setVisible(b);
	}
	
	private void MakeLabelText(JLabel L , String Text) {
		L.setText(Text);	
	}
	
	private void lock() {
	button1.setEnabled(false);
	button2.setEnabled(false);
	button3.setEnabled(false);
	Update();
	}
	
	private void delock() {
		button1.setEnabled(true);
		button2.setEnabled(true);
		button3.setEnabled(true);
	}

	public static void main(String[] args) {
		NimmMain m = new NimmMain();
		m.setVisible(true);

	}

}
