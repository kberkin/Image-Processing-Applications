package image_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


public class ImageProcessing_Part1 extends JFrame{
	
	getPixels gP;
	tab_0 tab0;
	tab_1 tab1;
	tab_2 tab2;
	tab_3 tab3;
	tab_4 tab4;
	tab_5 tab5;
	
	int width,height,color;
	int gX,gY;
	double gg;
	double teta;
	
	private short[][] pixels;
	private int[][] angles;
	private int[][] mag;
	private int[] bins;
	
	JLabel jl;
	
	int gx[][] = { {-1, 0, 1},
		  	       {-2, 0, 2},
		  	       {-1, 0, 1} };

	int gy[][] = { {1, 2, 1},
			       {0, 0, 0},
			       {-1, -2, -1} };
	
	
	int c = 0;
	int x1,x2,y1,y2;
	
	class getPixels{
		
		public void getP() {
			File f = new File("circle1.jpg");
			try {
				BufferedImage findE = ImageIO.read(f);
				
				width = findE.getWidth();
				height = findE.getHeight();
				
				System.out.println("Width : "+width);
				System.out.println("Height : "+height);
				
				pixels = new short[height][width];
				
				for(int row=0;row<height;row++) {
					for(int col=0;col<width;col++) {
						Color c = new Color(findE.getRGB(col, row));
						color = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
						
						pixels[row][col] = (short) color;
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class tab_0 extends JPanel{
		
		@Override
		public void paintComponent(Graphics g) {
			
			for(int row=0;row<height;row++) {
				for(int col=0;col<width;col++) {
					g.setColor(new Color(pixels[row][col], pixels[row][col], pixels[row][col]));
					g.fillRect(col, row, 1, 1);
				}
			}
		}
	}
	
	class tab_1 extends JPanel{
		
		@Override
		public void paintComponent(Graphics g){
			
			for(int row=1;row<height-1;row++) {
				for(int col=1;col<width-1;col++) {
					
					gX = (gx[0][0]*pixels[row-1][col-1]) + (gx[1][0]*pixels[row][col-1]) + (gx[2][0]*pixels[row+1][col-1])
						+(gx[0][2]*pixels[row-1][col+1]) + (gx[1][2]*pixels[row][col+1]) + (gx[2][2]*pixels[row+1][col+1]);
					
					if(gX < 0) {
						gX = -gX;
					}
					if(gX > 255) {
						gX = 255;
					}
					
					gX = 0xff000000 | (gX << 16) | (gX << 8) | gX;
					g.setColor(new Color(gX));
					
					g.fillRect(col, row, 1, 1);
				}
			}
		}
	}
	
	class tab_2 extends JPanel{
		
		@Override
		public void paintComponent(Graphics g){
			
			for(int col=1;col<width-1;col++) {
				for(int row=1;row<height-1;row++) {
					
					gY = (gy[0][0]*pixels[row-1][col-1]) + (gy[0][1]*pixels[row-1][col]) + (gy[0][2]*pixels[row-1][col+1])
						+(gy[2][0]*pixels[row+1][col-1]) + (gy[2][1]*pixels[row+1][col]) + (gy[2][2]*pixels[row+1][col+1]);
					
					if(gY < 0) {
						gY = -gY;
					}
					if(gY > 255) {
						gY = 255;
					}
					
					gY = 0xff000000 | (gY << 16) | (gY << 8) | gY;
					g.setColor(new Color(gY));
					
					g.fillRect(col, row, 1, 1);
				}
			}
		}
	}
	
	class tab_3 extends JPanel{
		
		@Override
		public void paintComponent(Graphics g){
			
			
			for(int row=1;row<height-1;row++) {
				for(int col=1;col<width-1;col++) {
					
					gX = (gx[0][0]*pixels[row-1][col-1]) + (gx[1][0]*pixels[row][col-1]) + (gx[2][0]*pixels[row+1][col-1])
						+(gx[0][2]*pixels[row-1][col+1]) + (gx[1][2]*pixels[row][col+1]) + (gx[2][2]*pixels[row+1][col+1]);
					gY = (gy[0][0]*pixels[row-1][col-1]) + (gy[0][1]*pixels[row-1][col]) + (gy[0][2]*pixels[row-1][col+1])
						+(gy[2][0]*pixels[row+1][col-1]) + (gy[2][1]*pixels[row+1][col]) + (gy[2][2]*pixels[row+1][col+1]);
					
					
					gg = Math.sqrt(Math.pow((double) gX, 2) + Math.pow((double) gY, 2));
					
					int ggg;
					ggg = (int) gg;
					
					if(ggg < 0) {
						ggg = -ggg;
					}
					if(ggg > 255) {
						ggg = 255;
					}
					
					ggg = 0xff000000 | (ggg << 16) | (ggg << 8) | ggg;
					g.setColor(new Color(ggg));
					
					g.fillRect(col, row, 1, 1);
				}
			}
			
		}
	}
	
	class tab_4 extends JPanel{
		
		@Override
		public void paintComponent(Graphics g){
			angles = new int[height][width];
			mag = new int[height][width];
			
			int checks = 0,checkb = 0,sum;
			
			bins = new int[9];
			
			int[] values = {0,20,40,60,80,100,120,140,160};
			int a;
			
			for(int row=1;row<height-1;row++) {
				for(int col=1;col<width-1;col++) {
					
					gX = (gx[0][0]*pixels[row-1][col-1]) + (gx[1][0]*pixels[row][col-1]) + (gx[2][0]*pixels[row+1][col-1])
						+(gx[0][2]*pixels[row-1][col+1]) + (gx[1][2]*pixels[row][col+1]) + (gx[2][2]*pixels[row+1][col+1]);
					gY = (gy[0][0]*pixels[row-1][col-1]) + (gy[0][1]*pixels[row-1][col]) + (gy[0][2]*pixels[row-1][col+1])
						+(gy[2][0]*pixels[row+1][col-1]) + (gy[2][1]*pixels[row+1][col]) + (gy[2][2]*pixels[row+1][col+1]);
					
					
					gg = Math.sqrt(Math.pow((double) gX, 2) + Math.pow((double) gY, 2));
					mag[row][col] = (int) gg;
					
					if(gX == 0) {
						angles[row][col] = 0;
					}else {
						teta = Math.toDegrees(Math.atan(-gY/gX));
						if(teta < 0) {
							teta = -teta;
						}
						angles[row][col] = (int) teta;
					}
				}
			}
			
			int down = 25,counter = 0;
			for(int row=1;row<height-1;row+=8) {
				for(int col=1;col<width-4;col+=8) {
					a = angles[row][col];
						
					for(int i=row;i<row+8;i++) {
						for(int j=col;j<col+8;j++) {
								for(int k=1;k<values.length-1;k++) {
									if(a > values[k] ) {
										if(a < values[k+1]) {
											checks = a - values[k];
											checkb = values[k+1] - a;
											sum = checks + checkb;
											bins[k] += (mag[row][col]*checkb)/sum;
											bins[k+1] += (mag[row][col]*checks)/sum;
										}
									}
								}
							}
						}
					}
					int ötele = 20;
					g.setColor(Color.ORANGE);
					for(int k=0;k<bins.length;k++) {
						g.drawRect(ötele, down, 25, -bins[k]);
						g.fillRect(ötele, down, 25, -bins[k]);
						ötele = ötele + 50;
					}
					down = down + 75;
			}
			
		}
	}
	
	class tab_5 extends JPanel{
		
		@Override
		public void paintComponent(Graphics g) {
			for(int i=2;i<y2*2;i+=2) {
				for(int j=2;j<x2*2;j+=2) {
					g.setColor(new Color(pixels[i/2][j/2], pixels[i/2][j/2], pixels[i/2][j/2]));
					g.fillRect(j, i, 2, 2);
				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		ImageProcessing_Part1 advf = new ImageProcessing_Part1();
	}
	
	
	
	ImageProcessing_Part1(){
		gP = new getPixels();
		gP.getP();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100, 100);
		this.setSize(700,550);
		this.setVisible(true);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 615, 485);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 12));
		tabbedPane.setForeground(Color.BLUE);
		tabbedPane.setBackground(Color.YELLOW);
		tabbedPane.setToolTipText("Image");
		tabbedPane.setBounds(10, 11, 625, 500);
		panel.add(tabbedPane);
		
		
		
		tab0 = new tab_0();
		tabbedPane.addTab("Grayscale Image", null, tab0, null);
		tab0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if(c == 0) {
						x1 = 0;
						y1 = 0;
					}else {
						x2 = e.getX();
						y2 = e.getY();
					}
					c = c + 1;
			}
		});
		
		
		tab1 = new tab_1();
		tabbedPane.addTab("Horizontal Edges", null, tab1, null);
		
		tab2 = new tab_2();
		tabbedPane.addTab("Vertical Edges", null, tab2, null);
		
		tab3 = new tab_3();
		tabbedPane.addTab("Combined", null, tab3, null);
		
		tab4 = new tab_4();
		tabbedPane.addTab("Histogram", null, tab4, null);
		tab4.setLayout(null);
		
		
		tab5 = new tab_5();
		tabbedPane.addTab("Zoom", null, tab5, null);
	}
}