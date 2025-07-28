package visual;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

import javax.swing.border.LineBorder;
import java.util.Date;

import logica.Bolsa;
import logica.Oferta;

public class ElementosOferta extends JPanel {

	private Oferta ofertaLaboral;
	private JLabel lblTitulo;
	private JLabel lblSalario;
	private JLabel lblModalidad;
	private JLabel lblTipo;
	private JLabel lblFecha;
	private JLabel lblArea;
	private JLabel lblMatchesObtenidos;

	public ElementosOferta(Oferta oferta) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK, 2, true),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		setBackground(Color.WHITE);
		setAlignmentX(LEFT_ALIGNMENT);

		this.ofertaLaboral = oferta;

		lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
		add(lblTitulo);

		lblArea = new JLabel("\u00C1rea");
		lblArea.setIcon(new ImageIcon(ElementosOferta.class.getResource("/img/iconLaboral_x16.png")));
		lblArea.setHorizontalAlignment(SwingConstants.LEFT);
		lblArea.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblArea.setAlignmentX(0.0f);
		add(lblArea);

		lblSalario = new JLabel("Salario");
		lblSalario.setHorizontalAlignment(SwingConstants.LEFT);
		lblSalario.setIcon(new ImageIcon(ElementosOferta.class.getResource("/img/money.png")));
		lblSalario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblSalario.setAlignmentX(LEFT_ALIGNMENT);
		add(lblSalario);

		lblModalidad = new JLabel("Modalidad");
		lblModalidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblModalidad.setIcon(new ImageIcon(ElementosOferta.class.getResource("/img/info.png")));
		lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblModalidad.setAlignmentX(LEFT_ALIGNMENT);
		add(lblModalidad);

		lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipo.setIcon(new ImageIcon(ElementosOferta.class.getResource("/img/briefcase.png")));
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblTipo.setAlignmentX(LEFT_ALIGNMENT);
		add(lblTipo);

		lblFecha = new JLabel("Fecha");
		lblFecha.setIcon(new ImageIcon(ElementosOferta.class.getResource("/img/iconCalendario_x16.png")));
		lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
		lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblFecha.setAlignmentX(LEFT_ALIGNMENT);
		add(lblFecha);
		
		lblMatchesObtenidos = new JLabel("Matches obtenidos" + Bolsa.getInstancia().contarMatchesOferta(oferta));
		lblMatchesObtenidos.setIcon(new ImageIcon(ElementosOferta.class.getResource("/img/match16.png")));
		lblMatchesObtenidos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMatchesObtenidos.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblMatchesObtenidos.setAlignmentX(0.0f);
		add(lblMatchesObtenidos);

		if (oferta.getCodigo() != null)
			lblTitulo.setText("Oferta #" + oferta.getCodigo());
		if (oferta.getArea() != null)
			lblArea.setText(oferta.getArea());
		if (oferta.getSalarioEstimado() > 0) {
			lblSalario.setText(String.valueOf(oferta.getSalarioEstimado()));
		} else {
			lblSalario.setText("No especificado");
		}
		if (oferta.getModalidad() != null)
			lblModalidad.setText(oferta.getModalidad());
		if (oferta.getTipo() != null)
			lblTipo.setText(oferta.getTipo());
		if (oferta.getFechaOferta() != null) {
			Date fechaOferta = oferta.getFechaOferta();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String formattedDate = formatoFecha.format(fechaOferta);
			lblFecha.setText(formattedDate);
		}

		SwingUtilities.invokeLater(() -> {
			setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
			revalidate();
		});
	}

	// === Getters y Setters ===
	public Oferta getOferta() {
		return ofertaLaboral;
	}

	public void setSolicitud(Oferta oferta) {
		this.ofertaLaboral = oferta;
	}
}
