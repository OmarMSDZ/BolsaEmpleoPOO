package visual;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

import logica.Bolsa;
import logica.Persona;
import logica.Usuario;

public class VisualizarCandidatos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel tableModel;
	private static Object[] fila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VisualizarCandidatos dialog = new VisualizarCandidatos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public VisualizarCandidatos() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Laborea - Visualizar candidatos registrados");
		setResizable(false);
		setBounds(100, 100, 1020, 855);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1014, 820);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		String[] headers = { "Código", "Nombre compl.", "Prov.", "Municipio", "Sexo", "Fecha Nac.", "Cédula",
				"Licencia", "Estado laboral" };

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

		JButton btnVisualizar = new JButton("Visualizar candidato");
		btnVisualizar.setIcon(new ImageIcon(VisualizarCandidatos.class.getResource("/img/user2.png")));
		btnVisualizar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnVisualizar.setBackground(Color.WHITE);
		btnVisualizar.setBounds(617, 13, 220, 31);
		buttonPane.add(btnVisualizar);

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(e -> {
			int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar esta ventana?", "Confirmación",
					JOptionPane.WARNING_MESSAGE);
			if (opcion == JOptionPane.OK_OPTION) {
				dispose(); // cerrar ventana
			} else {
				JOptionPane.showMessageDialog(null, "Acción cancelada.", "Información", JOptionPane.INFORMATION_MESSAGE,
						null);
			}
		});
		btnCerrar.setIcon(new ImageIcon(VisualizarCandidatos.class.getResource("/img/cancelar16px.png")));
		btnCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setBounds(851, 13, 137, 31);
		buttonPane.add(btnCerrar);

		JPanel pnlInformacion = new JPanel();
		pnlInformacion.setLayout(null);
		pnlInformacion.setBackground(Color.WHITE);
		pnlInformacion.setBounds(0, 0, 1014, 744);
		pnlContenido.add(pnlInformacion);

		JScrollPane scpTabla = new JScrollPane();
		scpTabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		scpTabla.setBounds(25, 77, 963, 643);
		pnlInformacion.add(scpTabla);

		JTable tabla = new JTable(tableModel);
		scpTabla.setViewportView(tabla);

		JLabel lblCanditatosRegistrados = new JLabel("Candidatos registrados");
		lblCanditatosRegistrados.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblCanditatosRegistrados.setBounds(25, 13, 431, 35);
		pnlInformacion.add(lblCanditatosRegistrados);

		JSeparator sptSubrayado = new JSeparator();
		sptSubrayado.setForeground(Color.BLACK);
		sptSubrayado.setBounds(25, 54, 963, 10);
		pnlInformacion.add(sptSubrayado);

		// Llenar tabla
		mostrarCandidatos();
	}

	private void mostrarCandidatos() {
		ArrayList<Usuario> usuarios = Bolsa.getInstancia().getListaUsuarios();
		tableModel.setRowCount(0);
		fila = new Object[9]; // 9 columnas

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for (Usuario usuarioActual : usuarios) {
			if (usuarioActual instanceof Persona) {
				Persona actualPersona = (Persona) usuarioActual;

				fila[0] = actualPersona.getCodigo();
				fila[1] = actualPersona.getNombre() + " " + actualPersona.getApellidos();
				fila[2] = actualPersona.getProvincia();
				fila[3] = actualPersona.getMunicipio();
				fila[4] = actualPersona.getSexo();
				fila[5] = df.format(actualPersona.getFechaNacimiento());
				fila[6] = actualPersona.getCedula();
				fila[7] = actualPersona.isLicenciaConducir() ? "Sí" : "No";
				fila[8] = actualPersona.isEstadoEmpleado() ? "Empleado" : "Desempleado";

				tableModel.addRow(fila);
			}
		}
	}
}
