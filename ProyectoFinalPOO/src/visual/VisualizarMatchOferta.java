package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Bolsa;
import logica.MatchOferta;
import logica.Obrero;
import logica.Oferta;
import logica.Persona;
import logica.Solicitud;
import logica.TecnicoSuperior;
import logica.Universitario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class VisualizarMatchOferta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel tableModel;
	private static Object[] fila;
	private static MatchOferta selected = null;
	private static Oferta ofertaActual;
	private JTabbedPane jtpVisualizar;
	private JTable tblMatches;
	private JLabel lblMostrarNombreCompleto;
	private JLabel lblMostrarCedula;
	private JLabel lblMostrarSexo;
	private JLabel lblMostrarFechaNacimiento;
	private JLabel lblMostrarTelefono;
	private JLabel lblMostrarCorreo;
	private JLabel lblMostrarProvincia;
	private JLabel lblMostrarMunicipio;
	private JLabel lblMostrarEstado;
	private JLabel lblMostrarNivelEducativo;
	private JTabbedPane jtpNivelEducativo;
	private JLabel lblMostrarTecnico;
	private JLabel lblMostrarCarrera;
	private JLabel lblMostrarAniosExp;
	private JLabel lblLimpieza;
	private JLabel lblConduccion;
	private JLabel lblElectricidad;
	private JLabel lblSeguridad;
	private JLabel lblOfimatica;
	private JLabel lblMantenimiento;
	private JLabel lblMecanica;
	private JLabel lblVentas;
	private JLabel lblMostrarLicencia;
	private JLabel lblMostrarMovilidad;
	private JLabel lblMostrarDisphorario;
	private JLabel lblMostrarTipoTrabajo;
	private JLabel lblMostrarModalidad;
	private JLabel lblMostrarSalarioDeseado;
	private JLabel lblMostrarFechaSolicitud;
	private JLabel lblMostrarDireccion;
	private JButton btnVisualizar;
	private JButton btnCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarMatchOferta dialog = new VisualizarMatchOferta(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarMatchOferta(Oferta oferta) {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		if (oferta != null) {
			setTitle("Laborea - Mejores coincidencias para la oferta: " + oferta.getPuestoTrab());
			ofertaActual = oferta;
		} else {
			setTitle("Laborea - Prueba ");
			ofertaActual = null;
		}
		setTitle("Laborea - Visualizar informaci\u00F3n del matcheo");
		setResizable(false);
		setBounds(100, 100, 1020, 855);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1014, 756);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		jtpVisualizar = new JTabbedPane(JTabbedPane.TOP);
		jtpVisualizar.setBounds(-11, -29, 1041, 795);
		contentPanel.add(jtpVisualizar);

		JPanel pnlVisualizarMatches = new JPanel();
		pnlVisualizarMatches.setBackground(Color.WHITE);
		jtpVisualizar.addTab("New tab", null, pnlVisualizarMatches, null);
		pnlVisualizarMatches.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scrollPane.setBounds(30, 77, 963, 673);
		pnlVisualizarMatches.add(scrollPane);

		tblMatches = new JTable();
		tblMatches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// al hacer clic en la tabla
				int index = tblMatches.getSelectedRow();
				if (index >= 0) {
					selected = Bolsa.getInstancia()
							.buscarMatchOfertaByCodigo(tblMatches.getValueAt(index, 0).toString());
					btnVisualizar.setEnabled(true);
					cargarDatosCandSol(selected);
				} else {
					btnVisualizar.setEnabled(false);
				}
			}
		});
		tblMatches.setBackground(Color.WHITE);
		tblMatches.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		// cargar headers de tabla y datos
		String[] headers = { "Código", "Candidato", "Provincia", "Solicitud N°", "Fecha" };
		tableModel = new DefaultTableModel(null, headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Nunca permitir edición
				return false;
			}
		};

		tblMatches.setModel(tableModel);

		// cargar datos
		mostrarMatches();

		scrollPane.setViewportView(tblMatches);

		JLabel label = new JLabel("Mejores coincidencias para esta oferta");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		label.setBounds(30, 13, 431, 35);
		pnlVisualizarMatches.add(label);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(30, 54, 963, 10);
		pnlVisualizarMatches.add(separator_2);

		JPanel pnlVisualizarCandidato = new JPanel();
		pnlVisualizarCandidato.setBackground(Color.WHITE);
		jtpVisualizar.addTab("New tab", null, pnlVisualizarCandidato, null);
		pnlVisualizarCandidato.setLayout(null);

		JPanel pnlOcultoLateralIzquierdo = new JPanel();
		pnlOcultoLateralIzquierdo.setBackground(Color.WHITE);
		pnlOcultoLateralIzquierdo.setBounds(10, 505, 13, 184);
		pnlVisualizarCandidato.add(pnlOcultoLateralIzquierdo);

		JPanel pnlOcultoInferior = new JPanel();
		pnlOcultoInferior.setBackground(Color.WHITE);
		pnlOcultoInferior.setBounds(20, 679, 404, 10);
		pnlVisualizarCandidato.add(pnlOcultoInferior);

		JPanel pnlOcultoLateralDerecho = new JPanel();
		pnlOcultoLateralDerecho.setBackground(Color.WHITE);
		pnlOcultoLateralDerecho.setBounds(420, 502, 10, 187);
		pnlVisualizarCandidato.add(pnlOcultoLateralDerecho);

		JPanel pnlOcultoSuperior = new JPanel();
		pnlOcultoSuperior.setBackground(Color.WHITE);
		pnlOcultoSuperior.setBounds(20, 502, 404, 28);
		pnlVisualizarCandidato.add(pnlOcultoSuperior);

		JLabel lblNewLabel = new JLabel("Datos candidato");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel.setBounds(31, 26, 238, 34);
		pnlVisualizarCandidato.add(lblNewLabel);

		JLabel label_1 = new JLabel("Nombre completo:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_1.setBounds(20, 75, 166, 41);
		pnlVisualizarCandidato.add(label_1);

		lblMostrarNombreCompleto = new JLabel("Nombre Completo");
		lblMostrarNombreCompleto.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNombreCompleto.setForeground(Color.DARK_GRAY);
		lblMostrarNombreCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarNombreCompleto.setBounds(196, 75, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarNombreCompleto);

		JLabel label_3 = new JLabel("Cédula:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_3.setBounds(20, 113, 166, 41);
		pnlVisualizarCandidato.add(label_3);

		lblMostrarCedula = new JLabel("Cedula");
		lblMostrarCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCedula.setForeground(Color.DARK_GRAY);
		lblMostrarCedula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCedula.setBounds(196, 113, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarCedula);

		JLabel label_5 = new JLabel("Sexo:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_5.setBounds(20, 154, 148, 41);
		pnlVisualizarCandidato.add(label_5);

		lblMostrarSexo = new JLabel("Sexo");
		lblMostrarSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSexo.setForeground(Color.DARK_GRAY);
		lblMostrarSexo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarSexo.setBounds(196, 154, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarSexo);

		JLabel label_7 = new JLabel("Fecha nacimiento:");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_7.setBounds(20, 229, 166, 41);
		pnlVisualizarCandidato.add(label_7);

		lblMostrarFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblMostrarFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarFechaNacimiento.setForeground(Color.DARK_GRAY);
		lblMostrarFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarFechaNacimiento.setBounds(196, 229, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarFechaNacimiento);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTelefono.setBounds(20, 264, 166, 41);
		pnlVisualizarCandidato.add(lblTelefono);

		lblMostrarTelefono = new JLabel("telefono");
		lblMostrarTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTelefono.setForeground(Color.DARK_GRAY);
		lblMostrarTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTelefono.setBounds(196, 264, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCorreo.setBounds(20, 305, 166, 41);
		pnlVisualizarCandidato.add(lblCorreo);

		lblMostrarCorreo = new JLabel("Correo");
		lblMostrarCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCorreo.setForeground(Color.DARK_GRAY);
		lblMostrarCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCorreo.setBounds(196, 305, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarCorreo);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblProvincia.setBounds(20, 342, 166, 41);
		pnlVisualizarCandidato.add(lblProvincia);

		lblMostrarProvincia = new JLabel("provincia");
		lblMostrarProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarProvincia.setForeground(Color.DARK_GRAY);
		lblMostrarProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarProvincia.setBounds(196, 342, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarProvincia);

		lblMostrarMunicipio = new JLabel("municipio");
		lblMostrarMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMunicipio.setForeground(Color.DARK_GRAY);
		lblMostrarMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarMunicipio.setBounds(196, 378, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarMunicipio);

		JLabel lblMunicipio_1 = new JLabel("Municipio:");
		lblMunicipio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMunicipio_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMunicipio_1.setBounds(20, 378, 166, 41);
		pnlVisualizarCandidato.add(lblMunicipio_1);

		JLabel lblEst = new JLabel("Estado:");
		lblEst.setHorizontalAlignment(SwingConstants.LEFT);
		lblEst.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEst.setBounds(20, 700, 71, 41);
		pnlVisualizarCandidato.add(lblEst);

		lblMostrarEstado = new JLabel("Estado");
		lblMostrarEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarEstado.setForeground(Color.DARK_GRAY);
		lblMostrarEstado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarEstado.setBounds(196, 700, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarEstado);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 69, 992, 17);
		pnlVisualizarCandidato.add(separator);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpVisualizar.setSelectedIndex(0);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(VisualizarMatchOferta.class.getResource("/img/close2.png")));
		lblNewLabel_1.setBounds(915, 13, 77, 67);
		pnlVisualizarCandidato.add(lblNewLabel_1);

		JButton btnContratar = new JButton("Contratar");
		btnContratar.setBackground(Color.WHITE);
		btnContratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					if (!selected.getSolicitudMatcheo().getSolicitante().isEstadoEmpleado()) {
						Bolsa.getInstancia().contratarPersona(selected.getSolicitudMatcheo().getSolicitante(),
								selected.getSolicitudMatcheo());
						Date fechaContratacion = new Date();
						selected.setFechaContratacion(fechaContratacion);
						selected.setAceptacionEmpresa(true);
						selected.getOfertaMatcheo().disminuirVacantesDisp();
						JOptionPane.showMessageDialog(null, "Contratación efectuada exitosamente.", "Información",
								JOptionPane.INFORMATION_MESSAGE, null);
					} else {
						JOptionPane.showMessageDialog(null,
								"Lo sentimos, pero este candidato no se encuentra disponile.", "Información",
								JOptionPane.INFORMATION_MESSAGE, null);
					}
				} else if (selected == null) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un matcheo antes de ejecutar esta acción.",
							"Información", JOptionPane.INFORMATION_MESSAGE, null);
				} else {
					JOptionPane.showMessageDialog(null, "No es posible contratar al candidato seleccionado.",
							"Advertencia", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnContratar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnContratar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContratar.setIcon(new ImageIcon(VisualizarMatchOferta.class.getResource("/img/add-user.png")));
		btnContratar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnContratar.setBounds(773, 667, 219, 73);
		pnlVisualizarCandidato.add(btnContratar);

		JLabel lblNivelEducativo = new JLabel("Nivel educativo:");
		lblNivelEducativo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNivelEducativo.setBounds(20, 453, 166, 51);
		pnlVisualizarCandidato.add(lblNivelEducativo);

		lblMostrarNivelEducativo = new JLabel("nivelEducativo");
		lblMostrarNivelEducativo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNivelEducativo.setForeground(Color.DARK_GRAY);
		lblMostrarNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarNivelEducativo.setBounds(196, 453, 260, 51);
		pnlVisualizarCandidato.add(lblMostrarNivelEducativo);

		jtpNivelEducativo = new JTabbedPane(JTabbedPane.TOP);
		jtpNivelEducativo.setEnabled(false);
		jtpNivelEducativo.setBounds(20, 505, 404, 179);
		pnlVisualizarCandidato.add(jtpNivelEducativo);

		JPanel pnlUniversitario = new JPanel();
		pnlUniversitario.setBackground(Color.WHITE);
		jtpNivelEducativo.addTab("New tab", null, pnlUniversitario, null);
		pnlUniversitario.setLayout(null);

		JLabel lblCarrear = new JLabel("Carrera:");
		lblCarrear.setHorizontalAlignment(SwingConstants.LEFT);
		lblCarrear.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCarrear.setBounds(0, 0, 66, 51);
		pnlUniversitario.add(lblCarrear);

		lblMostrarCarrera = new JLabel("Carrera");
		lblMostrarCarrera.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCarrera.setForeground(Color.DARK_GRAY);
		lblMostrarCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCarrera.setBounds(72, 0, 290, 51);
		pnlUniversitario.add(lblMostrarCarrera);

		JPanel pnlTecnicoSuperior = new JPanel();
		pnlTecnicoSuperior.setBackground(Color.WHITE);
		jtpNivelEducativo.addTab("New tab", null, pnlTecnicoSuperior, null);
		pnlTecnicoSuperior.setLayout(null);

		JLabel lblTecnico = new JLabel("Tecnico:");
		lblTecnico.setHorizontalAlignment(SwingConstants.LEFT);
		lblTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTecnico.setBounds(0, 0, 66, 51);
		pnlTecnicoSuperior.add(lblTecnico);

		lblMostrarTecnico = new JLabel("Tecnico");
		lblMostrarTecnico.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTecnico.setForeground(Color.DARK_GRAY);
		lblMostrarTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTecnico.setBounds(165, 0, 203, 51);
		pnlTecnicoSuperior.add(lblMostrarTecnico);

		JLabel lblAosExperiencia = new JLabel("A\u00F1os experiencia:");
		lblAosExperiencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblAosExperiencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAosExperiencia.setBounds(0, 45, 160, 51);
		pnlTecnicoSuperior.add(lblAosExperiencia);

		lblMostrarAniosExp = new JLabel("A\u00F1os experiencia");
		lblMostrarAniosExp.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarAniosExp.setForeground(Color.DARK_GRAY);
		lblMostrarAniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarAniosExp.setBounds(165, 45, 203, 51);
		pnlTecnicoSuperior.add(lblMostrarAniosExp);

		JPanel pnlObrero = new JPanel();
		pnlObrero.setBackground(Color.WHITE);
		jtpNivelEducativo.addTab("New tab", null, pnlObrero, null);
		pnlObrero.setLayout(null);

		JLabel lblHabilidades = new JLabel("Habilidades:");
		lblHabilidades.setHorizontalAlignment(SwingConstants.LEFT);
		lblHabilidades.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblHabilidades.setBounds(0, 0, 147, 51);
		pnlObrero.add(lblHabilidades);

		lblVentas = new JLabel("Ventas");
		lblVentas.setEnabled(false);
		lblVentas.setHorizontalAlignment(SwingConstants.LEFT);
		lblVentas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVentas.setBounds(0, 48, 76, 33);
		pnlObrero.add(lblVentas);

		lblMecanica = new JLabel("Mec\u00E1nica");
		lblMecanica.setEnabled(false);
		lblMecanica.setHorizontalAlignment(SwingConstants.LEFT);
		lblMecanica.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMecanica.setBounds(0, 92, 76, 33);
		pnlObrero.add(lblMecanica);

		lblOfimatica = new JLabel("Ofim\u00E1tica");
		lblOfimatica.setEnabled(false);
		lblOfimatica.setHorizontalAlignment(SwingConstants.LEFT);
		lblOfimatica.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblOfimatica.setBounds(77, 48, 113, 33);
		pnlObrero.add(lblOfimatica);

		lblMantenimiento = new JLabel("Mantenimiento");
		lblMantenimiento.setEnabled(false);
		lblMantenimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblMantenimiento.setBounds(77, 92, 113, 33);
		pnlObrero.add(lblMantenimiento);

		lblElectricidad = new JLabel("Electricidad");
		lblElectricidad.setEnabled(false);
		lblElectricidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblElectricidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblElectricidad.setBounds(193, 48, 91, 33);
		pnlObrero.add(lblElectricidad);

		lblSeguridad = new JLabel("Seguridad");
		lblSeguridad.setEnabled(false);
		lblSeguridad.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeguridad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSeguridad.setBounds(193, 92, 91, 33);
		pnlObrero.add(lblSeguridad);

		lblConduccion = new JLabel("Conduccion");
		lblConduccion.setEnabled(false);
		lblConduccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblConduccion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblConduccion.setBounds(284, 48, 91, 33);
		pnlObrero.add(lblConduccion);

		lblLimpieza = new JLabel("Limpieza");
		lblLimpieza.setEnabled(false);
		lblLimpieza.setHorizontalAlignment(SwingConstants.LEFT);
		lblLimpieza.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblLimpieza.setBounds(284, 92, 91, 33);
		pnlObrero.add(lblLimpieza);

		JLabel lblDatosSolicitud = new JLabel("Datos Solicitud");
		lblDatosSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblDatosSolicitud.setBounds(488, 26, 238, 34);
		pnlVisualizarCandidato.add(lblDatosSolicitud);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(466, 69, 13, 717);
		pnlVisualizarCandidato.add(separator_1);

		JLabel lblDisponibilidadDeHorario = new JLabel("Disp. de horario:");
		lblDisponibilidadDeHorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisponibilidadDeHorario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDisponibilidadDeHorario.setBounds(477, 75, 147, 41);
		pnlVisualizarCandidato.add(lblDisponibilidadDeHorario);

		lblMostrarDisphorario = new JLabel("DispHorario");
		lblMostrarDisphorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDisphorario.setForeground(Color.DARK_GRAY);
		lblMostrarDisphorario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDisphorario.setBounds(657, 75, 309, 41);
		pnlVisualizarCandidato.add(lblMostrarDisphorario);

		lblMostrarMovilidad = new JLabel("Movilidad");
		lblMostrarMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMovilidad.setForeground(Color.DARK_GRAY);
		lblMostrarMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarMovilidad.setBounds(658, 128, 309, 41);
		pnlVisualizarCandidato.add(lblMostrarMovilidad);

		JLabel lblMovilidad = new JLabel("Movilidad:");
		lblMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMovilidad.setBounds(478, 128, 147, 41);
		pnlVisualizarCandidato.add(lblMovilidad);

		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLicencia.setBounds(20, 189, 148, 41);
		pnlVisualizarCandidato.add(lblLicencia);

		lblMostrarLicencia = new JLabel("Licencia");
		lblMostrarLicencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarLicencia.setForeground(Color.DARK_GRAY);
		lblMostrarLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarLicencia.setBounds(196, 189, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarLicencia);

		JLabel lblTipoDeTrabajo = new JLabel("Tipo de trabajo:");
		lblTipoDeTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDeTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTipoDeTrabajo.setBounds(477, 180, 148, 41);
		pnlVisualizarCandidato.add(lblTipoDeTrabajo);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblModalidad.setBounds(477, 229, 148, 41);
		pnlVisualizarCandidato.add(lblModalidad);

		JLabel lblSalarioDeseado = new JLabel("Salario deseado:");
		lblSalarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSalarioDeseado.setBounds(477, 281, 148, 41);
		pnlVisualizarCandidato.add(lblSalarioDeseado);

		JLabel lblFechaSolicitud = new JLabel("Fecha solicitud:");
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFechaSolicitud.setBounds(477, 333, 148, 41);
		pnlVisualizarCandidato.add(lblFechaSolicitud);

		lblMostrarTipoTrabajo = new JLabel("tipoTrabajo");
		lblMostrarTipoTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTipoTrabajo.setForeground(Color.DARK_GRAY);
		lblMostrarTipoTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTipoTrabajo.setBounds(657, 180, 309, 41);
		pnlVisualizarCandidato.add(lblMostrarTipoTrabajo);

		lblMostrarModalidad = new JLabel("Modalidad");
		lblMostrarModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarModalidad.setForeground(Color.DARK_GRAY);
		lblMostrarModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarModalidad.setBounds(657, 229, 309, 41);
		pnlVisualizarCandidato.add(lblMostrarModalidad);

		lblMostrarSalarioDeseado = new JLabel("SalarioDeseado");
		lblMostrarSalarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSalarioDeseado.setForeground(Color.DARK_GRAY);
		lblMostrarSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarSalarioDeseado.setBounds(657, 281, 309, 41);
		pnlVisualizarCandidato.add(lblMostrarSalarioDeseado);

		lblMostrarFechaSolicitud = new JLabel("FechaSolicitud");
		lblMostrarFechaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarFechaSolicitud.setForeground(Color.DARK_GRAY);
		lblMostrarFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarFechaSolicitud.setBounds(657, 333, 309, 41);
		pnlVisualizarCandidato.add(lblMostrarFechaSolicitud);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDireccin.setBounds(20, 418, 166, 41);
		pnlVisualizarCandidato.add(lblDireccin);

		lblMostrarDireccion = new JLabel("direccion");
		lblMostrarDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDireccion.setForeground(Color.DARK_GRAY);
		lblMostrarDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDireccion.setBounds(196, 418, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarDireccion);

		JPanel buttomPane = new JPanel();
		buttomPane.setLayout(null);
		buttomPane.setBackground(Color.WHITE);
		buttomPane.setBounds(0, 757, 1014, 63);
		getContentPane().add(buttomPane);

		btnVisualizar = new JButton("Visualizar candidato");
		btnVisualizar.setIcon(new ImageIcon(VisualizarMatchOferta.class.getResource("/img/user2.png")));
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selected != null) {
					cargarDatosCandSol(selected);
					jtpVisualizar.setSelectedIndex(1);
					btnCerrar.setText("Regresar");
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un candidato antes.", "Información",
							JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		btnVisualizar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVisualizar.setBackground(Color.WHITE);
		btnVisualizar.setActionCommand("OK");
		btnVisualizar.setBounds(622, 16, 231, 31);
		buttomPane.add(btnVisualizar);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(new ImageIcon(VisualizarMatchOferta.class.getResource("/img/cancelar16px.png")));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnCerrar.getText().equalsIgnoreCase("CERRAR")) {
					int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar esta pestaña?",
							"Confirmación", JOptionPane.YES_NO_OPTION);
					if (opcion == JOptionPane.YES_OPTION) {
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Acción cancelada", "Información",
								JOptionPane.INFORMATION_MESSAGE, null);
					}
				} else {
					btnCerrar.setText("Cerrar");
					selected = null;
					jtpVisualizar.setSelectedIndex(0);
				}

			}
		});
		btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setActionCommand("Cancel");
		btnCerrar.setBounds(865, 16, 124, 31);
		buttomPane.add(btnCerrar);

	}

	private void mostrarMatches() {
		ArrayList<MatchOferta> listaMatcheo = Bolsa.getInstancia().getListaMatchOferta();
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		for (MatchOferta aux : listaMatcheo) {
			if (aux.getOfertaMatcheo().equals(ofertaActual)) {
				if (!aux.getSolicitudMatcheo().getSolicitante().isEstadoEmpleado()) {

					fila[0] = aux.getCodigo();
					fila[1] = aux.getSolicitudMatcheo().getSolicitante().getNombre() + " "
							+ aux.getSolicitudMatcheo().getSolicitante().getApellidos();
					fila[2] = aux.getSolicitudMatcheo().getSolicitante().getProvincia();
					fila[3] = aux.getSolicitudMatcheo().getCodigo();
					Date fechaMatch = aux.getFechaMatcheo();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String formattedDate = dateFormat.format(fechaMatch);
					fila[4] = formattedDate;

				}

				tableModel.addRow(fila);
			}
		}
	}

	// cargar datos en vista previa
	private void cargarDatosCandSol(MatchOferta match) {
		Persona aux = match.getSolicitudMatcheo().getSolicitante();
		Solicitud auxSoli = match.getSolicitudMatcheo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		// datos de candidato
		if (aux != null) {
			lblMostrarNombreCompleto.setText(aux.getNombre() + " " + aux.getApellidos());
			lblMostrarCedula.setText(aux.getCedula());
			lblMostrarTelefono.setText(aux.getTelefono());
			Date fechaNacimiento = aux.getFechaNacimiento();

			String fechaNacFormatted = dateFormat.format(fechaNacimiento);
			lblMostrarFechaNacimiento.setText(fechaNacFormatted);
			if (aux.getSexo().equalsIgnoreCase("m")) {
				lblMostrarSexo.setText("Masculino");
			} else {
				lblMostrarSexo.setText("Femenino");
			}
			if (aux.isLicenciaConducir()) {
				lblMostrarLicencia.setText("Posee");
			} else {
				lblMostrarLicencia.setText("No posee");
			}
			lblMostrarCorreo.setText(aux.getCorreoElectronico());
			lblMostrarProvincia.setText(aux.getProvincia());
			lblMostrarMunicipio.setText(aux.getMunicipio());
			lblMostrarDireccion.setText(aux.getDireccion());
			if (!aux.isEstadoEmpleado()) {
				lblMostrarEstado.setText("Desempleado");
			} else {
				lblMostrarEstado.setText("Empleado");
			}

			if (aux instanceof Universitario) {
				lblMostrarNivelEducativo.setText("Universitario");
				jtpNivelEducativo.setSelectedIndex(0);
				lblMostrarCarrera.setText(((Universitario) aux).getCarrera());
			} else if (aux instanceof TecnicoSuperior) {
				lblMostrarNivelEducativo.setText("Técnico");
				jtpNivelEducativo.setSelectedIndex(1);
				lblMostrarTecnico.setText(((TecnicoSuperior) aux).getTecnico());
				lblMostrarAniosExp.setText(String.valueOf(((TecnicoSuperior) aux).getAniosExperiencia()));
			} else if (aux instanceof Obrero) {
				lblMostrarNivelEducativo.setText("Obrero");
				jtpNivelEducativo.setSelectedIndex(2);
				lblVentas.setEnabled(((Obrero) aux).isVentas());
				lblMecanica.setEnabled(((Obrero) aux).isMecanica());
				lblOfimatica.setEnabled(((Obrero) aux).isOfimatica());
				lblMantenimiento.setEnabled(((Obrero) aux).isMantenimiento());
				lblElectricidad.setEnabled(((Obrero) aux).isElectricidad());
				lblSeguridad.setEnabled(((Obrero) aux).isSeguridad());
				lblConduccion.setEnabled(((Obrero) aux).isConduccion());
				lblLimpieza.setEnabled(((Obrero) aux).isLimpieza());
			}
		}
		// datos de solicitud
		if (auxSoli != null) {
			lblMostrarDisphorario.setText(auxSoli.getDispHorarios());
			if (auxSoli.isDispMovilidad()) {
				lblMostrarMovilidad.setText("Dispuesto/a a movilizarse");
			} else {
				lblMostrarMovilidad.setText("N/A");
			}
			lblMostrarTipoTrabajo.setText(auxSoli.getTipoEmpleo());
			lblMostrarModalidad.setText(auxSoli.getModalidad());
			lblMostrarSalarioDeseado.setText(String.valueOf(auxSoli.getSalarioDeseado()));
			Date fechaSolicitud = auxSoli.getFechaSolicitud();
			String fechaSoliFormatted = dateFormat.format(fechaSolicitud);
			lblMostrarFechaSolicitud.setText(fechaSoliFormatted);
		}
	}
}
