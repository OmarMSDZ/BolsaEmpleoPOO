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

public class VisualizarNotificacionSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel tableModel;
	private static Object[] fila;
	private static MatchOferta selected = null;
	private static Solicitud solicitudActual;
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
	private JLabel lblMostrarLicencia;
	private JLabel lblMostrarMovilidad;
	private JLabel lblMostrarDisphorario;
	private JLabel lblMostrarTipoTrabajo;
	private JLabel lblMostrarModalidad;
	private JLabel lblMostrarSalarioDeseado;
	private JLabel lblMostrarFechaSolicitud;
	private JLabel lblMostrarDireccion;
	private JLabel lblCodigoSolicitud;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarNotificacionSolicitud dialog = new VisualizarNotificacionSolicitud(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarNotificacionSolicitud(Solicitud soli) {
		if (soli != null) {
			setTitle("Laborea - Ofertas sugeridas para la solicitud: " + soli.getCodigo());
			solicitudActual = soli; //para poder llevar soli a otras funciones 
		} else {
			setTitle("Laborea - Prueba ");
			solicitudActual = null;
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

		JPanel pnlVisualizarNotificaciones = new JPanel();
		pnlVisualizarNotificaciones.setBackground(Color.WHITE);
		jtpVisualizar.addTab("New tab", null, pnlVisualizarNotificaciones, null);
		pnlVisualizarNotificaciones.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 969, 696);
		pnlVisualizarNotificaciones.add(scrollPane);

		tblMatches = new JTable();
		tblMatches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// al hacer clic en la tabla
				int index = tblMatches.getSelectedRow();
				if (index >= 0) {
					selected = Bolsa.getInstancia().buscarMatchOfertaByCodigo(tblMatches.getValueAt(index, 0).toString());
					btnVisualizarCandidato.setEnabled(true);
					cargarDatosCandSol(selected);
				} else {
					btnVisualizarCandidato.setEnabled(false);
				}
			}
		});
		tblMatches.setBackground(Color.WHITE);
		tblMatches.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		// cargar headers de tabla y datos
		String[] headers = { "Empresa", "Provincia", "Puesto", "Fecha"};
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

		JLabel lblNotificacionesSolicitud = new JLabel("Notificaciones - solicitud:");
		lblNotificacionesSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNotificacionesSolicitud.setBounds(10, 26, 185, 35);
		pnlVisualizarNotificaciones.add(lblNotificacionesSolicitud);
		
		lblCodigoSolicitud = new JLabel("codigoSolicitud");
		lblCodigoSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblCodigoSolicitud.setBounds(202, 26, 185, 35);
		pnlVisualizarNotificaciones.add(lblCodigoSolicitud);

		JPanel pnlVisualizarOfertaNotif = new JPanel();
		pnlVisualizarOfertaNotif.setBackground(Color.WHITE);
		jtpVisualizar.addTab("New tab", null, pnlVisualizarOfertaNotif, null);
		pnlVisualizarOfertaNotif.setLayout(null);

		JLabel lblNewLabel = new JLabel("Datos Empresa");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 24, 238, 34);
		pnlVisualizarOfertaNotif.add(lblNewLabel);

		JLabel label_1 = new JLabel("Nombre completo:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_1.setBounds(20, 75, 166, 41);
		pnlVisualizarOfertaNotif.add(label_1);

		lblMostrarNombreCompleto = new JLabel("Nombre Completo");
		lblMostrarNombreCompleto.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNombreCompleto.setForeground(Color.DARK_GRAY);
		lblMostrarNombreCompleto.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarNombreCompleto.setBounds(196, 75, 260, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarNombreCompleto);

		JLabel label_3 = new JLabel("Cédula:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_3.setBounds(20, 113, 166, 41);
		pnlVisualizarOfertaNotif.add(label_3);

		lblMostrarCedula = new JLabel("Cedula");
		lblMostrarCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCedula.setForeground(Color.DARK_GRAY);
		lblMostrarCedula.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCedula.setBounds(196, 113, 196, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarCedula);

		JLabel label_5 = new JLabel("Sexo:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_5.setBounds(20, 154, 148, 41);
		pnlVisualizarOfertaNotif.add(label_5);

		lblMostrarSexo = new JLabel("Sexo");
		lblMostrarSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSexo.setForeground(Color.DARK_GRAY);
		lblMostrarSexo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarSexo.setBounds(196, 154, 123, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarSexo);

		JLabel label_7 = new JLabel("Fecha nacimiento:");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		label_7.setBounds(20, 229, 166, 41);
		pnlVisualizarOfertaNotif.add(label_7);

		lblMostrarFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblMostrarFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarFechaNacimiento.setForeground(Color.DARK_GRAY);
		lblMostrarFechaNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarFechaNacimiento.setBounds(196, 229, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarFechaNacimiento);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTelefono.setBounds(20, 264, 166, 41);
		pnlVisualizarOfertaNotif.add(lblTelefono);

		lblMostrarTelefono = new JLabel("telefono");
		lblMostrarTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTelefono.setForeground(Color.DARK_GRAY);
		lblMostrarTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTelefono.setBounds(196, 264, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarTelefono);

		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCorreo.setBounds(20, 305, 166, 41);
		pnlVisualizarOfertaNotif.add(lblCorreo);

		lblMostrarCorreo = new JLabel("Correo");
		lblMostrarCorreo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarCorreo.setForeground(Color.DARK_GRAY);
		lblMostrarCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarCorreo.setBounds(196, 305, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarCorreo);

		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblProvincia.setBounds(20, 342, 166, 41);
		pnlVisualizarOfertaNotif.add(lblProvincia);

		lblMostrarProvincia = new JLabel("provincia");
		lblMostrarProvincia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarProvincia.setForeground(Color.DARK_GRAY);
		lblMostrarProvincia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarProvincia.setBounds(196, 342, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarProvincia);

		lblMostrarMunicipio = new JLabel("municipio");
		lblMostrarMunicipio.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMunicipio.setForeground(Color.DARK_GRAY);
		lblMostrarMunicipio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarMunicipio.setBounds(196, 378, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarMunicipio);

		JLabel lblMunicipio_1 = new JLabel("Municipio:");
		lblMunicipio_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblMunicipio_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMunicipio_1.setBounds(20, 378, 166, 41);
		pnlVisualizarOfertaNotif.add(lblMunicipio_1);

		JLabel lblEst = new JLabel("Estado:");
		lblEst.setHorizontalAlignment(SwingConstants.LEFT);
		lblEst.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEst.setBounds(20, 682, 166, 41);
		pnlVisualizarOfertaNotif.add(lblEst);

		lblMostrarEstado = new JLabel("Estado");
		lblMostrarEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarEstado.setForeground(Color.DARK_GRAY);
		lblMostrarEstado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarEstado.setBounds(196, 682, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarEstado);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 69, 992, 17);
		pnlVisualizarOfertaNotif.add(separator);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtpVisualizar.setSelectedIndex(0);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(VisualizarNotificacionSolicitud.class.getResource("/img/close2.png")));
		lblNewLabel_1.setBounds(904, 11, 77, 67);
		pnlVisualizarOfertaNotif.add(lblNewLabel_1);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// contratar candidato seleccionado (Solo si este ha aceptado)
				if (selected != null && selected.isAceptacionCandidato()) {
					Bolsa.getInstancia().contratarPersona(selected.getSolicitudMatcheo().getSolicitante());
				} else {
					JOptionPane.showMessageDialog(null, "No es posible contratar al candidato seleccionado", "Alerta",
							JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnAceptar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAceptar.setIcon(new ImageIcon(VisualizarNotificacionSolicitud.class.getResource("/img/right.png")));
		btnAceptar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAceptar.setBounds(549, 702, 214, 73);
		pnlVisualizarOfertaNotif.add(btnAceptar);

		JLabel lblDisponibilidad = new JLabel("Disponibilidad:");
		lblDisponibilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisponibilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDisponibilidad.setBounds(20, 721, 166, 41);
		pnlVisualizarOfertaNotif.add(lblDisponibilidad);

		lblMostrarDisponibilidad = new JLabel("Estado");
		lblMostrarDisponibilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDisponibilidad.setForeground(Color.DARK_GRAY);
		lblMostrarDisponibilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDisponibilidad.setBounds(196, 722, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarDisponibilidad);

		JLabel lblNivelEducativo = new JLabel("Nivel educativo:");
		lblNivelEducativo.setHorizontalAlignment(SwingConstants.LEFT);
		lblNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNivelEducativo.setBounds(20, 448, 166, 51);
		pnlVisualizarOfertaNotif.add(lblNivelEducativo);

		lblMostrarNivelEducativo = new JLabel("nivelEducativo");
		lblMostrarNivelEducativo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarNivelEducativo.setForeground(Color.DARK_GRAY);
		lblMostrarNivelEducativo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarNivelEducativo.setBounds(196, 448, 203, 51);
		pnlVisualizarOfertaNotif.add(lblMostrarNivelEducativo);

		JLabel lblDatosSolicitud = new JLabel("Datos Oferta");
		lblDatosSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblDatosSolicitud.setBounds(474, 24, 238, 34);
		pnlVisualizarOfertaNotif.add(lblDatosSolicitud);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(466, 69, 13, 717);
		pnlVisualizarOfertaNotif.add(separator_1);

		JLabel lblDisponibilidadDeHorario = new JLabel("Disp. de horario:");
		lblDisponibilidadDeHorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisponibilidadDeHorario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDisponibilidadDeHorario.setBounds(477, 75, 147, 41);
		pnlVisualizarOfertaNotif.add(lblDisponibilidadDeHorario);

		lblMostrarDisphorario = new JLabel("DispHorario");
		lblMostrarDisphorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDisphorario.setForeground(Color.DARK_GRAY);
		lblMostrarDisphorario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDisphorario.setBounds(657, 75, 260, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarDisphorario);

		lblMostrarMovilidad = new JLabel("Movilidad");
		lblMostrarMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarMovilidad.setForeground(Color.DARK_GRAY);
		lblMostrarMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarMovilidad.setBounds(658, 128, 196, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarMovilidad);

		JLabel lblMovilidad = new JLabel("Movilidad:");
		lblMovilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMovilidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMovilidad.setBounds(478, 128, 147, 41);
		pnlVisualizarOfertaNotif.add(lblMovilidad);

		JLabel lblLicencia = new JLabel("Licencia:");
		lblLicencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblLicencia.setBounds(20, 189, 148, 41);
		pnlVisualizarOfertaNotif.add(lblLicencia);

		lblMostrarLicencia = new JLabel("Licencia");
		lblMostrarLicencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarLicencia.setForeground(Color.DARK_GRAY);
		lblMostrarLicencia.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarLicencia.setBounds(201, 189, 123, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarLicencia);

		JLabel lblTipoDeTrabajo = new JLabel("Tipo de trabajo:");
		lblTipoDeTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoDeTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblTipoDeTrabajo.setBounds(477, 180, 148, 41);
		pnlVisualizarOfertaNotif.add(lblTipoDeTrabajo);

		JLabel lblModalidad = new JLabel("Modalidad:");
		lblModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblModalidad.setBounds(477, 245, 148, 41);
		pnlVisualizarOfertaNotif.add(lblModalidad);

		JLabel lblSalarioDeseado = new JLabel("Salario deseado:");
		lblSalarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSalarioDeseado.setBounds(477, 306, 148, 41);
		pnlVisualizarOfertaNotif.add(lblSalarioDeseado);

		JLabel lblFechaSolicitud = new JLabel("Fecha solicitud:");
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFechaSolicitud.setBounds(477, 377, 148, 41);
		pnlVisualizarOfertaNotif.add(lblFechaSolicitud);

		lblMostrarTipoTrabajo = new JLabel("tipoTrabajo");
		lblMostrarTipoTrabajo.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarTipoTrabajo.setForeground(Color.DARK_GRAY);
		lblMostrarTipoTrabajo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarTipoTrabajo.setBounds(657, 180, 260, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarTipoTrabajo);

		lblMostrarModalidad = new JLabel("Modalidad");
		lblMostrarModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarModalidad.setForeground(Color.DARK_GRAY);
		lblMostrarModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarModalidad.setBounds(657, 245, 196, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarModalidad);

		lblMostrarSalarioDeseado = new JLabel("SalarioDeseado");
		lblMostrarSalarioDeseado.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarSalarioDeseado.setForeground(Color.DARK_GRAY);
		lblMostrarSalarioDeseado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarSalarioDeseado.setBounds(657, 306, 123, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarSalarioDeseado);

		lblMostrarFechaSolicitud = new JLabel("FechaSolicitud");
		lblMostrarFechaSolicitud.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarFechaSolicitud.setForeground(Color.DARK_GRAY);
		lblMostrarFechaSolicitud.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarFechaSolicitud.setBounds(657, 377, 123, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarFechaSolicitud);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.LEFT);
		lblDireccin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDireccin.setBounds(20, 418, 166, 41);
		pnlVisualizarOfertaNotif.add(lblDireccin);

		lblMostrarDireccion = new JLabel("direccion");
		lblMostrarDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lblMostrarDireccion.setForeground(Color.DARK_GRAY);
		lblMostrarDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblMostrarDireccion.setBounds(196, 418, 252, 41);
		pnlVisualizarOfertaNotif.add(lblMostrarDireccion);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setIcon(new ImageIcon(VisualizarNotificacionSolicitud.class.getResource("/img/close1.png")));
		btnRechazar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRechazar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRechazar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRechazar.setBounds(767, 702, 214, 73);
		pnlVisualizarOfertaNotif.add(btnRechazar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnVisualizarCandidato = new JButton("Visualizar");
			btnVisualizarCandidato.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarDatosCandSol(selected);
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
				btnCerrar.setIcon(new ImageIcon(VisualizarNotificacionSolicitud.class.getResource("/img/cancelar16px.png")));
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
			if (aux.getSolicitudMatcheo().equals(solicitudActual)) {
				fila[0] = aux.getOfertaMatcheo().getEmpReclutadora().getNombre();
				fila[1] =aux.getOfertaMatcheo().getEmpReclutadora().getProvincia(); 
				fila[2] = aux.getOfertaMatcheo().getPuestoTrab();
				Date fechaMatch = aux.getFechaMatcheo();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String formattedDate = dateFormat.format(fechaMatch);
				fila[3] = formattedDate;
				 
				tableModel.addRow(fila);
			}
		}
	}

	// cargar datos en vista previa
	private void cargarDatosCandSol(MatchOferta match) {
		Persona aux = match.getSolicitudMatcheo().getSolicitante();
		Solicitud auxSoli = match.getSolicitudMatcheo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		//datos de candidato
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
			if (!aux.isEstado()) {
				lblMostrarEstado.setText("Desempleado");
			} else {
				lblMostrarEstado.setText("Empleado");
			}

			if (match.isAceptacionCandidato()) {
				lblMostrarDisponibilidad.setText("Dispuesto/a a trabajar");
			} else {
				lblMostrarDisponibilidad.setText("N/D");
			}

			 
		}
		//datos de solicitud
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
