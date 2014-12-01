/*
 * Created on 08.11.2004
 *
 */
package de.hdm.bankProject.client;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import de.hdm.bankProject.data.Customer;

/**
 * Kundenformulare enthalten Felder fuer den Vornamen und den Nachnamen eines Kontoinhabers.
 * Fuer die Erzeugung, das Loeschen und das Auffinden anhand des Nachnamens existieren
 * jeweils Schaltflaechen und Menueeintraege.
 * 

 * @author Christian Rathke
 */
public class CustomerForm {

    private JTextField vornameText;
    private JTextField nachnameText;
    private JTextField idText;

    /**
     * Erzeugen der Felder und Schaltflaechen.
     * @param bi
     * @return
     */
    JPanel generateComponents(BankInterface bi) {
        JLabel vorname = new JLabel("Vorname", JLabel.TRAILING);
        vornameText = new JTextField(5);
        vornameText.setEditable(true);
        vornameText.setActionCommand("changeCustomer");
        vornameText.addActionListener(bi);

        JLabel nachname = new JLabel("Nachname", JLabel.TRAILING);
        nachnameText = new JTextField(5);
        nachnameText.setEditable(true);
        nachnameText.setActionCommand("changeCustomer");
        nachnameText.addActionListener(bi);

        JLabel id = new JLabel("ID", JLabel.TRAILING);
        idText = new JTextField();
        idText.setEditable(false);

        JPanel fieldsPane = new JPanel(new SpringLayout());
        fieldsPane.add(vorname);
        fieldsPane.add(vornameText);
        fieldsPane.add(nachname);
        fieldsPane.add(nachnameText);
        fieldsPane.add(id);
        fieldsPane.add(idText);

        fieldsPane.setLayout(new SpringLayout());
        SpringUtilities.makeCompactGrid(fieldsPane, 3, 2, 6, 6, 6, 6);

        JButton submit = new JButton("�ndern");
        submit.setActionCommand("changeCustomer");
        submit.addActionListener(bi);

        JButton delete = new JButton("L�schen");
        delete.setActionCommand("deleteCustomer");
        delete.addActionListener(bi);

        JButton find = new JButton("Suchen");
        find.setActionCommand("findCustomer");
        find.addActionListener(bi);

        JButton create = new JButton("Neu");
        create.setActionCommand("createCustomer");
        create.addActionListener(bi);

        JPanel buttonPane = new JPanel();
        buttonPane.add(submit);
        buttonPane.add(find);
        buttonPane.add(delete);
        buttonPane.add(create);

        JPanel pane = new JPanel(new BorderLayout());

        pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.black), "Kunde")));
        pane.add(fieldsPane, BorderLayout.CENTER);
        pane.add(buttonPane, BorderLayout.SOUTH);

        return pane;
    }

    /**
     * Produziert den Inhalt des Vornamen-Feldes.
     * @return
     */
    String getVorname() {
        return vornameText.getText();
    }

    /**
     * Produziert den Inhalt des Nachnamen-Feldes.
     * @return
     */
    String getNachname() {
        return nachnameText.getText();
    }

    /**
     * Das Setzen des Kunden bewirkt das Befuellen der Formularfelder.
     * @param c
     */
    void setCustomer(Customer c) {
        if (c != null) {
            nachnameText.setText(c.getLastName());
            vornameText.setText(c.getFirstName());
            idText.setText(Integer.toString(c.getId()));
        }
    }

    /**
     * Das Entfernen des Kunden bewirkt das Loeschen der Formularfelder.
     */
    void removeCustomer() {
        nachnameText.setText("");
        vornameText.setText("");
        idText.setText("");
    }
}
