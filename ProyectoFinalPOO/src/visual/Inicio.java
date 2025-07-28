package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

import logica.Bolsa;
import logica.Persona;
import logica.Solicitud;
import logica.Universitario;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private JPanel btnInicioSesion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//iniciar matching automatico al iniciar la app 
				Bolsa.getInstancia().iniciarMatchingAutomatico();
				
				FileInputStream bolsaIn;
				FileOutputStream bolsaOut;
				ObjectInputStream bolsaRead;
				ObjectOutputStream bolsaWrite;
				try {
					bolsaIn = new FileInputStream("BdLaborea.dat");
					bolsaRead = new ObjectInputStream(bolsaIn);
					Bolsa temp = (Bolsa) bolsaRead.readObject();
					Bolsa.setBolsaLaboral(temp);
					bolsaIn.close();
					bolsaRead.close();
				} catch (FileNotFoundException e) {
					// TODO: handle exception
					try {
						bolsaOut = new FileOutputStream("BdLaborea.dat");
						bolsaWrite = new ObjectOutputStream(bolsaOut);
						Persona aux = new Universitario("U-1", "Omar Jadis", "1234", "8091231234", "omarM@gmail.com",
								"Santiago", "Santiago", "Su casa", true, "Morales Diaz", "M", new Date(), "40215233418", true,
								false, "Ingeniería en Sistemas Computacionales");

						Bolsa.getInstancia().insertarUsuario(aux);
						bolsaWrite.writeObject(Bolsa.getInstancia());
						bolsaOut.close();
						bolsaWrite.close();

					} catch (FileSystemNotFoundException e1) {

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Inicio frame = new Inicio();
					//centralizar guardado en archivos
					frame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							Bolsa.guardarEstado();
							//cerrar el hilo de match
							Bolsa.getInstancia().detenerMatchingAutomatico();
//							System.out.println("Inicio cerro");
						}
					});
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setTitle("Laborea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1938, 1027);

		// poner ventana en centro de pantalla y tamaño maximo
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel(
				"<html><center>Conecta con empresas seg\u00FAn tu perfil. <br> Ingresa y haz crecer tu trayectoria profesional.</center></html>");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		label.setBounds(0, 550, 1917, 82);
		panel.add(label);

		JLabel label_1 = new JLabel("<html><center>Impulsa tu carrera <br>Empieza hoy con Laborea</center></html>");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		label_1.setBounds(0, 162, 1917, 108);
		panel.add(label_1);

		JLabel iconLaborea = new JLabel("");
		iconLaborea.setIcon(new ImageIcon(Inicio.class.getResource("/img/Laborea.png")));
		iconLaborea.setHorizontalAlignment(SwingConstants.CENTER);
		iconLaborea.setBounds(0, 0, 150, 75);
		panel.add(iconLaborea);

		JLabel lblDerechosAutor = new JLabel("\u00A9 2025 Laborea - Desarrollado por Omar Morales y Mauricio Trejo");
		lblDerechosAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDerechosAutor.setForeground(Color.WHITE);
		lblDerechosAutor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblDerechosAutor.setBounds(12, 916, 1881, 62);
		panel.add(lblDerechosAutor);

		btnInicioSesion = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Color de fondo
				g2.setColor(getBackground());

				// Dibuja un rectángulo redondeado (x, y, width, height, arcWidth, arcHeight)
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 80, 80);
			}
		};
		btnInicioSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginUsuarios pantIniciarSesion = new LoginUsuarios(Inicio.this);
				pantIniciarSesion.setModal(true);
				pantIniciarSesion.setVisible(true);
				
			}
		});
		btnInicioSesion.setOpaque(false);
		btnInicioSesion.setBounds(833, 327, 250, 96);
		panel.add(btnInicioSesion);
		btnInicioSesion.setLayout(null);

		JLabel lblIniciarSesion = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblIniciarSesion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIniciarSesion.setBounds(100, 13, 122, 70);
		btnInicioSesion.add(lblIniciarSesion);

		JLabel iconLogin = new JLabel("");
		iconLogin.setHorizontalAlignment(SwingConstants.CENTER);
		iconLogin.setIcon(new ImageIcon(Inicio.class.getResource("/img/enter.png")));
		iconLogin.setBounds(12, 13, 79, 74);
		btnInicioSesion.add(iconLogin);

		JLabel iconFondoInicio = new JLabel("");
		iconFondoInicio.setIcon(new ImageIcon(Inicio.class.getResource("/img/fondoinicio.png")));
		iconFondoInicio.setHorizontalAlignment(SwingConstants.CENTER);
		iconFondoInicio.setBounds(0, 0, 1917, 1000);
		panel.add(iconFondoInicio);

	}
}
