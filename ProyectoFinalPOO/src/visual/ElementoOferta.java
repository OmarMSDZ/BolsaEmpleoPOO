package visual;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import logica.Oferta;

public class ElementoOferta extends JPanel {

    private Oferta oferta;
   

    private JLabel lblTitulo;
    private JLabel lblEmpresa;
    private JLabel lblUbicacion;
    private JLabel lblSalario;
    private JLabel lblModalidad;
    private JLabel lblTipo;
    private JLabel lblFecha;

    public ElementoOferta(Oferta oferta) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);
        setAlignmentX(LEFT_ALIGNMENT);

        this.oferta = oferta;

        lblTitulo = new JLabel("Título");
        lblTitulo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        add(lblTitulo);

        lblEmpresa = new JLabel("Empresa");
        lblEmpresa.setIcon(new ImageIcon(ElementoOferta.class.getResource("/img/building.png")));
        lblEmpresa.setForeground(new Color(100,149,237));
        lblEmpresa.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblEmpresa.setAlignmentX(LEFT_ALIGNMENT);
        add(lblEmpresa);

        lblUbicacion = new JLabel("Ubicación");
        lblUbicacion.setIcon(new ImageIcon(ElementoOferta.class.getResource("/img/pin.png")));
        lblUbicacion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblUbicacion.setAlignmentX(LEFT_ALIGNMENT);
        add(lblUbicacion);

        lblSalario = new JLabel("Salario");
        lblSalario.setIcon(new ImageIcon(ElementoOferta.class.getResource("/img/money.png")));
        lblSalario.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblSalario.setAlignmentX(LEFT_ALIGNMENT);
        add(lblSalario);

        lblModalidad = new JLabel("Modalidad");
        lblModalidad.setIcon(new ImageIcon(ElementoOferta.class.getResource("/img/info.png")));
        lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblModalidad.setAlignmentX(LEFT_ALIGNMENT);
        add(lblModalidad);

        lblTipo = new JLabel("Tipo");
        lblTipo.setIcon(new ImageIcon(ElementoOferta.class.getResource("/img/briefcase.png")));
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblTipo.setAlignmentX(LEFT_ALIGNMENT);
        add(lblTipo);

        lblFecha = new JLabel("Fecha");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblFecha.setAlignmentX(LEFT_ALIGNMENT);
        add(lblFecha);

        if (oferta.getPuestoTrab() != null) lblTitulo.setText(oferta.getPuestoTrab());
        if (oferta.getEmpReclutadora().getNombre() != null) lblEmpresa.setText(oferta.getEmpReclutadora().getNombre());
        if (oferta.getProvincia() != null) lblUbicacion.setText(oferta.getProvincia());
        if(oferta.getSalarioEstimado()>0) {
        	lblSalario.setText(String.valueOf(oferta.getSalarioEstimado()));
        } else {
        	lblSalario.setText("A discutir");	
        }
        if (oferta.getModalidad() != null) lblModalidad.setText(oferta.getModalidad());
        if (oferta.getTipo() != null) lblTipo.setText(oferta.getTipo());
        if (oferta.getFecha() != null) lblFecha.setText(String.valueOf(oferta.getFecha()));

        SwingUtilities.invokeLater(() -> {
            setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
            revalidate();
        });
    }

    // === Getters y Setters ===

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

}
