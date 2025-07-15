package visual;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
		setBounds(100, 100, 450, 300);

		//poner ventana en centro de pantalla y tamaño maximo
		dim = getToolkit().getScreenSize(); // obtener dimensiones de la pantalla de la pc
		setSize(dim.width, dim.height);
		setLocationRelativeTo(null);


		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblencuentraVacantesSegn = new JLabel("<html><center>Conecta con empresas seg\u00FAn tu perfil. <br> Ingresa y haz crecer tu trayectoria profesional.</center></html>");
		lblencuentraVacantesSegn.setForeground(Color.WHITE);
		lblencuentraVacantesSegn.setHorizontalAlignment(SwingConstants.CENTER);
		lblencuentraVacantesSegn.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblencuentraVacantesSegn.setBounds(0, 617, 1904, 107);
		contentPane.add(lblencuentraVacantesSegn);

		JLabel lblImpulsaTuCarrera = new JLabel("<html><center>Impulsa tu carrera <br>Empieza hoy con Laborea</center></html>");
		lblImpulsaTuCarrera.setHorizontalAlignment(SwingConstants.CENTER);
		lblImpulsaTuCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		lblImpulsaTuCarrera.setBounds(0, 208, 1904, 107);
		contentPane.add(lblImpulsaTuCarrera);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Inicio.class.getResource("/img/Laborea.png")));
		lblLogo.setBounds(0, 0, 239, 107);
		contentPane.add(lblLogo);

		//hacer que el panel tenga bordes redondeados, usando paintcomponent
		JPanel btnLoginCandidato = new JPanel() {
			@Override
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
		btnLoginCandidato.setOpaque(false);

		btnLoginCandidato.setBackground(new Color(44, 62, 80));
		btnLoginCandidato.setBounds(551, 450, 372, 101);
		contentPane.add(btnLoginCandidato);
		btnLoginCandidato.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ingresar como candidato");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginCandidatos LC = new LoginCandidatos();
				LC.setModal(true);
				LC.setVisible(true);
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Inicio.class.getResource("/img/people.png")));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 372, 79);
		btnLoginCandidato.add(lblNewLabel);

		JPanel btnLoginEmpresa = new JPanel() {
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
		btnLoginEmpresa.setLayout(null);
		btnLoginEmpresa.setOpaque(false);
		btnLoginEmpresa.setBackground(new Color(44, 62, 80));
		btnLoginEmpresa.setBounds(981, 450, 372, 101);
		contentPane.add(btnLoginEmpresa);

		JLabel lblIngresarComoReclutador = new JLabel("Ingresar como empresa");
		lblIngresarComoReclutador.setVerticalTextPosition(SwingConstants.TOP);
		lblIngresarComoReclutador.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIngresarComoReclutador.setIcon(new ImageIcon(Inicio.class.getResource("/img/business-and-trade.png")));
		lblIngresarComoReclutador.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresarComoReclutador.setForeground(Color.WHITE);
		lblIngresarComoReclutador.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		lblIngresarComoReclutador.setBounds(0, 11, 372, 79);
		btnLoginEmpresa.add(lblIngresarComoReclutador);

		JLabel lblLaborea = new JLabel("<html>\u00A9 2025 Laborea \u2014 Desarrollado por Omar Morales y Mauricio Trejo</html>");
		lblLaborea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLaborea.setForeground(Color.WHITE);
		lblLaborea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLaborea.setBounds(0, 934, 1894, 107);
		contentPane.add(lblLaborea);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Inicio.class.getResource("/img/fondoinicio.png")));
		lblFondo.setBounds(0, 0, 1904, 1041);
		contentPane.add(lblFondo);




	}
}
