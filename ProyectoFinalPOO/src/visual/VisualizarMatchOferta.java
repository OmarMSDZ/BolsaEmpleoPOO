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
	private JButton btnVisualizarCandidato;
	private JLabel lblMostrarNombreCompleto;
	private JLabel lblMostrarCedula;
	private JLabel lblMostrarSexo;
	private JLabel lblMostrarFechaNacimiento;
	private JLabel lblMostrarTelefono;
	private JLabel lblMostrarCorreo;
	private JLabel lblMostrarProvincia;
	private JLabel lblMostrarMunicipio;
	private JLabel lblMostrarEstado;
	private JLabel lblMostrarDisponibilidad;
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
		if(oferta!=null) {
			setTitle("Laborea - Mejores coincidencias para la oferta: "+oferta.getPuestoTrab());
			ofertaActual = oferta;
		} else {
			setTitle("Laborea - Prueba ");
			ofertaActual = null;
		}
		setTitle("Laborea");
		setResizable(false);
		setBounds(100, 100, 1003, 855);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		
		jtpVisualizar = new JTabbedPane(JTabbedPane.TOP);
		jtpVisualizar.setBounds(0, -23, 1007, 814);
		contentPanel.add(jtpVisualizar);
		
		JPanel pnlVisualizarMatches = new JPanel();
		pnlVisualizarMatches.setBackground(Color.WHITE);
		jtpVisualizar.addTab("New tab", null, pnlVisualizarMatches, null);
		pnlVisualizarMatches.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 969, 450);
		pnlVisualizarMatches.add(scrollPane);
		
		tblMatches = new JTable();
		tblMatches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//al hacer clic en la tabla
				int index = tblMatches.getSelectedRow();
				if(index>=0) {
					selected = Bolsa.getInstancia().buscarMatchOfertaByCodigo(tblMatches.getValueAt(index, 0).toString());
					btnVisualizarCandidato.setEnabled(true);
					cargarDatosCand(selected);
				} else {
					btnVisualizarCandidato.setEnabled(false);
				}
			}
		});
		tblMatches.setBackground(Color.WHITE);
		tblMatches.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		//cargar headers de tabla y datos
		String [] headers = {"Cod", "Candidato", "Provincia", "Solicitud N°", "Fecha", "Estado aceptación"};
		tableModel = new DefaultTableModel(null, headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Nunca permitir edición
				return false;
			}
		};

		tblMatches.setModel(tableModel);
		
		//cargar datos
		mostrarMatches();
		
		scrollPane.setViewportView(tblMatches);
		
		JLabel label = new JLabel("Mejores coincidencias para esta oferta");
		label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		label.setBounds(10, 26, 342, 35);
		pnlVisualizarMatches.add(label);
		
		JPanel pnlVisualizarCandidato = new JPanel();
		pnlVisualizarCandidato.setBackground(Color.WHITE);
		jtpVisualizar.addTab("New tab", null, pnlVisualizarCandidato, null);
		pnlVisualizarCandidato.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Datos candidato");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 24, 238, 34);
		pnlVisualizarCandidato.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("Nombre completo:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_1.setBounds(20, 90, 166, 41);
		pnlVisualizarCandidato.add(label_1);
		
		lblMostrarNombreCompleto = new JLabel("Nombre Completo");
		lblMostrarNombreCompleto.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNombreCompleto.setForeground(Color.DARK_GRAY);
		lblMostrarNombreCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarNombreCompleto.setBounds(196, 90, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarNombreCompleto);
		
		JLabel label_3 = new JLabel("Cédula:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_3.setBounds(20, 128, 166, 41);
		pnlVisualizarCandidato.add(label_3);
		
		lblMostrarCedula = new JLabel("Cedula");
		lblMostrarCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCedula.setForeground(Color.DARK_GRAY);
		lblMostrarCedula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCedula.setBounds(196, 128, 196, 41);
		pnlVisualizarCandidato.add(lblMostrarCedula);
		
		JLabel label_5 = new JLabel("Sexo:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_5.setBounds(20, 169, 148, 41);
		pnlVisualizarCandidato.add(label_5);
		
		lblMostrarSexo = new JLabel("Sexo");
		lblMostrarSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSexo.setForeground(Color.DARK_GRAY);
		lblMostrarSexo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarSexo.setBounds(196, 169, 123, 41);
		pnlVisualizarCandidato.add(lblMostrarSexo);
		
		JLabel label_7 = new JLabel("Fecha nacimiento:");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_7.setBounds(20, 207, 166, 41);
		pnlVisualizarCandidato.add(label_7);
		
		lblMostrarFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblMostrarFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarFechaNacimiento.setForeground(Color.DARK_GRAY);
		lblMostrarFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarFechaNacimiento.setBounds(196, 207, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarFechaNacimiento);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTelefono.setBounds(20, 242, 166, 41);
		pnlVisualizarCandidato.add(lblTelefono);
		
		lblMostrarTelefono = new JLabel("telefono");
		lblMostrarTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTelefono.setForeground(Color.DARK_GRAY);
		lblMostrarTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTelefono.setBounds(196, 242, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarTelefono);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCorreo.setBounds(20, 282, 166, 41);
		pnlVisualizarCandidato.add(lblCorreo);
		
		lblMostrarCorreo = new JLabel("Correo");
		lblMostrarCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCorreo.setForeground(Color.DARK_GRAY);
		lblMostrarCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCorreo.setBounds(196, 282, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarCorreo);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblProvincia.setBounds(20, 326, 166, 41);
		pnlVisualizarCandidato.add(lblProvincia);
		
		lblMostrarProvincia = new JLabel("provincia");
		lblMostrarProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarProvincia.setForeground(Color.DARK_GRAY);
		lblMostrarProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarProvincia.setBounds(196, 326, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarProvincia);
		
		lblMostrarMunicipio = new JLabel("municipio");
		lblMostrarMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMunicipio.setForeground(Color.DARK_GRAY);
		lblMostrarMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarMunicipio.setBounds(196, 369, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarMunicipio);
		
		JLabel lblMunicipio_1 = new JLabel("Municipio:");
		lblMunicipio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMunicipio_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMunicipio_1.setBounds(20, 369, 166, 41);
		pnlVisualizarCandidato.add(lblMunicipio_1);
		
		JLabel lblEst = new JLabel("Estado:");
		lblEst.setHorizontalAlignment(SwingConstants.LEFT);
		lblEst.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEst.setBounds(20, 682, 166, 41);
		pnlVisualizarCandidato.add(lblEst);
		
		lblMostrarEstado = new JLabel("Estado");
		lblMostrarEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarEstado.setForeground(Color.DARK_GRAY);
		lblMostrarEstado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarEstado.setBounds(196, 682, 252, 41);
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
		lblNewLabel_1.setBounds(904, 11, 77, 67);
		pnlVisualizarCandidato.add(lblNewLabel_1);
		
		JButton btnContratar = new JButton("Contratar");
		btnContratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//contratar candidato seleccionado (Solo si este ha aceptado)
				if(selected!=null && selected.isAceptacionCandidato()) {
					Bolsa.getInstancia().contratarPersona(selected.getSolicitudMatcheo().getSolicitante());
				} else {
					JOptionPane.showMessageDialog(null, "No es posible contratar al candidato seleccionado",
							"Alerta", JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnContratar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnContratar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnContratar.setIcon(new ImageIcon(VisualizarMatchOferta.class.getResource("/img/add-user.png")));
		btnContratar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnContratar.setBounds(767, 702, 214, 73);
		pnlVisualizarCandidato.add(btnContratar);
		
		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisponibilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDisponibilidad.setBounds(20, 721, 166, 41);
		pnlVisualizarCandidato.add(lblDisponibilidad);
		
		lblMostrarDisponibilidad = new JLabel("Estado");
		lblMostrarDisponibilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDisponibilidad.setForeground(Color.DARK_GRAY);
		lblMostrarDisponibilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDisponibilidad.setBounds(196, 722, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarDisponibilidad);
		
		JLabel lblNivelEducativo = new JLabel("Nivel educativo:");
		lblNivelEducativo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNivelEducativo.setBounds(20, 448, 166, 51);
		pnlVisualizarCandidato.add(lblNivelEducativo);
		
		lblMostrarNivelEducativo = new JLabel("nivelEducativo");
		lblMostrarNivelEducativo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNivelEducativo.setForeground(Color.DARK_GRAY);
		lblMostrarNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarNivelEducativo.setBounds(196, 448, 203, 51);
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
		lblCarrear.setBounds(0, 11, 66, 51);
		pnlUniversitario.add(lblCarrear);
		
		lblMostrarCarrera = new JLabel("Carrera");
		lblMostrarCarrera.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCarrera.setForeground(Color.DARK_GRAY);
		lblMostrarCarrera.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCarrera.setBounds(72, 11, 290, 51);
		pnlUniversitario.add(lblMostrarCarrera);
		
		JPanel pnlTecnicoSuperior = new JPanel();
		pnlTecnicoSuperior.setBackground(Color.WHITE);
		jtpNivelEducativo.addTab("New tab", null, pnlTecnicoSuperior, null);
		pnlTecnicoSuperior.setLayout(null);
		
		JLabel lblTecnico = new JLabel("Tecnico:");
		lblTecnico.setHorizontalAlignment(SwingConstants.LEFT);
		lblTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTecnico.setBounds(0, 11, 66, 51);
		pnlTecnicoSuperior.add(lblTecnico);
		
		lblMostrarTecnico = new JLabel("Tecnico");
		lblMostrarTecnico.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTecnico.setForeground(Color.DARK_GRAY);
		lblMostrarTecnico.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTecnico.setBounds(165, 11, 203, 51);
		pnlTecnicoSuperior.add(lblMostrarTecnico);
		
		JLabel lblAosExperiencia = new JLabel("A\u00F1os experiencia:");
		lblAosExperiencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblAosExperiencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblAosExperiencia.setBounds(0, 78, 160, 51);
		pnlTecnicoSuperior.add(lblAosExperiencia);
		
		lblMostrarAniosExp = new JLabel("A\u00F1os experiencia");
		lblMostrarAniosExp.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarAniosExp.setForeground(Color.DARK_GRAY);
		lblMostrarAniosExp.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarAniosExp.setBounds(165, 78, 203, 51);
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
		lblDatosSolicitud.setBounds(466, 24, 238, 34);
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
		lblMostrarDisphorario.setBounds(657, 75, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarDisphorario);
		
		lblMostrarMovilidad = new JLabel("Movilidad");
		lblMostrarMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMovilidad.setForeground(Color.DARK_GRAY);
		lblMostrarMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarMovilidad.setBounds(658, 128, 196, 41);
		pnlVisualizarCandidato.add(lblMostrarMovilidad);
		
		JLabel lblMovilidad = new JLabel("Movilidad:");
		lblMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMovilidad.setBounds(478, 128, 147, 41);
		pnlVisualizarCandidato.add(lblMovilidad);
		
		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLicencia.setBounds(477, 186, 148, 41);
		pnlVisualizarCandidato.add(lblLicencia);
		
		lblMostrarLicencia = new JLabel("Licencia");
		lblMostrarLicencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarLicencia.setForeground(Color.DARK_GRAY);
		lblMostrarLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarLicencia.setBounds(658, 186, 123, 41);
		pnlVisualizarCandidato.add(lblMostrarLicencia);
		
		JLabel lblTipoDeTrabajo = new JLabel("Tipo de trabajo:");
		lblTipoDeTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDeTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTipoDeTrabajo.setBounds(478, 240, 148, 41);
		pnlVisualizarCandidato.add(lblTipoDeTrabajo);
		
		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblModalidad.setBounds(478, 305, 148, 41);
		pnlVisualizarCandidato.add(lblModalidad);
		
		JLabel lblSalarioDeseado = new JLabel("Salario deseado:");
		lblSalarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSalarioDeseado.setBounds(478, 366, 148, 41);
		pnlVisualizarCandidato.add(lblSalarioDeseado);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha solicitud:");
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFechaSolicitud.setBounds(478, 437, 148, 41);
		pnlVisualizarCandidato.add(lblFechaSolicitud);
		
		lblMostrarTipoTrabajo = new JLabel("tipoTrabajo");
		lblMostrarTipoTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTipoTrabajo.setForeground(Color.DARK_GRAY);
		lblMostrarTipoTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTipoTrabajo.setBounds(658, 240, 260, 41);
		pnlVisualizarCandidato.add(lblMostrarTipoTrabajo);
		
		lblMostrarModalidad = new JLabel("Modalidad");
		lblMostrarModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarModalidad.setForeground(Color.DARK_GRAY);
		lblMostrarModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarModalidad.setBounds(658, 305, 196, 41);
		pnlVisualizarCandidato.add(lblMostrarModalidad);
		
		lblMostrarSalarioDeseado = new JLabel("SalarioDeseado");
		lblMostrarSalarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSalarioDeseado.setForeground(Color.DARK_GRAY);
		lblMostrarSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarSalarioDeseado.setBounds(658, 366, 123, 41);
		pnlVisualizarCandidato.add(lblMostrarSalarioDeseado);
		
		lblMostrarFechaSolicitud = new JLabel("FechaSolicitud");
		lblMostrarFechaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarFechaSolicitud.setForeground(Color.DARK_GRAY);
		lblMostrarFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarFechaSolicitud.setBounds(658, 437, 123, 41);
		pnlVisualizarCandidato.add(lblMostrarFechaSolicitud);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDireccin.setBounds(20, 409, 166, 41);
		pnlVisualizarCandidato.add(lblDireccin);
		
		lblMostrarDireccion = new JLabel("direccion");
		lblMostrarDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDireccion.setForeground(Color.DARK_GRAY);
		lblMostrarDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDireccion.setBounds(196, 409, 252, 41);
		pnlVisualizarCandidato.add(lblMostrarDireccion);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnVisualizarCandidato = new JButton("Visualizar candidato");
			btnVisualizarCandidato.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarDatosCand(selected);
					jtpVisualizar.setSelectedIndex(1);
				}
			});
			btnVisualizarCandidato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			btnVisualizarCandidato.setBackground(Color.WHITE);
			btnVisualizarCandidato.setActionCommand("OK");
			buttonPane.add(btnVisualizarCandidato);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setIcon(new ImageIcon(VisualizarMatchOferta.class.getResource("/img/cancelar16px.png")));
				btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
				btnCerrar.setBackground(Color.WHITE);
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		
	}
	
	private void mostrarMatches() {
		ArrayList<MatchOferta> listaMatcheo = Bolsa.getInstancia().getListaMatchOferta();
		tableModel.setRowCount(0);
		fila = new Object[tableModel.getColumnCount()];
		for (MatchOferta aux : listaMatcheo) {
			if(aux.getOfertaMatcheo().equals(ofertaActual)) {
				fila[0] = aux.getCodigo();
				fila[1] = aux.getSolicitudMatcheo().getSolicitante().getNombre() + " " + aux.getSolicitudMatcheo().getSolicitante().getApellidos();
				fila[2] = aux.getSolicitudMatcheo().getSolicitante().getProvincia();
				fila[3] = aux.getSolicitudMatcheo().getCodigo();
				Date fechaMatch = aux.getFechaMatcheo();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String formattedDate = dateFormat.format(fechaMatch);
				fila[4] = formattedDate;
				if(!aux.isAceptacionCandidato()) {
					fila[5] = "Pendiente aprob. candidato";	
				} else {
					fila [5] = "Aprobada p. candidato";  
				}
				 
				tableModel.addRow(fila);
			}
		}
	}
	
	//cargar datos en vista previa 
	private void cargarDatosCand(MatchOferta match) {

		Persona aux = match.getSolicitudMatcheo().getSolicitante();
		if(aux!=null) {
			lblMostrarNombreCompleto.setText(aux.getNombre()+" " +aux.getApellidos());
			lblMostrarCedula.setText(aux.getCedula());
			lblMostrarTelefono.setText(aux.getTelefono());
			Date fechaNacimiento = aux.getFechaNacimiento();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String fechaNacFormatted = dateFormat.format(fechaNacimiento);
			lblMostrarFechaNacimiento.setText(fechaNacFormatted);
			if(aux.getSexo().equalsIgnoreCase("m")) {
				lblMostrarSexo.setText("Masculino");
			} else {
				lblMostrarSexo.setText("Femenino");	
			}
			lblMostrarCorreo.setText(aux.getCorreoElectronico());
			lblMostrarProvincia.setText(aux.getProvincia());
			lblMostrarMunicipio.setText(aux.getMunicipio());
			lblMostrarDireccion.setText(aux.getDireccion());
			if(!aux.isEstado()) {
				lblMostrarEstado.setText("Desempleado");
			} else {
				lblMostrarEstado.setText("Empleado");
			}
			
			if(match.isAceptacionCandidato()) {
				lblMostrarDisponibilidad.setText("Dispuesto/a a trabajar");
			} else {
				lblMostrarDisponibilidad.setText("N/D");	
			}
			
			if(aux instanceof Universitario) {
				jtpNivelEducativo.setSelectedIndex(0);
				lblMostrarCarrera.setText(((Universitario) aux).getCarrera());
			} else if(aux instanceof TecnicoSuperior) {
				jtpNivelEducativo.setSelectedIndex(1);
				lblMostrarTecnico.setText(((TecnicoSuperior) aux).getTecnico());
				lblMostrarAniosExp.setText(String.valueOf(((TecnicoSuperior) aux).getAniosExperiencia()));
			} else if(aux instanceof Obrero) {
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
	}
}
