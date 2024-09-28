package DatabaseSetup;

import java.sql.*;
import AllClasses.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Implementation implements MethodInterface {

    @Override
    public void addPatient(Patient obj) {
        Connection con = null;
        CallableStatement callableStatement = null;
        try {
            con = Database.getConnection();
            String sql = "CALL patientAdd(?, ?, ?, ?, ?);";
            callableStatement = con.prepareCall(sql);
            callableStatement.setInt(1, obj.getpId());
            callableStatement.setString(2, obj.getName());
            callableStatement.setInt(3, obj.getAge());
            callableStatement.setString(4, obj.getDisease());
            callableStatement.setString(5, obj.getPhNo());
            int row = callableStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void deletePatient(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL patientDelete(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            int rowsAffected = callableStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void updatePatient(int id, Patient obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL patientUpdate(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, obj.getName());
            callableStatement.setInt(3, obj.getAge());
            callableStatement.setString(4, obj.getDisease());
            callableStatement.setString(5, obj.getPhNo());
            int rowsAffected = callableStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public ArrayList<Patient> searchPatient(String name) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL patientSearch(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, name);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("p_id");
                String patientName = resultSet.getString("p_name");
                int age = resultSet.getInt("age");
                String medicalIssue = resultSet.getString("disease");
                String phoneNumber = resultSet.getString("phone_no");
                Patient patient = new Patient(id, patientName, age, medicalIssue, phoneNumber);
                patients.add(patient);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patients;
    }

    @Override
    public int patientNextId() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int nextPatientId = 0;
        try {
            connection = Database.getConnection();
            callableStatement = connection.prepareCall("CALL getNextPatientId(?);");
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.executeUpdate();
            nextPatientId = callableStatement.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextPatientId;
    }

    @Override
    public void addDoctor(Doctor obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorAdd(?, ?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, obj.getdId());
            callableStatement.setString(2, obj.getName());
            callableStatement.setString(3, obj.getQualification());
            callableStatement.setString(4, obj.getDesignation());
            callableStatement.setInt(5, obj.getSalary());
            callableStatement.setString(6, obj.getDepartment());
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorDelete(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void updateDoctor(int id, Doctor obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorUpdate(?, ?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.setString(2, obj.getName());
            callableStatement.setString(3, obj.getQualification());
            callableStatement.setString(4, obj.getDesignation());
            callableStatement.setInt(5, obj.getSalary());
            callableStatement.setString(6, obj.getDepartment());
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public ArrayList<Doctor> searchDoctor(String name) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorSearch(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, name);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("d_id");
                String doctorName = resultSet.getString("d_name");
                String qualification = resultSet.getString("qualification");
                String designation = resultSet.getString("designation");
                int salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");
                Doctor doctor = new Doctor(id, doctorName, qualification, designation, salary, department);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return doctors;
    }

    @Override
    public int doctorNextId() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int nextId = 0;
        try {
            connection = Database.getConnection();
            String sql = "{CALL getNextDoctorId(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            nextId = callableStatement.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextId;
    }

    public int billNextId() {
        Connection con = null;
        CallableStatement stat = null;
        int nextId = 0;
        try {
            con = Database.getConnection();
            String sql = "{CALL getNextBillId(?)}";
            stat = con.prepareCall(sql);
            stat.registerOutParameter(1, Types.INTEGER);
            stat.execute();
            nextId = stat.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextId;
    }

    @Override
    public void addAppointment(Appointment obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL addAppointmentByNames(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, obj.getaId());
            callableStatement.setString(2, obj.getpName());
            callableStatement.setString(3, obj.getdName());
            callableStatement.setTimestamp(4, obj.getDate_time());
            callableStatement.setString(5, obj.getApp_type());
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void deleteAppointment(int id) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL appointmentDelete(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, id);
            callableStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public ArrayList<Appointment> searchAppointment(String patientName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Appointment> appointments = new ArrayList<>();

        try {
            connection = Database.getConnection();
            String sql = "{CALL appointmentSearch(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, patientName);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int appId = resultSet.getInt("app_id");
                String patient = resultSet.getString("patient_name");
                String doctor = resultSet.getString("doctor_name");
                Timestamp dateTime = resultSet.getTimestamp("date_time");
                String appType = resultSet.getString("app_type");
                Appointment appointment = new Appointment(appId, patient, doctor, dateTime, appType);
                appointment.setaId(appId);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointments;
    }

    @Override
    public int appointmentNextId() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        int nextAppointmentId = 0;

        try {
            connection = Database.getConnection();
            String sql = "{CALL getNextAppointmentId(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.INTEGER);
            callableStatement.execute();
            nextAppointmentId = callableStatement.getInt(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nextAppointmentId;
    }

    @Override
    public String[] appointmentIds() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> appointmentIdList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL appointmentId()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String appId = resultSet.getString("app_id");
                appointmentIdList.add(appId);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointmentIdList.toArray(new String[0]);
    }

    @Override
    public String[] doctorList() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> doctorNameList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL doctorList()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String doctorName = resultSet.getString("d_name");
                doctorNameList.add(doctorName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return doctorNameList.toArray(new String[0]);
    }

    @Override
    public String[] patientList() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> patientNameList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL patientList()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String patientName = resultSet.getString("p_name");
                patientNameList.add(patientName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patientNameList.toArray(new String[0]);
    }

    @Override
    public void addBill(Bill obj) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            connection = Database.getConnection();
            String sql = "{CALL addBill(?, ?, ?, ?, ?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, obj.getbId());
            callableStatement.setInt(2, obj.getAmount());
            callableStatement.setString(3, obj.getPayer_name());
            callableStatement.setInt(4, obj.getaId());
            callableStatement.setDate(5, Date.valueOf(obj.getDate()));
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public ArrayList<Bill> billSearch(int appointmentId) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Bill> billList = new ArrayList<>();

        try {
            connection = Database.getConnection();
            String sql = "{CALL billSearch(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, appointmentId);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int amount = resultSet.getInt("payable_amount");
                String name = resultSet.getString("payer_ name");
                int appId = resultSet.getInt("app_id");
                Date billDate = resultSet.getDate("date");
                Bill bill = new Bill(billId, amount, name, appId, billDate.toString());
                billList.add(bill);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
        return billList;
    }

    @Override
    public ArrayList<String[]> patientsByDoctorName(String doctorName) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String[]> patientList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL getPatientsByDoctorName(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, doctorName);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String patientId = String.valueOf(resultSet.getInt("p_id"));
                String patientName = resultSet.getString("p_name");
                patientList.add(new String[]{patientId, patientName});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patientList;
    }

    @Override
    public String[][] billByDate(String date) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<String[]> billsList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL getBillsByDate(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setDate(1, java.sql.Date.valueOf(date));
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int payableAmount = resultSet.getInt("payable_amount");
                String[] billInfo = new String[2];
                billInfo[0] = String.valueOf(billId);
                billInfo[1] = String.valueOf(payableAmount);
                billsList.add(billInfo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return billsList.toArray(new String[0][0]);
    }

    @Override
    public Object[][] AppointmentDetailBypatientName(String name) {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Object[]> appointmentDetailsList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL getAppointmentDetailsByPatientName(?)}";
            callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, name);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int appId = resultSet.getInt("app_id");
                String appType = resultSet.getString("app_type");
                String disease = resultSet.getString("disease");
                String doctorName = resultSet.getString("d_name");
                int payableAmount = resultSet.getInt("payable_amount");
                Timestamp dateTime = resultSet.getTimestamp("date_time");
                appointmentDetailsList.add(new Object[]{
                    appId,
                    appType,
                    disease,
                    doctorName,
                    payableAmount,
                    dateTime
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointmentDetailsList.toArray(new Object[0][]);
    }

    @Override
    public ArrayList<Patient> showPatientData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Patient> patientList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showPatientData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int patientId = resultSet.getInt("p_id");
                String patientName = resultSet.getString("p_name");
                int patientAge = resultSet.getInt("age");
                String patientDisease = resultSet.getString("disease");
                String patientPhoneNumber = resultSet.getString("phone_no");

                Patient patient = new Patient(patientId, patientName, patientAge, patientDisease, patientPhoneNumber);
                patientList.add(patient);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return patientList;
    }

    @Override
    public ArrayList<Doctor> showDoctorData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Doctor> doctorList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showDoctorData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int doctorId = resultSet.getInt("d_id");
                String doctorName = resultSet.getString("d_name");
                String qualification = resultSet.getString("qualification");
                String designation = resultSet.getString("designation");
                int salary = resultSet.getInt("salary");
                String department = resultSet.getString("department");
                Doctor doctor = new Doctor(doctorId, doctorName, qualification, designation, salary, department);
                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return doctorList;
    }

    @Override
    public ArrayList<Bill> showBillData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Bill> billList = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showBillData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                int amount = resultSet.getInt("payable_amount");
                String patientName = resultSet.getString("payer_ name");
                int appointmentId = resultSet.getInt("app_id");
                Date billDate = resultSet.getDate("date");
                Bill bill = new Bill(billId, amount, patientName, appointmentId, billDate.toString());
                billList.add(bill);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return billList;
    }

    @Override
    public ArrayList<Appointment> showAppointmentData() {
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        ArrayList<Appointment> appointments = new ArrayList<>();
        try {
            connection = Database.getConnection();
            String sql = "{CALL showAppointmentData()}";
            callableStatement = connection.prepareCall(sql);
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int appId = resultSet.getInt("app_id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                Timestamp dateTime = resultSet.getTimestamp("date_time");
                String appType = resultSet.getString("app_type");
                Appointment appointment = new Appointment(appId, patientName, doctorName, dateTime, appType);
                appointment.setaId(appId);
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return appointments;
    }

}
