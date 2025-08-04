package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Bolsa;
import logica.MatchOferta;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;

public class InformeMatches extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblMatches;
	private static DefaultTableModel tableModel;
	private static Object[] fila;
	private JLabel lblMostrarTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformeMatches dialog = new InformeMatches();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformeMatches() {
		setResizable(false);
		setTitle("Laborea - Informe matches realizados");
		setBounds(100, 100, 1102, 647);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 1076, 468);
		contentPanel.add(scrollPane);

		tblMatches = new JTable();
		tblMatches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		tblMatches.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		// cargar headers de tabla y datos
		String[] headers = { "Código", "Candidato", "Oferta", "Área", "Empresa", "Contratado", "Fecha" };
		tableModel = new DefaultTableModel(null, headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Nunca permitir edición
				return false;
			}
		};

		tblMatches.setModel(tableModel);

//		// cargar datos
//		cargarMatches();
//		cantMatches();

		scrollPane.setViewportView(tblMatches);

		JLabel lblNewLabel = new JLabel("Matches realizados");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblNewLabel.setBounds(10, 24, 430, 23);
		contentPanel.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 58, 1076, 11);
		contentPanel.add(separator);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTotal.setBounds(10, 68, 71, 23);
		contentPanel.add(lblTotal);

		lblMostrarTotal = new JLabel("0");
		lblMostrarTotal.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMostrarTotal.setBounds(86, 68, 95, 23);
		contentPanel.add(lblMostrarTotal);

		JButton btnNewButton = new JButton("Recargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarMatches();
				cantMatches();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton.setIcon(new ImageIcon(InformeMatches.class.getResource("/img/refresh.png")));
		btnNewButton.setBounds(960, 63, 126, 37);
		contentPanel.add(btnNewButton);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setIcon(new ImageIcon(InformeMatches.class.getResource("/img/cancelar16px.png")));
		btnCerrar.setBounds(960, 567, 126, 44);
		contentPanel.add(btnCerrar);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar esta ventana?",
						"Confirmación", JOptionPane.WARNING_MESSAGE);
				if (opcion == JOptionPane.OK_OPTION) {
					dispose(); // cerrar ventana
				} else {
					JOptionPane.showMessageDialog(null, "Acción cancelada.", "Información",
							JOptionPane.INFORMATION_MESSAGE, null);
				}
			}
		});
		btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setActionCommand("Cancel");
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.setLayout(null);
			}
		}
		cargarMatches(); // Mostrar todos los matcheos disponibles esta el momento
	}

	// cargar matches
	private void cargarMatches() {
		if (!Bolsa.getInstancia().getListaMatchOferta().isEmpty()) {

			ArrayList<MatchOferta> listaMatcheo = Bolsa.getInstancia().getListaMatchOferta();
			tableModel.setRowCount(0);
			fila = new Object[tableModel.getColumnCount()];
			for (MatchOferta aux : listaMatcheo) {

				if (!aux.getSolicitudMatcheo().getSolicitante().isEstadoEmpleado()) {

					fila[0] = aux.getCodigo();
					fila[1] = aux.getSolicitudMatcheo().getSolicitante().getNombre() + " "
							+ aux.getSolicitudMatcheo().getSolicitante().getApellidos();
					fila[2] = aux.getOfertaMatcheo().getPuestoTrab();
					fila[3] = aux.getOfertaMatcheo().getArea();
					fila[4] = aux.getOfertaMatcheo().getEmpReclutadora().getNombre();
					if (aux.isAceptacionEmpresa()) {
						fila[5] = "Si";
					} else {
						fila[5] = "No";
					}
					Date fechaMatch = aux.getFechaMatcheo();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String formattedDate = dateFormat.format(fechaMatch);
					fila[6] = formattedDate;
				}

				tableModel.addRow(fila);
			}
		}
	}

	// cantidad total de matches
	private void cantMatches() {
		if (!Bolsa.getInstancia().getListaMatchOferta().isEmpty()) {
			lblMostrarTotal.setText(String.valueOf(Bolsa.getInstancia().getListaMatchOferta().size()));
		}
	}
}
