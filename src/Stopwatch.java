

import java.awt.*;
import java.awt.event.*;

public class Stopwatch extends Frame implements ActionListener 
{
    Label lblMinutes, lblSeconds, lblMilliseconds, lblTitle, lblMinText, lblSecText, lblMsText;
    Button btnStart, btnStop, btnReset;

    int minutes = 0, seconds = 0, milliseconds = 0;
    boolean running = false;
    Thread thread;

    public Stopwatch() 
    {
        setLayout(null);
        setSize(500, 350);
        setTitle("Stopwatch");
        setBackground(new Color(0, 128, 128)); 
        setLocationRelativeTo(null);

        
        lblTitle = new Label("STOPWATCH", Label.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBackground(new Color(102, 255, 255));
        lblTitle.setBounds(0, 50, 500, 40);
        add(lblTitle);

        
        lblMinText = new Label("Minutes", Label.CENTER);
        lblMinText.setBounds(70, 100, 100, 20);
        add(lblMinText);

        lblSecText = new Label("Seconds", Label.CENTER);
        lblSecText.setBounds(200, 100, 100, 20);
        add(lblSecText);

        lblMsText = new Label("MilliSeconds", Label.CENTER);
        lblMsText.setBounds(330, 100, 100, 20);
        add(lblMsText);

  
        lblMinutes = new Label("00", Label.CENTER);
        lblMinutes.setBackground(Color.cyan);
        lblMinutes.setFont(new Font("Arial", Font.BOLD, 20));
        lblMinutes.setBounds(70, 130, 100, 40);
        add(lblMinutes);

        lblSeconds = new Label("00", Label.CENTER);
        lblSeconds.setBackground(Color.cyan);
        lblSeconds.setFont(new Font("Arial", Font.BOLD, 20));
        lblSeconds.setBounds(200, 130, 100, 40);
        add(lblSeconds);

        lblMilliseconds = new Label("000", Label.CENTER);
        lblMilliseconds.setBackground(Color.cyan);
        lblMilliseconds.setFont(new Font("Arial", Font.BOLD, 20));
        lblMilliseconds.setBounds(330, 130, 100, 40);
        add(lblMilliseconds);

        
        btnStart = new Button("START");
        btnStart.setBounds(70, 200, 100, 30);
        btnStart.setBackground(Color.red);
        btnStart.addActionListener(this);
        add(btnStart);

        btnStop = new Button("STOP");
        btnStop.setBounds(200, 200, 100, 30);
        btnStop.setBackground(Color.red);
        btnStop.addActionListener(this);
        add(btnStop);

        btnReset = new Button("RESET");
        btnReset.setBounds(330, 200, 100, 30);
        btnReset.setBackground(Color.red);
        btnReset.addActionListener(this);
        add(btnReset);

        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                running = false;
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == btnStart) 
        {
            if (!running) 
            {
                running = true;
                startStopwatch();
            }
        } 
        else if (e.getSource() == btnStop)
        {
            running = false;
        } 
        else if (e.getSource() == btnReset)
        {
            running = false;
            minutes = seconds = milliseconds = 0;
            updateLabels();
        }
    }

    public void startStopwatch()
    {
        thread = new Thread(() -> 
        {
            try 
            {
                while (running) 
                {
                    Thread.sleep(10);
                    milliseconds++;
                    if (milliseconds == 100) 
                    {
                        milliseconds = 0;
                        seconds++;
                    }
                    if (seconds == 60)
                    {
                        seconds = 0;
                        minutes++;
                    }
                    updateLabels();
                }
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        });
        thread.start();
    }

    public void updateLabels() 
    {
        lblMinutes.setText(String.format("%02d", minutes));
        lblSeconds.setText(String.format("%02d", seconds));
        lblMilliseconds.setText(String.format("%03d", milliseconds));
    }

    public static void main(String[] args) 
    {
        new Stopwatch();
    }
}
