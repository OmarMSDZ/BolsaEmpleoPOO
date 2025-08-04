package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Obrero;
import logica.Persona;
import logica.Solicitud;
import logica.TecnicoSuperior;
import logica.Universitario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class VisualizarPerfilCandidato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNombrePersona;
	private static Persona persActiva;
	private JLabel lblApellidoPersona;
	private JLabel lblTelefonoPersona;
	private JLabel lblCorreoPersona;
	private JLabel lblProvinciaPersona;
	private JLabel lblMunicipioPersona;
	private JLabel lblDireccionPersona;
	private JLabel lblNivelEducativoPersona;
	private JLabel lblFechaNacimientoPersona;
	private JLabel lblCarreraPersona;
	private JLabel lblNombreTecnicoPersona;
	private JLabel lblAnosExperienciaPersona;
	private JCheckBox chkbxVentas;
	private JCheckBox chkbxMecanica;
	private JCheckBox chkbxOfimatica;
	private JCheckBox chkbxElectricidad;
	private JCheckBox chkbxSeguridad;
	private JCheckBox chkbxMantenimiento;
	private JCheckBox chkbxConduccion;
	private JCheckBox chkbxLimpieza;
	private JTabbedPane jtpNivelEducativo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		try {
			VisualizarPerfilCandidato dialog = new VisualizarPerfilCandidato();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			 
//			dialog.addWindowListener(new WindowAdapter() {
//				@Override
//				public void windowClosing(WindowEvent e) {
//					Bolsa.guardarEstado();
//				}
//			}); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarPerfilCandidato() {
		setBackground(Color.WHITE);
		setResizable(false);
		Persona actual = (Persona) Bolsa.getUsuarioActivo();

		if (actual != null) {
			persActiva = actual;
			setTitle("Laborea - Perfil de " + persActiva.getNombre() + " " + persActiva.getApellidos());
		} else {
			// Para pruebas
			persActiva = new Universitario("U-1", "Omar Jadis", "1234", "8091231234", "omarM@gmail.com", "Santiago",
					"Santiago", "Su casa", true, "Morales Diaz", "M", new Date(), "40215233418", true, false,
					"Ingeniería en Sistemas Computacionales");

			Bolsa.getInstancia().insertarUsuario(persActiva);
			Bolsa.setUsuarioActivo(persActiva); // Insertar y establecer como activo para pruebas
			setTitle("Laborea - Pruebas vista perfil");
		}
		setBounds(100, 100, 846, 952);

		SwingUtilities.updateComponentTreeUI(this); // Actualiza la ventana
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/Laborea.png")));
			lblNewLabel.setBounds(0, 0, 150, 75);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Mi Perfil");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 28));
			lblNewLabel_1.setBounds(0, 59, 844, 46);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/user.png")));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Segoe UI", Font.BOLD, 28));
			label.setBounds(0, 116, 844, 69);
			contentPanel.add(label);
		}

		JPanel pnlInfoPersonal = new JPanel() {
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

		pnlInfoPersonal.setOpaque(false);
		pnlInfoPersonal.setBorder(new LineBorder(new Color(0, 0, 0))); // borde
		pnlInfoPersonal.setBackground(Color.WHITE);
		pnlInfoPersonal.setBounds(21, 220, 790, 265);
		contentPanel.add(pnlInfoPersonal);
		pnlInfoPersonal.setLayout(null);
		{
			JLabel lblInformacinPersonal = new JLabel("Informaci\u00F3n personal");
			lblInformacinPersonal.setBounds(10, 0, 263, 46);
			pnlInfoPersonal.add(lblInformacinPersonal);
			lblInformacinPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblNombres = new JLabel("Nombre/s");
			lblNombres.setForeground(Color.DARK_GRAY);
			lblNombres.setBounds(10, 58, 189, 46);
			pnlInfoPersonal.add(lblNombres);
			lblNombres.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblApellidos = new JLabel("Apellido/s");
			lblApellidos.setForeground(Color.DARK_GRAY);
			lblApellidos.setBounds(211, 58, 189, 46);
			pnlInfoPersonal.add(lblApellidos);
			lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblTelefono = new JLabel("Tel\u00E9fono");
			lblTelefono.setForeground(Color.DARK_GRAY);
			lblTelefono.setBounds(630, 58, 189, 46);
			pnlInfoPersonal.add(lblTelefono);
			lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblCorreo = new JLabel("Correo");
			lblCorreo.setForeground(Color.DARK_GRAY);
			lblCorreo.setBounds(10, 143, 160, 46);
			pnlInfoPersonal.add(lblCorreo);
			lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblProvincia = new JLabel("Provincia");
			lblProvincia.setForeground(Color.DARK_GRAY);
			lblProvincia.setBounds(211, 146, 189, 46);
			pnlInfoPersonal.add(lblProvincia);
			lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblMunicipio = new JLabel("Municipio");
			lblMunicipio.setForeground(Color.DARK_GRAY);
			lblMunicipio.setBounds(441, 146, 189, 46);
			pnlInfoPersonal.add(lblMunicipio);
			lblMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}
		{
			JLabel lblDireccion = new JLabel("Direcci\u00F3n");
			lblDireccion.setForeground(Color.DARK_GRAY);
			lblDireccion.setBounds(630, 146, 189, 46);
			pnlInfoPersonal.add(lblDireccion);
			lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		}

		JButton btnEditarInfoPersonal = new JButton("Editar");
		btnEditarInfoPersonal.setFocusable(false);
		btnEditarInfoPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarInfoPersonalCandidato edit = new EditarInfoPersonalCandidato(0);
				edit.setModal(true);
				edit.setVisible(true);
			}
		});
		btnEditarInfoPersonal.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/edit.png")));
		btnEditarInfoPersonal.setBackground(Color.WHITE);
		btnEditarInfoPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		btnEditarInfoPersonal.setBounds(669, 7, 94, 38);
		pnlInfoPersonal.add(btnEditarInfoPersonal);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 49, 790, 9);
		pnlInfoPersonal.add(separator);
		{
			lblNombrePersona = new JLabel("Nombre/s");
			lblNombrePersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblNombrePersona.setBounds(10, 104, 189, 46);
			pnlInfoPersonal.add(lblNombrePersona);
		}
		{
			lblApellidoPersona = new JLabel("Apellido/s");
			lblApellidoPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblApellidoPersona.setBounds(211, 104, 189, 46);
			pnlInfoPersonal.add(lblApellidoPersona);
		}
		{
			lblTelefonoPersona = new JLabel("Tel\u00E9fono");
			lblTelefonoPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblTelefonoPersona.setBounds(630, 104, 189, 46);
			pnlInfoPersonal.add(lblTelefonoPersona);
		}
		{
			lblCorreoPersona = new JLabel("Correo");
			lblCorreoPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblCorreoPersona.setBounds(10, 189, 160, 46);
			pnlInfoPersonal.add(lblCorreoPersona);
		}
		{
			lblProvinciaPersona = new JLabel("Provincia");
			lblProvinciaPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblProvinciaPersona.setBounds(211, 189, 189, 46);
			pnlInfoPersonal.add(lblProvinciaPersona);
		}
		{
			lblMunicipioPersona = new JLabel("Municipio");
			lblMunicipioPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblMunicipioPersona.setBounds(441, 189, 189, 46);
			pnlInfoPersonal.add(lblMunicipioPersona);
		}
		{
			lblDireccionPersona = new JLabel("Direcci\u00F3n");
			lblDireccionPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblDireccionPersona.setBounds(630, 189, 189, 46);
			pnlInfoPersonal.add(lblDireccionPersona);
		}
		{
			JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
			lblFechaNacimiento.setForeground(Color.DARK_GRAY);
			lblFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblFechaNacimiento.setBounds(441, 58, 189, 46);
			pnlInfoPersonal.add(lblFechaNacimiento);
		}
		{
			lblFechaNacimientoPersona = new JLabel("Fecha Nacimiento");
			lblFechaNacimientoPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblFechaNacimientoPersona.setBounds(441, 104, 189, 46);
			pnlInfoPersonal.add(lblFechaNacimientoPersona);
		}
		{
			JPanel panel = new JPanel() {
				protected void paintComponent(Graphics g) {
				}
			};
			panel.setLayout(null);
			panel.setOpaque(false);
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBackground(Color.WHITE);
			panel.setBounds(21, 525, 790, 213);
			contentPanel.add(panel);
			{
				JLabel lblInformacinProfesional = new JLabel("Informaci\u00F3n Profesional");
				lblInformacinProfesional.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblInformacinProfesional.setBounds(10, 0, 263, 46);
				panel.add(lblInformacinProfesional);
			}
			{
				JLabel lblNivelEducativo = new JLabel("Nivel Educativo");
				lblNivelEducativo.setForeground(Color.DARK_GRAY);
				lblNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblNivelEducativo.setBounds(10, 58, 189, 46);
				panel.add(lblNivelEducativo);
			}
			{
				JButton btnEditarInfoProfesional = new JButton("Editar");
				btnEditarInfoProfesional.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EditarInfoPersonalCandidato edit = new EditarInfoPersonalCandidato(1);
						edit.setModal(true);
						edit.setVisible(true);
					}
				});
				btnEditarInfoProfesional.setFocusable(false);
				btnEditarInfoProfesional
						.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/edit.png")));
				btnEditarInfoProfesional.setFont(new Font("Segoe UI", Font.PLAIN, 11));
				btnEditarInfoProfesional.setBackground(Color.WHITE);
				btnEditarInfoProfesional.setBounds(669, 7, 94, 38);
				panel.add(btnEditarInfoProfesional);
			}
			{
				JSeparator separator_1 = new JSeparator();
				separator_1.setForeground(Color.BLACK);
				separator_1.setBounds(0, 49, 790, 9);
				panel.add(separator_1);
			}
			{
				lblNivelEducativoPersona = new JLabel("Nivel Educativo");
				lblNivelEducativoPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblNivelEducativoPersona.setBounds(10, 104, 138, 46);
				panel.add(lblNivelEducativoPersona);
			}

			UIManager.put("TabbedPane.focus", new Color(0, 0, 0, 0)); // Hace el borde invisible
			UIManager.put("TabbedPane.selected", UIManager.getColor("TabbedPane.background")); // Igualar color
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(168, 21, 612, 37);
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(152, 57, 21, 149);
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(774, 80, 6, 123);
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(168, 189, 612, 6);
				panel.add(panel_1);
			}
			// seleccionado

			jtpNivelEducativo = new JTabbedPane(JTabbedPane.TOP);
			jtpNivelEducativo.setFocusTraversalKeysEnabled(false);

			jtpNivelEducativo.setFocusable(false);
			jtpNivelEducativo.setEnabled(false);
			jtpNivelEducativo.setBounds(168, 21, 612, 174);
			panel.add(jtpNivelEducativo);

			JPanel pnlNivelUniversitario = new JPanel();
			pnlNivelUniversitario.setBackground(Color.WHITE);
			jtpNivelEducativo.addTab("New tab", null, pnlNivelUniversitario, null);
			pnlNivelUniversitario.setLayout(null);
			{
				JLabel lblCarrea = new JLabel("Carrera");
				lblCarrea.setForeground(Color.DARK_GRAY);
				lblCarrea.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblCarrea.setBounds(10, 11, 189, 46);
				pnlNivelUniversitario.add(lblCarrea);
			}
			{
				lblCarreraPersona = new JLabel("Carrera");
				lblCarreraPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblCarreraPersona.setBounds(10, 58, 571, 46);
				pnlNivelUniversitario.add(lblCarreraPersona);
			}

			JPanel pnlNivelTecnico = new JPanel();
			pnlNivelTecnico.setBackground(Color.WHITE);
			jtpNivelEducativo.addTab("New tab", null, pnlNivelTecnico, null);
			pnlNivelTecnico.setLayout(null);
			{
				JLabel lblTcnico = new JLabel("T\u00E9cnico");
				lblTcnico.setForeground(Color.DARK_GRAY);
				lblTcnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblTcnico.setBounds(10, 11, 138, 46);
				pnlNivelTecnico.add(lblTcnico);
			}
			{
				lblNombreTecnicoPersona = new JLabel("Nombre Tecnico");
				lblNombreTecnicoPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblNombreTecnicoPersona.setBounds(10, 65, 138, 46);
				pnlNivelTecnico.add(lblNombreTecnicoPersona);
			}
			{
				JLabel lblAosExp = new JLabel("A\u00F1os experiencia");
				lblAosExp.setForeground(Color.DARK_GRAY);
				lblAosExp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblAosExp.setBounds(181, 11, 138, 46);
				pnlNivelTecnico.add(lblAosExp);
			}
			{
				lblAnosExperienciaPersona = new JLabel("AnosExp");
				lblAnosExperienciaPersona.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				lblAnosExperienciaPersona.setBounds(181, 65, 138, 46);
				pnlNivelTecnico.add(lblAnosExperienciaPersona);
			}

			JPanel pnlNivelObrero = new JPanel();
			pnlNivelObrero.setBackground(Color.WHITE);
			jtpNivelEducativo.addTab("New tab", null, pnlNivelObrero, null);
			pnlNivelObrero.setLayout(null);

			chkbxVentas = new JCheckBox("Ventas");
			chkbxVentas.setEnabled(false);
			chkbxVentas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxVentas.setBackground(Color.WHITE);
			chkbxVentas.setBounds(10, 64, 87, 23);
			pnlNivelObrero.add(chkbxVentas);

			chkbxMecanica = new JCheckBox("Mec\u00E1nica");
			chkbxMecanica.setEnabled(false);
			chkbxMecanica.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxMecanica.setBackground(Color.WHITE);
			chkbxMecanica.setBounds(10, 93, 87, 23);
			pnlNivelObrero.add(chkbxMecanica);

			chkbxOfimatica = new JCheckBox("Ofim\u00E1tica");
			chkbxOfimatica.setEnabled(false);
			chkbxOfimatica.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxOfimatica.setBackground(Color.WHITE);
			chkbxOfimatica.setBounds(126, 64, 119, 23);
			pnlNivelObrero.add(chkbxOfimatica);

			chkbxElectricidad = new JCheckBox("Electricidad");
			chkbxElectricidad.setEnabled(false);
			chkbxElectricidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxElectricidad.setBackground(Color.WHITE);
			chkbxElectricidad.setBounds(126, 93, 119, 23);
			pnlNivelObrero.add(chkbxElectricidad);

			chkbxSeguridad = new JCheckBox("Seguridad");
			chkbxSeguridad.setEnabled(false);
			chkbxSeguridad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxSeguridad.setBackground(Color.WHITE);
			chkbxSeguridad.setBounds(259, 64, 119, 23);
			pnlNivelObrero.add(chkbxSeguridad);

			chkbxMantenimiento = new JCheckBox("Mantenimiento");
			chkbxMantenimiento.setEnabled(false);
			chkbxMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxMantenimiento.setBackground(Color.WHITE);
			chkbxMantenimiento.setBounds(259, 93, 119, 23);
			pnlNivelObrero.add(chkbxMantenimiento);

			chkbxConduccion = new JCheckBox("Conducci\u00F3n");
			chkbxConduccion.setEnabled(false);
			chkbxConduccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxConduccion.setBackground(Color.WHITE);
			chkbxConduccion.setBounds(412, 64, 106, 23);
			pnlNivelObrero.add(chkbxConduccion);

			chkbxLimpieza = new JCheckBox("Limpieza");
			chkbxLimpieza.setEnabled(false);
			chkbxLimpieza.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			chkbxLimpieza.setBackground(Color.WHITE);
			chkbxLimpieza.setBounds(412, 93, 119, 23);
			pnlNivelObrero.add(chkbxLimpieza);

			JLabel lblHabilidades = new JLabel("Habilidades");
			lblHabilidades.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			lblHabilidades.setBounds(10, 11, 138, 46);
			pnlNivelObrero.add(lblHabilidades);
		}
		{
			JButton btnDesactivarCuenta = new JButton("Desactivar Cuenta");
			btnDesactivarCuenta.setEnabled(false);
			btnDesactivarCuenta.setFocusable(false);
			btnDesactivarCuenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = JOptionPane.showInputDialog(null, "Introduzca su contraseña:", "Verificación",
							JOptionPane.PLAIN_MESSAGE);
					if (persActiva.getPasswd().equals(input)) {
						// Acción protegida
						Bolsa.getInstancia().desactivarCuenta(persActiva);
					} else {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Acción denegada",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnDesactivarCuenta.setBackground(Color.WHITE);
			btnDesactivarCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			btnDesactivarCuenta.setBounds(247, 769, 216, 40);
			contentPanel.add(btnDesactivarCuenta);

			JButton btnCambiarContraseña = new JButton("Cambiar Contrase\u00F1a");
			btnCambiarContraseña.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String actual = JOptionPane.showInputDialog(null, "Ingrese su contraseña actual:", "Verificación",
							JOptionPane.PLAIN_MESSAGE);

					if (actual == null)
						return; // Cancelado

					if (!persActiva.getPasswd().equals(actual)) {
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta. No se pudo cambiar.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					String nueva = JOptionPane.showInputDialog(null, "Ingrese su nueva contraseña:", "Nueva contraseña",
							JOptionPane.PLAIN_MESSAGE);

					if (nueva == null || nueva.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se ingresó una nueva contraseña.", "Alerta",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					// modificar datos usuario
					Bolsa.getInstancia().modificarUsuario(persActiva);

					JOptionPane.showMessageDialog(null, "Contraseña actualizada exitosamente.", "",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			btnCambiarContraseña.setFont(new Font("Segoe UI", Font.PLAIN, 16));
			btnCambiarContraseña.setFocusable(false);
			btnCambiarContraseña.setBackground(Color.WHITE);
			btnCambiarContraseña.setBounds(21, 769, 216, 40);
			contentPanel.add(btnCambiarContraseña);

			JLabel btnRegresar = new JLabel("");
			btnRegresar.setHorizontalAlignment(SwingConstants.CENTER);
			btnRegresar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				 
			 
						dispose();
					 
				}
			});
			btnRegresar.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/flechaRegresar.png")));
			btnRegresar.setBounds(738, 0, 90, 91);
			contentPanel.add(btnRegresar);
			if (persActiva != null) {
				btnDesactivarCuenta.setEnabled(true); // solo activar si hay persona activa en esta vista
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Recargar");
				okButton.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/refresh.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cargarInfo();
					}
				});
				okButton.setFocusable(false);
				okButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				okButton.setBackground(Color.WHITE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.setIcon(new ImageIcon(VisualizarPerfilCandidato.class.getResource("/img/cancelar16px.png")));
				cancelButton.setFocusable(false);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				 
 
							dispose();
						 
					}
				});
				cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				cancelButton.setBackground(Color.WHITE);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		cargarInfo();
	}

	// cargar informacion de persona en la vista
	private void cargarInfo() {
		if (persActiva != null) {
			// informacion personal
			lblNombrePersona.setText(persActiva.getNombre());
			lblApellidoPersona.setText(persActiva.getApellidos());
			lblTelefonoPersona.setText(persActiva.getTelefono());
			lblCorreoPersona.setText(persActiva.getCorreoElectronico());
			lblProvinciaPersona.setText(persActiva.getProvincia());
			lblMunicipioPersona.setText(persActiva.getMunicipio());
			lblDireccionPersona.setText(persActiva.getDireccion());
			Date fechaSolicitud = persActiva.getFechaNacimiento();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = dateFormat.format(fechaSolicitud);
			lblFechaNacimientoPersona.setText(formattedDate);
			// informacion profesional
			if (persActiva instanceof Universitario) {
				jtpNivelEducativo.setSelectedIndex(0);
				lblNivelEducativoPersona.setText("Universitario");
				lblCarreraPersona.setText(((Universitario) persActiva).getCarrera());
			} else if (persActiva instanceof TecnicoSuperior) {
				jtpNivelEducativo.setSelectedIndex(1);
				lblNivelEducativoPersona.setText("Técnico Superior");
				lblNombreTecnicoPersona.setText(((TecnicoSuperior) persActiva).getTecnico());
				lblAnosExperienciaPersona.setText(String.valueOf(((TecnicoSuperior) persActiva).getAniosExperiencia()));
			} else if (persActiva instanceof Obrero) {
				jtpNivelEducativo.setSelectedIndex(2);
				lblNivelEducativoPersona.setText("Obrero");
				if (((Obrero) persActiva).isVentas()) {
					chkbxVentas.setSelected(true);
				}
				if (((Obrero) persActiva).isSeguridad()) {
					chkbxSeguridad.setSelected(true);
				}
				if (((Obrero) persActiva).isConduccion()) {
					chkbxConduccion.setSelected(true);
				}
				if (((Obrero) persActiva).isLimpieza()) {
					chkbxLimpieza.setSelected(true);
				}
				if (((Obrero) persActiva).isOfimatica()) {
					chkbxOfimatica.setSelected(true);
				}
				if (((Obrero) persActiva).isMantenimiento()) {
					chkbxMantenimiento.setSelected(true);
				}
				if (((Obrero) persActiva).isMecanica()) {
					chkbxMecanica.setSelected(true);
				}
				if (((Obrero) persActiva).isElectricidad()) {
					chkbxElectricidad.setSelected(true);
				}
			}

		}
	}
}