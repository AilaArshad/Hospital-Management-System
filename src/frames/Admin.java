/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import AllClasses.*;
import DatabaseSetup.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.*;
import java.util.Date;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aila Arshad
 */
public class Admin extends javax.swing.JFrame {

    private int lastSelectedRow = -1;
    private int lastSelectedRow1 = -1;
    private int lastSelectedRow2 = -1;
    private int lastSelectedRow3 = -1;

    /**
     * Creates new form Admin
     */
    public Admin() {
        initComponents();
        this.setLocationRelativeTo(null);
        setNextPatientId();
        setNextDoctorId();
        setNextBillId();
        setNextAppointmentId();
        populatePatientTable();
        populateAppointmentTable();
        populateBillTable();
        populateDoctorTable();
        populateDoctorComboBox();
        populatePatientComboBox();
        populateAppointmentComboBox();
        dateListnor();
    }

    private void setNextPatientId() {
        Implementation imp = new Implementation();
        int nextId = imp.patientNextId();
        p_id.setText(String.valueOf(nextId));
        p_id.setEditable(false);
    }

    private void setNextBillId() {
        Implementation imp = new Implementation();
        int nextId = imp.billNextId();
        b_id.setText(String.valueOf(nextId));
        b_id.setEditable(false);
    }

    private void setNextAppointmentId() {
        Implementation imp = new Implementation();
        int nextId = imp.appointmentNextId();
        a_id.setText(String.valueOf(nextId));
        a_id.setEditable(false);
    }

    private void setNextDoctorId() {
        Implementation imp = new Implementation();
        int nextId = imp.doctorNextId();
        d_id.setText(String.valueOf(nextId));
        d_id.setEditable(false);
    }

    private void clearTextFields() {
        p_name.setText("Name");
        age.setText("Age");
        disease.setText("Disease");
        ph_no.setText("Phone Number");
        setNextPatientId();
    }

    private void clearTextFieldsDoc() {
        d_name.setText("Name");
        qualification.setText("Qualification");
        department.setText("Department");
        designation.setText("Designation");
        salary.setText("Salary");
        setNextDoctorId();
    }

    private void clearTextFieldsApp() {
        pat_name.setSelectedIndex(0);
        doc_name.setSelectedIndex(0);
        a_date.setDate(null);
        time.setText("");
        app_type.setText("");
        setNextAppointmentId();
    }

    private void clearTextFieldsBill() {
        app_id.setSelectedIndex(0);
        payer_name.setText("");
        b_date.setDate(null);
        amount.setText("");
        setNextBillId();
    }

