package visual;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Bolsa;
import logica.Empresa;
import logica.Usuario;

public class VisualizarEmpresas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel tableModel;
	private static Object[] fila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarEmpresas dialog = new VisualizarEmpresas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarEmpresas() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Laborea - Visualizar empresas registradas");
		setResizable(false);
		setBounds(100, 100, 1020, 855);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1014, 820);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		String[] headers = {
			"Código", "Nombre", "Provincia", "Municipio",
			"RNC", "Tipo empresa", "Sector empresarial", "Estado"
		};

		tableModel = new DefaultTableModel(null, headers) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		JPanel pnlContenido = new JPanel();
		pnlContenido.setBackground(Color.WHITE);
		pnlContenido.setBounds(0, 0, 1014, 807);
		contentPanel.add(pnlContenido);
		pnlContenido.setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 744, 1014, 63);
		pnlContenido.add(buttonPane);
		buttonPane.setLayout(null);
		buttonPane.setBackground(Color.WHITE);

		JButton btnVisualizar = new JButton("Visualizar empresa");
		btnVisualizar.setIcon(new ImageIcon(VisualizarEmpresas.class.getResource("/img/building.png")));
		btnVisualizar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVisualizar.setBackground(Color.WHITE);
		btnVisualizar.setBounds(633, 16, 220, 31);
		buttonPane.add(btnVisualizar);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> {
			int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar esta ventana?",
					"Confirmación", JOptionPane.WARNING_MESSAGE);
			if (opcion == JOptionPane.OK_OPTION) {
				dispose(); // cerrar ventana
			} else {
				JOptionPane.showMessageDialog(null, "Acción cancelada.", "Información",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		btnCerrar.setIcon(new ImageIcon(VisualizarEmpresas.class.getResource("/img/cancelar16px.png")));
		btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setBounds(865, 16, 137, 31);
		buttonPane.add(btnCerrar);

		JPanel pnlInformacion = new JPanel();
		pnlInformacion.setLayout(null);
		pnlInformacion.setBackground(Color.WHITE);
		pnlInformacion.setBounds(0, 0, 1014, 744);
		pnlContenido.add(pnlInformacion);

		JScrollPane scpTabla = new JScrollPane();
		scpTabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scpTabla.setBounds(30, 77, 963, 643);
		pnlInformacion.add(scpTabla);

		JTable tabla = new JTable(tableModel);
		scpTabla.setViewportView(tabla);

		JLabel lblEmpresasRegistradas = new JLabel("Empresas registradas");
		lblEmpresasRegistradas.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblEmpresasRegistradas.setBounds(30, 13, 431, 35);
		pnlInformacion.add(lblEmpresasRegistradas);

		JSeparator sptSubrayado = new JSeparator();
		sptSubrayado.setForeground(Color.BLACK);
		sptSubrayado.setBounds(30, 54, 963, 10);
		pnlInformacion.add(sptSubrayado);

		// Llenar tabla
		mostrarEmpresas();
	}

	private void mostrarEmpresas() {
		ArrayList<Usuario> usuarios = Bolsa.getInstancia().getListaUsuarios();
		tableModel.setRowCount(0);
		fila = new Object[8]; // 8 columnas

		for (Usuario u : usuarios) {
			if (u instanceof Empresa) {
				Empresa e = (Empresa) u;

				fila[0] = e.getCodigo();
				fila[1] = e.getNombre();
				fila[2] = e.getProvincia();
				fila[3] = e.getMunicipio();
				fila[4] = e.getRnc();
				fila[5] = e.getTipoEmpresa();
				fila[6] = e.getSectorEmpresarial();
				fila[7] = e.isEstado() ? "Activa" : "Inactiva";

				tableModel.addRow(fila);
			}
		}
	}
}
