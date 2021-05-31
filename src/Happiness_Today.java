/***
 * Today's Happiness
 * 2020115001 
 * 
 */
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class Happiness_Today extends JFrame{

   private JFrame f;   

   ImageIcon icon_Image = new ImageIcon("C:/Users/hse20/eclipse-workspace/COMP217015-JAVA-Team-Project-main/src/img/sad.png");
   Image Face = icon_Image.getImage();

   private int imgX = 12;
   private int imgY = 12;

   MyPanel panel;
   
   public Happiness_Today(JFrame g)
   {
      this.f = g;
   }
   
   public int getHappiness()
   {
      return imgX / 5;
   }

   public void view()
   {
      EventQueue.invokeLater(new Runnable() 
      {
         public void run() 
         {
            try {
               Happiness_Today window = new Happiness_Today();
               window.f.setVisible(true);
            } catch (Exception e) 
            {
               e.printStackTrace();
            }
         }
      });
   }

   public Happiness_Today() {
      initialize_Image();
   }

   class MyPanel extends JPanel 
   {

      @Override
      protected void paintComponent(Graphics g) {
         super.paintComponent(g); 
         g.setColor(Color.BLUE);
         g.fillRoundRect(12, 25, 500, 10, 20, 5); // x, y, width, height, arcWidth, arcHeight
         g.drawImage(Face, imgX, imgY, this);
      }

      public MyPanel() 
      {
         setFocusable(true);

         addMouseMotionListener(new MouseAdapter() 
         { 
            int tmpX = 0;
            
            @Override
            public void mouseDragged(MouseEvent e) 
            {
               
               // 皑沥 蔼
               tmpX = e.getX();
               if (tmpX < 12)
                  imgX = 12;
               else if (tmpX > 500)
                  imgX = 450;
               else
                  imgX = tmpX - 50;
               repaint();
               
               if (imgX < 200)
               {
                  icon_Image = new ImageIcon("C:/Users/hse20/eclipse-workspace/COMP217015-JAVA-Team-Project-main/src/img/sad.png");
                  Face = icon_Image.getImage();
               }
               else if (imgX >= 200 && imgX < 350)
               {   
                  icon_Image = new ImageIcon("C:/Users/hse20/eclipse-workspace/COMP217015-JAVA-Team-Project-main/src/img/soso.png");
                  Face = icon_Image.getImage();
               }
               else if (imgX >= 350)
               {
                  icon_Image = new ImageIcon("C:/Users/hse20/eclipse-workspace/COMP217015-JAVA-Team-Project-main/src/img/happy.png");
                  Face = icon_Image.getImage();
               }
               
               //System.out.println(imgX);
             }
         }
         );
      }
   }

   private void initialize_Image() 
   {
      f = new JFrame();
      f.setBounds(0,0,500,150);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      panel = new MyPanel();
      f.getContentPane().add(panel, BorderLayout.CENTER);
      panel.setLayout(null);
   }
   
   // 皑沥 荐摹 穿利 class
}