    private void populatePatientTable() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        Implementation imp = new Implementation();
        ArrayList<Patient> patients = imp.showPatientData();
        for (Patient patient : patients) {
            Object[] row = new Object[]{
                patient.getpId(),
                patient.getName(),
                patient.getAge(),
                patient.getDisease(),
                patient.getPhNo()
            };
            tableModel.addRow(row);
        }
    }

    private void populateDoctorTable() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable2.getModel();
        tableModel.setRowCount(0);
        Implementation imp = new Implementation();
        ArrayList<Doctor> doctors = imp.showDoctorData();
        for (Doctor doctor : doctors) {
            Object[] row = new Object[]{
                doctor.getdId(),
                doctor.getName(),
                doctor.getQualification(),
                doctor.getDesignation(),
                doctor.getSalary(),
                doctor.getDepartment()
            };
            tableModel.addRow(row);
        }
    }

    private void populateBillTable() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable4.getModel();
        tableModel.setRowCount(0);
        Implementation imp = new Implementation();
        ArrayList<Bill> bills = imp.showBillData();
        for (Bill bill : bills) {
            Object[] row = new Object[]{
                bill.getbId(),
                bill.getaId(),
                bill.getDate(),
                bill.getPayer_name(),
                bill.getAmount(),};
            tableModel.addRow(row);
        }
    }

    private void populateAppointmentTable() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable3.getModel();
        tableModel.setRowCount(0);
        Implementation imp = new Implementation();
        ArrayList<Appointment> appointments = imp.showAppointmentData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        for (Appointment appoint : appointments) {
            Date date = appoint.getDate_time();
            String formattedDate = dateFormat.format(date);
            String formattedTime = timeFormat.format(date);
            Object[] row = new Object[]{
                appoint.getaId(),
                appoint.getpName(),
                appoint.getdName(),
                formattedDate,
                formattedTime,
                appoint.getApp_type()
            };
            tableModel.addRow(row);
        }
    }

    private void updatePatientTable(ArrayList<Patient> patients) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (Patient patient : patients) {
            model.addRow(new Object[]{
                patient.getpId(),
                patient.getName(),
                patient.getAge(),
                patient.getDisease(),
                patient.getPhNo()
            });
        }
    }

    private void updateDoctorTable(ArrayList<Doctor> doctors) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        for (Doctor doc : doctors) {
            model.addRow(new Object[]{
                doc.getdId(),
                doc.getName(),
                doc.getQualification(),
                doc.getDesignation(),
                doc.getSalary(),
                doc.getDepartment()
            });
        }
    }

    private void updateAppointmenrTable(ArrayList<Appointment> appointments) {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        for (Appointment appoint : appointments) {
            Date date = appoint.getDate_time();
            String formattedDate = dateFormat.format(date);
            String formattedTime = timeFormat.format(date);
            model.addRow(new Object[]{
                appoint.getaId(),
                appoint.getpName(),
                appoint.getdName(),
                formattedDate,
                formattedTime,
                appoint.getApp_type()
            });
        }
    }

    private void updateBillTable(ArrayList<Bill> bills) {
        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        model.setRowCount(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Bill bill : bills) {
            try {
                String dateStr = bill.getDate();
                Date parsedDate = dateFormat.parse(dateStr);
                String formattedDate = dateFormat.format(parsedDate);
                model.addRow(new Object[]{
                    bill.getbId(),
                    bill.getaId(),
                    formattedDate,
                    bill.getPayer_name(),
                    bill.getAmount()
                });
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Invalid date format in bill: " + e.getMessage());
            }
        }
    }

    private void updatePatientTableByDoctor(String doctorName) {
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        model.setRowCount(0);
        Implementation imp = new Implementation();
        ArrayList<String[]> patients = imp.patientsByDoctorName(doctorName);
        for (String[] patient : patients) {
            String patientId = patient[0];
            String patientName = patient[1];
            model.addRow(new Object[]{patientId, patientName});
        }
    }

    private void populateDoctorComboBox() {
        Implementation imp = new Implementation();
        String[] doctorNames = imp.doctorList();
        doc_name.removeAllItems();
        for (String doctorName : doctorNames) {
            doc_name.addItem(doctorName);
        }
    }

    private void populatePatientComboBox() {
        Implementation imp = new Implementation();
        String[] patientsNames = imp.patientList();
        pat_name.removeAllItems();
        for (String doctorName : patientsNames) {
            pat_name.addItem(doctorName);
        }
    }

    private void populateAppointmentComboBox() {
        Implementation imp = new Implementation();
        String[] appointmentIds = imp.appointmentIds();
        for (String appIds : appointmentIds) {
            app_id.addItem(appIds);
        }
    }

    private void updateBillTableByDate(String[][] bills) {
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        model.setRowCount(0);
        for (String[] bill : bills) {
            model.addRow(new Object[]{bill[0], bill[1]});
        }
    }

    private void dateListnor() {
        date.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    Date selectedDate = date.getDate();
                    if (selectedDate != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateStr = dateFormat.format(selectedDate);
                        Implementation imp = new Implementation();
                        String[][] bills = imp.billByDate(dateStr);
                        updateBillTableByDate(bills);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker = new com.raven.swing.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        p_id = new javax.swing.JTextField();
        p_name = new javax.swing.JTextField();
        age = new javax.swing.JTextField();
        disease = new javax.swing.JTextField();
        ph_no = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        d_id = new javax.swing.JTextField();
        d_name = new javax.swing.JTextField();
        qualification = new javax.swing.JTextField();
        designation = new javax.swing.JTextField();
        salary = new javax.swing.JTextField();
        department = new javax.swing.JTextField();
        d_search = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        a_id = new javax.swing.JTextField();
        time = new javax.swing.JTextField();
        app_type = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pat_name = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        doc_name = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        a_date = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        s_pat = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        s_app = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        b_id = new javax.swing.JTextField();
        payer_name = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        app_id = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        b_date = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        s_d_name = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        s_p_name = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        date = new com.toedter.calendar.JDateChooser();

        timePicker.setBackground(new java.awt.Color(204, 204, 204));
        timePicker.setForeground(new java.awt.Color(0, 0, 0));
        timePicker.setDisplayText(time);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), "Patient Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 3, 18))); // NOI18N

        p_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p_id.setText("ID");
        p_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        p_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p_name.setText("Name");
        p_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        age.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        age.setText("Age");
        age.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        disease.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        disease.setText("Disease");
        disease.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ph_no.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ph_no.setText("Phone Number");
        ph_no.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(p_id)
                    .addComponent(p_name)
                    .addComponent(age)
                    .addComponent(disease)
                    .addComponent(ph_no, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(p_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(disease, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(ph_no, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 23, -1, -1));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Age", "Disease", "Phone Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setShowGrid(true);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 69, 540, 424));

        search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        search.setText("Search Name");
        search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        jPanel2.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 23, 540, 40));

        jPanel8.setBackground(new java.awt.Color(153, 153, 255));
        jPanel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Add");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 424, -1, 43));

        jPanel9.setBackground(new java.awt.Color(153, 204, 255));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Delete");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 424, -1, -1));

        jPanel10.setBackground(new java.awt.Color(0, 204, 204));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Update");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel3)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 424, -1, 43));

        jTabbedPane1.addTab("Patient", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), "Doctor Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 3, 18))); // NOI18N

        d_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        d_id.setText("ID");
        d_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        d_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        d_name.setText("Name");
        d_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        qualification.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        qualification.setText("Qualification");
        qualification.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        designation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        designation.setText("Designation");
        designation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        salary.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        salary.setText("Salary");
        salary.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        department.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        department.setText("Department");
        department.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        department.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(d_id)
                    .addComponent(d_name)
                    .addComponent(qualification)
                    .addComponent(designation)
                    .addComponent(salary, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(department, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(d_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(d_name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(qualification, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(designation, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(department, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 23, -1, -1));

        d_search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        d_search.setText("Search Name");
        d_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        d_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                d_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                d_searchFocusLost(evt);
            }
        });
        d_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                d_searchKeyReleased(evt);
            }
        });
        jPanel3.add(d_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 23, 540, 40));

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Qualification", "Designation", "Salary", "Department"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setShowGrid(true);
        jTable2.setSurrendersFocusOnKeystroke(true);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 69, 540, 424));

        jPanel12.setBackground(new java.awt.Color(153, 153, 255));
        jPanel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel12MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Add");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel4)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 424, -1, 43));

        jPanel13.setBackground(new java.awt.Color(153, 204, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Delete");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 424, -1, -1));

        jPanel14.setBackground(new java.awt.Color(0, 204, 204));
        jPanel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Update");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel6)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 424, -1, 43));

        jTabbedPane1.addTab("Doctor", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), "Appointment Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 3, 18))); // NOI18N

        a_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        a_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        time.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        time.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        time.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeActionPerformed(evt);
            }
        });

        app_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        app_type.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        app_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                app_typeActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("ID:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Doctor Name:");

        pat_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Patient Name:");

        doc_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Date:");

        a_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        a_date.setNextFocusableComponent(a_date);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Time:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Appointment Type:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/time.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addComponent(doc_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(a_id)
                    .addComponent(app_type, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pat_name, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(a_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(a_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pat_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doc_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a_date, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(app_type, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 20, -1, -1));

        s_pat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        s_pat.setText("Search Patient ");
        s_pat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        s_pat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                s_patFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_patFocusLost(evt);
            }
        });
        s_pat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_patKeyReleased(evt);
            }
        });
        jPanel4.add(s_pat, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 20, 540, 40));

        jTable3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        jTable3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", " Patient Name", "Doctor Name", "Date", "Time", "Appointment Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setShowGrid(true);
        jTable3.setSurrendersFocusOnKeystroke(true);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 540, 424));

        jPanel16.setBackground(new java.awt.Color(153, 153, 255));
        jPanel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Add");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(40, 40, 40))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 447, 120, -1));

        jPanel17.setBackground(new java.awt.Color(153, 204, 255));
        jPanel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Delete");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel14)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(317, 447, -1, -1));

        jTabbedPane1.addTab("Appointment", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        s_app.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        s_app.setText("Appointment ID");
        s_app.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        s_app.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                s_appFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_appFocusLost(evt);
            }
        });
        s_app.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_appKeyReleased(evt);
            }
        });
        jPanel5.add(s_app, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 18, 540, 40));

        jTable4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        jTable4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Appointment ID", "Date", "Payer Name ", "Payable Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.setShowGrid(true);
        jTable4.setSurrendersFocusOnKeystroke(true);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 64, 540, 424));

        jPanel19.setBackground(new java.awt.Color(153, 153, 255));
        jPanel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel19MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Add");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel15)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(334, 451, -1, -1));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), "Bill Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 3, 18))); // NOI18N

        b_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        b_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        payer_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        payer_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        payer_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payer_nameActionPerformed(evt);
            }
        });

        amount.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        amount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("ID:");

        app_id.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Appointment ID:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Date:");

        b_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Payer Name:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Payable Amount:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel19)
                        .addComponent(jLabel18)
                        .addComponent(jLabel16)
                        .addComponent(b_id)
                        .addComponent(payer_name, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                        .addComponent(amount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                        .addComponent(app_id, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(3, 3, 3)
                .addComponent(b_id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(app_id, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b_date, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payer_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel5.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 18, -1, 415));

        jTabbedPane1.addTab("Bill", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        s_d_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_d_name.setText("Doctor Name");
        s_d_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        s_d_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                s_d_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_d_nameFocusLost(evt);
            }
        });
        s_d_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_d_nameActionPerformed(evt);
            }
        });
        s_d_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_d_nameKeyReleased(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "Patient Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.setShowGrid(true);
        jScrollPane5.setViewportView(jTable5);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bill ID", "Bill Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable6.setShowGrid(true);
        jScrollPane6.setViewportView(jTable6);

        s_p_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        s_p_name.setText("Patient Name");
        s_p_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        s_p_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                s_p_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                s_p_nameFocusLost(evt);
            }
        });
        s_p_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                s_p_nameKeyReleased(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Appointment ID", "Appointment Type ", "Disease", "Doctor", "Bill Amount", "Appointment Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.setShowGrid(true);
        jScrollPane7.setViewportView(jTable7);

        date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(s_d_name, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_p_name, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(s_p_name, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(s_d_name)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Reports", jPanel6);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 540));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void departmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departmentActionPerformed

    private void app_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_app_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_app_typeActionPerformed

    private void timeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeActionPerformed

    private void payer_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payer_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payer_nameActionPerformed

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        timePicker.showPopup(this, 100, 100);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void s_d_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_d_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_d_nameActionPerformed

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        // TODO add your handling code here:
        String idText = p_id.getText();
        String name = p_name.getText();
        String ageText = age.getText();
        String medicalIssue = disease.getText();
        String phoneNumber = ph_no.getText();
        if (idText.isEmpty() || name.isEmpty() || ageText.isEmpty() || medicalIssue.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            int age = Integer.parseInt(ageText);
            Patient newPatient = new Patient(id, name, age, medicalIssue, phoneNumber);
            Implementation patientDAO = new Implementation();
            patientDAO.addPatient(newPatient);
            JOptionPane.showMessageDialog(this, "Patient added successfully!");
            populatePatientTable();
            clearTextFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID and Age must be numbers.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while adding the patient.");
        }
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        String patientIdText = p_id.getText();
        try {
            int patientId = Integer.parseInt(patientIdText);
            Implementation patientDAO = new Implementation();
            patientDAO.deletePatient(patientId);
            JOptionPane.showMessageDialog(null, "Patient with ID " + patientId + " deleted successfully.");
            populatePatientTable();
            clearTextFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Patient ID. Please enter a valid number.");
        }
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == lastSelectedRow) {
            clearTextFields();
            lastSelectedRow = -1;
        } else {
            if (selectedRow >= 0) {
                p_id.setText(jTable1.getValueAt(selectedRow, 0).toString());
                p_name.setText(jTable1.getValueAt(selectedRow, 1).toString());
                age.setText(jTable1.getValueAt(selectedRow, 2).toString());
                disease.setText(jTable1.getValueAt(selectedRow, 3).toString());
                ph_no.setText(jTable1.getValueAt(selectedRow, 4).toString());
                lastSelectedRow = selectedRow;
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to view details.");
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        int id = Integer.parseInt(p_id.getText());
        String name = p_name.getText();
        int p_age = Integer.parseInt(age.getText());
        String p_disease = disease.getText();
        String phNo = ph_no.getText(); //
        Patient updatedPatient = new Patient(id, name, p_age, p_disease, phNo);
        Implementation imp = new Implementation();
        imp.updatePatient(id, updatedPatient);
        JOptionPane.showMessageDialog(null, "Patient with ID " + id + " updated successfully.");
        populatePatientTable();
        clearTextFields();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        String searchText = search.getText().trim();
        if (!searchText.isEmpty()) {
            Implementation imp = new Implementation();
            ArrayList<Patient> searchResults = imp.searchPatient(searchText);
            updatePatientTable(searchResults);
        } else {
            populatePatientTable();
        }
    }//GEN-LAST:event_searchKeyReleased

    private void searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusGained
        // TODO add your handling code here:
        if (search.getText().trim().equalsIgnoreCase("Search Name")) {
            search.setText("");
        }
    }//GEN-LAST:event_searchFocusGained

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        // TODO add your handling code here:
        if (search.getText().isBlank()) {
            search.setText("Search Name");
        }
    }//GEN-LAST:event_searchFocusLost

    private void jPanel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel12MouseClicked
        // TODO add your handling code here:
        String idText = d_id.getText();
        String name = d_name.getText();
        String qua = qualification.getText();
        String des = designation.getText();
        String dep = department.getText();
        String sal = salary.getText();
        if (idText.isEmpty() || name.isEmpty() || qua.isEmpty() || des.isEmpty() || dep.isEmpty() || sal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            int income = Integer.parseInt(salary.getText());
            Doctor newDoctor = new Doctor(id, name, qua, des, income, dep);
            Implementation patientDAO = new Implementation();
            patientDAO.addDoctor(newDoctor);
            JOptionPane.showMessageDialog(this, "Doctor added successfully!");
            populateDoctorTable();
            clearTextFieldsDoc();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID and salary must be numbers.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred while adding the patient.");
        }
    }//GEN-LAST:event_jPanel12MouseClicked

    private void d_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d_searchKeyReleased
        // TODO add your handling code here:
        String searchText = d_search.getText().trim();
        if (!searchText.isEmpty()) {
            Implementation imp = new Implementation();
            ArrayList<Doctor> searchResults = imp.searchDoctor(searchText);
            updateDoctorTable(searchResults);
        } else {
            populateDoctorTable();
        }
    }//GEN-LAST:event_d_searchKeyReleased

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        int id = Integer.parseInt(d_id.getText());
        String name = d_name.getText();
        int income = Integer.parseInt(salary.getText());
        String des = designation.getText();
        String dep = department.getText();
        String qua = qualification.getText();
        Doctor updatedDoctor = new Doctor(id, name, qua, des, income, dep);
        Implementation imp = new Implementation();
        imp.updateDoctor(id, updatedDoctor);
        JOptionPane.showMessageDialog(null, "Doctpr with ID " + id + " updated successfully.");
        populateDoctorTable();
        clearTextFieldsDoc();
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == lastSelectedRow1) {
            clearTextFieldsDoc();
            lastSelectedRow1 = -1;
        } else {
            if (selectedRow >= 0) {
                d_id.setText(jTable2.getValueAt(selectedRow, 0).toString());
                d_name.setText(jTable2.getValueAt(selectedRow, 1).toString());
                qualification.setText(jTable2.getValueAt(selectedRow, 2).toString());
                designation.setText(jTable2.getValueAt(selectedRow, 3).toString());
                salary.setText(jTable2.getValueAt(selectedRow, 4).toString());
                department.setText(jTable2.getValueAt(selectedRow, 5).toString());
                lastSelectedRow1 = selectedRow;
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to view details.");
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        String patientIdText = d_id.getText();
        try {
            int doctorId = Integer.parseInt(patientIdText);
            Implementation patientDAO = new Implementation();
            patientDAO.deleteDoctor(doctorId);
            JOptionPane.showMessageDialog(null, "Doctor with ID " + doctorId + " deleted successfully.");
            populateDoctorTable();
            clearTextFieldsDoc();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Doctor ID. Please enter a valid number.");
        }
    }//GEN-LAST:event_jPanel13MouseClicked

    private void d_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_d_searchFocusGained
        // TODO add your handling code here:
        if (d_search.getText().trim().equalsIgnoreCase("Search Name")) {
            d_search.setText("");
        }
    }//GEN-LAST:event_d_searchFocusGained

    private void d_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_d_searchFocusLost
        // TODO add your handling code here:
        if (d_search.getText().isBlank()) {
            d_search.setText("Search Name");
        }
    }//GEN-LAST:event_d_searchFocusLost

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
        Appointment newAppointment = null;
        int id = Integer.parseInt(a_id.getText());
        String patientName = pat_name.getSelectedItem().toString();
        String doctorName = doc_name.getSelectedItem().toString();
        String appointmentType = app_type.getText();
        Date selectedDate = a_date.getDate();
        String timeStr = time.getText();
        if (selectedDate != null && !timeStr.isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = dateFormat.format(selectedDate);
                SimpleDateFormat timeFormat12Hour = new SimpleDateFormat("hh:mm a");
                Date parsedTime = timeFormat12Hour.parse(timeStr);
                SimpleDateFormat timeFormat24Hour = new SimpleDateFormat("HH:mm:ss");
                String formattedTimeStr = timeFormat24Hour.format(parsedTime);
                String dateTimeStr = dateStr + " " + formattedTimeStr;
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parsedDateTime = dateTimeFormat.parse(dateTimeStr);
                Timestamp appointmentDate = new Timestamp(parsedDateTime.getTime());
                newAppointment = new Appointment(id, patientName, doctorName, appointmentDate, appointmentType);
                Implementation imp = new Implementation();
                imp.addAppointment(newAppointment);
                JOptionPane.showMessageDialog(null, id);
                JOptionPane.showMessageDialog(null, "Appointment added successfully!");
                populateAppointmentTable();
                clearTextFieldsApp();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Invalid date or time format. Please use 'yyyy-MM-dd' for date and 'HH:mm a' for time.");
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select both date and time.");
        }
    }//GEN-LAST:event_jPanel16MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        // TODO add your handling code here:
        String appointmentIdStr = a_id.getText();
        if (!appointmentIdStr.isEmpty()) {
            try {
                int appointmentId = Integer.parseInt(appointmentIdStr);
                Implementation imp = new Implementation();
                imp.deleteAppointment(appointmentId);
                JOptionPane.showMessageDialog(null, "Appointment deleted successfully!");
                populateAppointmentTable();
                clearTextFieldsApp();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid appointment ID. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please enter the appointment ID to delete.");
        }
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable3.getSelectedRow();
        if (selectedRow == lastSelectedRow2) {
            clearTextFieldsApp();
            lastSelectedRow2 = -1;
        } else {
            if (selectedRow >= 0) {
                a_id.setText(jTable3.getValueAt(selectedRow, 0).toString());
                pat_name.setSelectedItem(jTable3.getValueAt(selectedRow, 1).toString());
                doc_name.setSelectedItem(jTable3.getValueAt(selectedRow, 2).toString());
                String dateString = jTable3.getValueAt(selectedRow, 3).toString();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsedDate = dateFormat.parse(dateString);
                    a_date.setDate(parsedDate);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                time.setText(jTable3.getValueAt(selectedRow, 4).toString());
                app_type.setText(jTable3.getValueAt(selectedRow, 5).toString());
                lastSelectedRow2 = selectedRow;
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to view details.");
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void s_patKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_patKeyReleased
        // TODO add your handling code here:
        String searchText = s_pat.getText().trim();
        if (!searchText.isEmpty()) {
            Implementation imp = new Implementation();
            ArrayList<Appointment> searchResults = imp.searchAppointment(searchText);
            updateAppointmenrTable(searchResults);
        } else {
            populateAppointmentTable();
        }
    }//GEN-LAST:event_s_patKeyReleased

    private void s_d_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_d_nameKeyReleased
        // TODO add your handling code here:
        String searchText = s_d_name.getText().trim();
        if (!searchText.isEmpty()) {
            updatePatientTableByDoctor(searchText);
        }

    }//GEN-LAST:event_s_d_nameKeyReleased

    private void jPanel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel19MouseClicked
        // TODO add your handling code here:
        try {
            int billId = Integer.parseInt(b_id.getText());
            int amount = Integer.parseInt(this.amount.getText());
            String payerName = payer_name.getText();
            int appointmentId = Integer.parseInt(app_id.getSelectedItem().toString());
            java.util.Date selectedDate = b_date.getDate();
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
            Bill newBill = new Bill(billId, amount, payerName, appointmentId, dateStr);
            Implementation imp = new Implementation();
            imp.addBill(newBill);
            JOptionPane.showMessageDialog(null, "Bill added successfully!");
            populateBillTable();
            clearTextFieldsBill();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for Bill ID, Amount, and Appointment ID.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jPanel19MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable4.getSelectedRow();
        if (selectedRow == lastSelectedRow3) {
            clearTextFieldsBill();
            lastSelectedRow3 = -1;
        } else {
            if (selectedRow >= 0) {
                b_id.setText(jTable4.getValueAt(selectedRow, 0).toString());
                payer_name.setText(jTable4.getValueAt(selectedRow, 3).toString());
                amount.setText(jTable4.getValueAt(selectedRow, 4).toString());
                app_id.setSelectedItem(jTable4.getValueAt(selectedRow, 1).toString());
                if (selectedRow != -1) {
                    try {
                        String dateStr = jTable4.getValueAt(selectedRow, 2).toString();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date parsedDate = dateFormat.parse(dateStr);
                        b_date.setDate(parsedDate);
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Invalid date format: " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No row selected.");
                }

                lastSelectedRow3 = selectedRow;
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to view details.");
            }
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void s_appKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_appKeyReleased
        // TODO add your handling code here
        try {
            String searchText = s_app.getText().trim();
            if (!searchText.isEmpty()) {
                Implementation imp = new Implementation();
                ArrayList<Bill> searchResults = imp.billSearch(Integer.parseInt(searchText));
                updateBillTable(searchResults);
            } else {
                populateBillTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_s_appKeyReleased

    private void s_patFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_patFocusGained
        // TODO add your handling code here:
        if (s_pat.getText().trim().equalsIgnoreCase("Search Patient")) {
            s_pat.setText("");
        }
    }//GEN-LAST:event_s_patFocusGained

    private void s_patFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_patFocusLost
        // TODO add your handling code here:
        if (s_pat.getText().isBlank()) {
            s_pat.setText("Search Patient");

        }
    }//GEN-LAST:event_s_patFocusLost

    private void s_appFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_appFocusGained
        // TODO add your handling code here:
        if (s_app.getText().trim().equalsIgnoreCase("Appointment ID")) {
            s_app.setText("");
        }
    }//GEN-LAST:event_s_appFocusGained

    private void s_appFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_appFocusLost
        // TODO add your handling code here:
        if (s_app.getText().isBlank()) {
            s_app.setText("Appointment ID");
        }
    }//GEN-LAST:event_s_appFocusLost

    private void s_d_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_d_nameFocusGained
        // TODO add your handling code here:
        if (s_d_name.getText().trim().equalsIgnoreCase("Doctor Name")) {
            s_d_name.setText("");
        }
    }//GEN-LAST:event_s_d_nameFocusGained

    private void s_d_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_d_nameFocusLost
        // TODO add your handling code here:
        if (s_d_name.getText().isBlank()) {
            s_d_name.setText("Doctor Name");
        }
    }//GEN-LAST:event_s_d_nameFocusLost

    private void s_p_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_p_nameFocusGained
        // TODO add your handling code here:
        if (s_p_name.getText().trim().equalsIgnoreCase("Patient Name")) {
            s_p_name.setText("");
        }
    }//GEN-LAST:event_s_p_nameFocusGained

    private void s_p_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s_p_nameFocusLost
        // TODO add your handling code here:
        if (s_p_name.getText().isBlank()) {
            s_p_name.setText("Patient Name");
        }
    }//GEN-LAST:event_s_p_nameFocusLost

    private void s_p_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s_p_nameKeyReleased
        // TODO add your handling code here:
        String patientName = s_p_name.getText();
        Implementation imp = new Implementation();
        Object[][] appointmentDetails = imp.AppointmentDetailBypatientName(patientName);
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        model.setRowCount(0);
        for (Object[] details : appointmentDetails) {
            model.addRow(details);
        }
    }//GEN-LAST:event_s_p_nameKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser a_date;
    private javax.swing.JTextField a_id;
    private javax.swing.JTextField age;
    private javax.swing.JTextField amount;
    private javax.swing.JComboBox<String> app_id;
    private javax.swing.JTextField app_type;
    private com.toedter.calendar.JDateChooser b_date;
    private javax.swing.JTextField b_id;
    private javax.swing.JTextField d_id;
    private javax.swing.JTextField d_name;
    private javax.swing.JTextField d_search;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField department;
    private javax.swing.JTextField designation;
    private javax.swing.JTextField disease;
    private javax.swing.JComboBox<String> doc_name;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField p_id;
    private javax.swing.JTextField p_name;
    private javax.swing.JComboBox<String> pat_name;
    private javax.swing.JTextField payer_name;
    private javax.swing.JTextField ph_no;
    private javax.swing.JTextField qualification;
    private javax.swing.JTextField s_app;
    private javax.swing.JTextField s_d_name;
    private javax.swing.JTextField s_p_name;
    private javax.swing.JTextField s_pat;
    private javax.swing.JTextField salary;
    private javax.swing.JTextField search;
    private javax.swing.JTextField time;
    private com.raven.swing.TimePicker timePicker;
    // End of variables declaration//GEN-END:variables
}
